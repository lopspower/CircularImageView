package com.mikhaellopez.circularimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView {
	private int borderWidth;
	private int viewWidth;
	private int viewHeight;
	private Bitmap image;
	private Paint paint;
	private Paint paintBorder;
	private BitmapShader shader;

	public CircularImageView(final Context context) {
		this(context, null);
	}

	public CircularImageView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.circularImageViewStyle);
	}

	public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// init paint
		paint = new Paint();
		paint.setAntiAlias(true);

		paintBorder = new Paint();
		paintBorder.setAntiAlias(true);

		// load the styled attributes and set their properties
		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyle, 0);

		if(attributes.getBoolean(R.styleable.CircularImageView_border, true)) {
			setBorderWidth(attributes.getColor(R.styleable.CircularImageView_border_width, 4));
			setBorderColor(attributes.getInt(R.styleable.CircularImageView_border_color, Color.WHITE));
		}
		
		if(attributes.getBoolean(R.styleable.CircularImageView_shadow, false))
			addShadow();
	}

	
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
		this.invalidate();
	}

	public void setBorderColor(int borderColor) {
		if (paintBorder != null)
			paintBorder.setColor(borderColor);
		this.invalidate();
	}

	public void addShadow() {
		setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
		paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// load the bitmap
		BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
		if (bitmapDrawable != null)
			image = bitmapDrawable.getBitmap();

		// init shader
		if (image != null) {
			shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(), canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
			paint.setShader(shader);
			int circleCenter = viewWidth / 2;

			// circleCenter is the x or y of the view's center
			// radius is the radius in pixels of the cirle to be drawn
			// paint contains the shader that will texture the shape
			canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter + borderWidth - 4.0f, paintBorder);
			canvas.drawCircle(circleCenter + borderWidth, circleCenter + borderWidth, circleCenter - 4.0f, paint);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec, widthMeasureSpec);

		viewWidth = width - (borderWidth * 2);
		viewHeight = height - (borderWidth * 2);

		setMeasuredDimension(width, height);
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			result = viewWidth;
		}

		return result;
	}

	private int measureHeight(int measureSpecHeight, int measureSpecWidth) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpecHeight);
		int specSize = MeasureSpec.getSize(measureSpecHeight);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text (beware: ascent is a negative number)
			result = viewHeight;
		}

		return (result + 2);
	}
}