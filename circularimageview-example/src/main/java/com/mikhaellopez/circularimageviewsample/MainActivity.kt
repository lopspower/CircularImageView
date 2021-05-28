package com.mikhaellopez.circularimageviewsample

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.larswerkman.lobsterpicker.OnColorListener
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider
import com.mikhaellopez.circularimageview.CircularImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circularImageView = findViewById<CircularImageView>(R.id.circularImageView)
        findViewById<SeekBar>(R.id.seekBarBorderWidth).onProgressChanged { circularImageView.borderWidth = it.toDp() }
        findViewById<SeekBar>(R.id.seekBarShadowRadius).onProgressChanged { circularImageView.shadowRadius = it.toDp() }
        findViewById<LobsterShadeSlider>(R.id.shadeSlider).onColorChanged {
            circularImageView.borderColor = it
            circularImageView.shadowColor = it
        }
    }

    //region Extensions
    private fun Int.toDp(): Float =
        this * resources.displayMetrics.density

    private fun SeekBar.onProgressChanged(onProgressChanged: (Int) -> Unit) {
        setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                onProgressChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Nothing
            }
        })
    }

    private fun LobsterShadeSlider.onColorChanged(onColorChanged: (Int) -> Unit) {
        addOnColorListener(object : OnColorListener {
            override fun onColorChanged(color: Int) {
                onColorChanged(color)
            }

            override fun onColorSelected(color: Int) {
                // Nothing
            }
        })
    }
    //endregion

}