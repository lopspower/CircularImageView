package com.mikhaellopez.circularimageview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView.ScaleType.*
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class CircularImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_BORDER_WIDTH = 4f
        private const val DEFAULT_SHADOW_RADIUS = 8.0f
    }

    // Properties
    private val paint: Paint = Paint().apply { isAntiAlias = true }
    private val paintBorder: Paint = Paint().apply { isAntiAlias = true }
    private val paintShadow: Paint = Paint().apply { isAntiAlias = true }
    private val paintBackground: Paint = Paint().apply { isAntiAlias = true }
    private var circleCenter = 0
    private var heightCircle: Int = 0

    //region Attributes
    var circleColor: Int = Color.WHITE
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorStart: Int? = null
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorEnd: Int? = null
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorDirection: GradientDirection = GradientDirection.LEFT_TO_RIGHT
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var borderWidth: Float = 0f
        set(value) {
            field = value
            update()
        }
    var borderColor: Int = Color.BLACK
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var borderColorStart: Int? = null
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var borderColorEnd: Int? = null
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var borderColorDirection: GradientDirection = GradientDirection.LEFT_TO_RIGHT
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var shadowRadius: Float = 0f
        set(value) {
            field = value
            shadowEnable = shadowRadius > 0f
        }
    var shadowColor = Color.BLACK
        set(value) {
            field = value
            paintShadow.color = field
            invalidate()
        }
    var shadowGravity = ShadowGravity.BOTTOM
        set(value) {
            field = value
            invalidate()
        }
    var shadowEnable = false
        set(value) {
            field = value
            if (field && shadowRadius == 0f) {
                shadowRadius = DEFAULT_SHADOW_RADIUS * resources.displayMetrics.density
            }
            update()
        }
    //endregion

    // Color Filter
    private var civColorFilter: ColorFilter? = null
        set(value) {
            if (field != value) {
                field = value
                if (field != null) {
                    civDrawable = null // To force re-update shader
                    invalidate()
                }
            }
        }

    // Object used to draw
    private var civImage: Bitmap? = null
    private var civDrawable: Drawable? = null

    init {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        // Load the styled attributes and set their properties
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyleAttr, 0)

        // Init Background Color
        circleColor =
            attributes.getColor(R.styleable.CircularImageView_civ_circle_color, Color.WHITE)
        attributes.getColor(R.styleable.CircularImageView_civ_circle_color_start, 0)
            .also { if (it != 0) circleColorStart = it }
        attributes.getColor(R.styleable.CircularImageView_civ_circle_color_end, 0)
            .also { if (it != 0) circleColorEnd = it }
        circleColorDirection = attributes.getInteger(
            R.styleable.CircularImageView_civ_circle_color_direction,
            circleColorDirection.value
        ).toGradientDirection()

        // Init Border
        if (attributes.getBoolean(R.styleable.CircularImageView_civ_border, true)) {
            val defaultBorderWidth =
                DEFAULT_BORDER_WIDTH * resources.displayMetrics.density
            borderWidth = attributes.getDimension(
                R.styleable.CircularImageView_civ_border_width,
                defaultBorderWidth
            )
            borderColor =
                attributes.getColor(R.styleable.CircularImageView_civ_border_color, Color.WHITE)
            attributes.getColor(R.styleable.CircularImageView_civ_border_color_start, 0)
                .also { if (it != 0) borderColorStart = it }
            attributes.getColor(R.styleable.CircularImageView_civ_border_color_end, 0)
                .also { if (it != 0) borderColorEnd = it }
            borderColorDirection = attributes.getInteger(
                R.styleable.CircularImageView_civ_border_color_direction,
                borderColorDirection.value
            ).toGradientDirection()
        }

        // Init Shadow
        shadowEnable = attributes.getBoolean(R.styleable.CircularImageView_civ_shadow, shadowEnable)
        if (shadowEnable) {
            shadowGravity = attributes.getInteger(
                R.styleable.CircularImageView_civ_shadow_gravity,
                shadowGravity.value
            ).toShadowGravity()
            val defaultShadowRadius = DEFAULT_SHADOW_RADIUS * resources.displayMetrics.density
            shadowRadius = attributes.getDimension(
                R.styleable.CircularImageView_civ_shadow_radius,
                defaultShadowRadius
            )
            shadowColor =
                attributes.getColor(R.styleable.CircularImageView_civ_shadow_color, shadowColor)
        }

        attributes.recycle()
    }
    //endregion

    //region Set Attr Method
    override fun setColorFilter(colorFilter: ColorFilter?) {
        civColorFilter = colorFilter
    }

    override fun getScaleType(): ScaleType =
        super.getScaleType() ?: CENTER_CROP

    override fun setScaleType(scaleType: ScaleType) {
        require(
            listOf(
                CENTER_CROP,
                CENTER_INSIDE,
                FIT_CENTER
            ).contains(scaleType)
        ) {
            "ScaleType $scaleType not supported. Just ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE & ScaleType.FIT_CENTER are available for this library."
        }
        super.setScaleType(scaleType)
    }
    //endregion

    //region Draw Method
    override fun onDraw(canvas: Canvas) {
        // Load the bitmap
        loadBitmap()

        // Check if civImage isn't null
        if (civImage == null) return

        val circleCenterWithBorder = circleCenter + borderWidth
        val margeWithShadowRadius = if (shadowEnable) shadowRadius * 2 else 0f

        // Draw Shadow
        if (shadowEnable) {
            drawShadow()
            canvas.drawCircle(
                circleCenterWithBorder,
                circleCenterWithBorder,
                circleCenterWithBorder - margeWithShadowRadius,
                paintShadow
            )
        }
        // Draw Border
        canvas.drawCircle(
            circleCenterWithBorder,
            circleCenterWithBorder,
            circleCenterWithBorder - margeWithShadowRadius,
            paintBorder
        )
        // Draw Circle background
        canvas.drawCircle(
            circleCenterWithBorder,
            circleCenterWithBorder,
            circleCenter - margeWithShadowRadius,
            paintBackground
        )
        // Draw CircularImageView
        canvas.drawCircle(
            circleCenterWithBorder,
            circleCenterWithBorder,
            circleCenter - margeWithShadowRadius,
            paint
        )
    }

    private fun update() {
        if (civImage != null)
            updateShader()

        val usableWidth = width - (paddingLeft + paddingRight)
        val usableHeight = height - (paddingTop + paddingBottom)

        heightCircle = min(usableWidth, usableHeight)

        circleCenter = (heightCircle - borderWidth * 2).toInt() / 2
        manageCircleColor()
        manageBorderColor()

        manageElevation()
        invalidate()
    }

    private fun manageCircleColor() {
        paintBackground.shader = createLinearGradient(
            circleColorStart ?: circleColor,
            circleColorEnd ?: circleColor, circleColorDirection
        )
    }

    private fun manageBorderColor() {
        val borderColor = if (borderWidth == 0f) circleColor else this.borderColor
        paintBorder.shader = createLinearGradient(
            borderColorStart ?: borderColor,
            borderColorEnd ?: borderColor, borderColorDirection
        )
    }

    private fun createLinearGradient(
        startColor: Int,
        endColor: Int,
        gradientDirection: GradientDirection
    ): LinearGradient {
        var x0 = 0f
        var y0 = 0f
        var x1 = 0f
        var y1 = 0f
        when (gradientDirection) {
            GradientDirection.LEFT_TO_RIGHT -> x1 = width.toFloat()
            GradientDirection.RIGHT_TO_LEFT -> x0 = width.toFloat()
            GradientDirection.TOP_TO_BOTTOM -> y1 = height.toFloat()
            GradientDirection.BOTTOM_TO_TOP -> y0 = height.toFloat()
        }
        return LinearGradient(x0, y0, x1, y1, startColor, endColor, Shader.TileMode.CLAMP)
    }

    private fun manageElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outlineProvider = if (!shadowEnable) object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setOval(0, 0, heightCircle, heightCircle)
                }
            } else {
                null
            }
        }
    }

    private fun loadBitmap() {
        if (civDrawable == drawable) return

        civDrawable = drawable
        civImage = drawableToBitmap(civDrawable)
        updateShader()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        update()
    }

    private fun drawShadow() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, paintShadow)
        }

        var dx = 0.0f
        var dy = 0.0f

        when (shadowGravity) {
            ShadowGravity.CENTER -> {
                /*dx, dy = 0.0f*/
            }
            ShadowGravity.TOP -> dy = -shadowRadius / 2
            ShadowGravity.BOTTOM -> dy = shadowRadius / 2
            ShadowGravity.START -> dx = -shadowRadius / 2
            ShadowGravity.END -> dx = shadowRadius / 2
        }

        paintShadow.setShadowLayer(shadowRadius, dx, dy, shadowColor)
    }

    private fun updateShader() {
        civImage?.also {
            // Create Shader
            val shader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

            // Apply matrix in Shader with scaleType
            shader.setLocalMatrix(
                when (scaleType) {
                    CENTER_CROP -> centerCrop(it, heightCircle)
                    CENTER_INSIDE -> centerInside(it, heightCircle)
                    FIT_CENTER -> fitCenter(it, heightCircle)
                    else -> Matrix()
                }
            )

            // Set Shader in Paint
            paint.shader = shader

            // Apply colorFilter
            paint.colorFilter = civColorFilter
        }
    }

    private fun centerCrop(bitmap: Bitmap, viewSize: Int): Matrix =
        Matrix().apply {
            val scale: Float
            val dx: Float
            val dy: Float
            if (bitmap.width * viewSize > bitmap.height * viewSize) {
                scale = viewSize / bitmap.height.toFloat()
                dx = (viewSize - bitmap.width * scale) * .5f
                dy = 0f
            } else {
                scale = viewSize / bitmap.width.toFloat()
                dx = 0f
                dy = (viewSize - bitmap.height * scale) * .5f
            }
            setScale(scale, scale)
            postTranslate(dx, dy)
        }

    private fun centerInside(bitmap: Bitmap, viewSize: Int): Matrix =
        Matrix().apply {
            val scale = if (bitmap.width <= viewSize && bitmap.height <= viewSize) {
                1.0f
            } else {
                (viewSize.toFloat() / bitmap.width.toFloat()).coerceAtMost(viewSize.toFloat() / bitmap.height.toFloat())
            }

            val dx: Float = ((viewSize - bitmap.width * scale) * .5f).roundToInt().toFloat()
            val dy: Float = ((viewSize - bitmap.height * scale) * .5f).roundToInt().toFloat()

            setScale(scale, scale)
            postTranslate(dx, dy)
        }

    private fun fitCenter(bitmap: Bitmap, viewSize: Int): Matrix =
        Matrix().apply {
            setRectToRect(
                RectF().apply { set(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat()) },
                RectF().apply { set(0f, 0f, viewSize.toFloat(), viewSize.toFloat()) },
                Matrix.ScaleToFit.CENTER
            )
        }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? =
        drawable?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && drawable is VectorDrawable) {
                drawable.vectorDrawableToBitmap()
            } else {
                when (drawable) {
                    is BitmapDrawable -> drawable.bitmapDrawableToBitmap()
                    else -> drawable.toBitmap()
                }
            }
        }

    private fun VectorDrawable.vectorDrawableToBitmap(): Bitmap {
        // Generate max bitmap size from view when is vector drawable
        // no when scale type is CENTER_INSIDE
        val bitmap = Bitmap.createBitmap(
            if (scaleType == CENTER_INSIDE) this.intrinsicWidth else width,
            if (scaleType == CENTER_INSIDE) this.intrinsicHeight else height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        this.setBounds(0, 0, canvas.width, canvas.height)
        this.draw(canvas)
        return bitmap
    }

    private fun BitmapDrawable.bitmapDrawableToBitmap(): Bitmap =
        bitmap.let {
            Bitmap.createScaledBitmap(
                it,
                this.intrinsicWidth,
                this.intrinsicHeight,
                false
            )
        }

    private fun Drawable.toBitmap(): Bitmap? =
        try {
            // Create Bitmap object out of the drawable
            val bitmap = Bitmap.createBitmap(
                this.intrinsicWidth,
                this.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            this.setBounds(0, 0, canvas.width, canvas.height)
            this.draw(canvas)
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    //endregion

    //region Measure Method
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val usableWidth = measure(widthMeasureSpec) - (paddingLeft + paddingRight)
        val usableHeight = measure(heightMeasureSpec) - (paddingTop + paddingBottom)
        heightCircle = min(usableWidth, usableHeight)
        setMeasuredDimension(heightCircle, heightCircle)
    }

    private fun measure(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize // The parent has determined an exact size for the child.
            MeasureSpec.AT_MOST -> specSize // The child can be as large as it wants up to the specified size.
            else -> heightCircle // The parent has not imposed any constraint on the child.
        }
    }
    //endregion

    private fun Int.toShadowGravity(): ShadowGravity =
        when (this) {
            1 -> ShadowGravity.CENTER
            2 -> ShadowGravity.TOP
            3 -> ShadowGravity.BOTTOM
            4 -> ShadowGravity.START
            5 -> ShadowGravity.END
            else -> throw IllegalArgumentException("This value is not supported for ShadowGravity: $this")
        }

    private fun Int.toGradientDirection(): GradientDirection =
        when (this) {
            1 -> GradientDirection.LEFT_TO_RIGHT
            2 -> GradientDirection.RIGHT_TO_LEFT
            3 -> GradientDirection.TOP_TO_BOTTOM
            4 -> GradientDirection.BOTTOM_TO_TOP
            else -> throw IllegalArgumentException("This value is not supported for GradientDirection: $this")
        }

    /**
     * ShadowGravity enum class to set the gravity of the CircleView shadow
     */
    enum class ShadowGravity(val value: Int) {
        CENTER(1),
        TOP(2),
        BOTTOM(3),
        START(4),
        END(5)
    }

    /**
     * GradientDirection enum class to set the direction of the gradient color
     */
    enum class GradientDirection(val value: Int) {
        LEFT_TO_RIGHT(1),
        RIGHT_TO_LEFT(2),
        TOP_TO_BOTTOM(3),
        BOTTOM_TO_TOP(4)
    }

}