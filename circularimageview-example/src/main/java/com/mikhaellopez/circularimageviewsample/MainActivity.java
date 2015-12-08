package com.mikhaellopez.circularimageviewsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by Mikhael LOPEZ on 09/10/15.
 */
public class MainActivity extends AppCompatActivity {

    private CircularImageView circularImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularImageView = (CircularImageView) findViewById(R.id.circularImageView);

        // BORDER
        ((SeekBar) findViewById(R.id.seekBarBorderWidth)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circularImageView.setBorderWidth(progress * (int) getResources().getDisplayMetrics().density);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        // AMPLITUDE
        ((SeekBar) findViewById(R.id.seekBarShadowRadius)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circularImageView.setShadowRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // COLOR
        ((LobsterShadeSlider) findViewById(R.id.shadeslider)).addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                circularImageView.setBorderColor(color);
                circularImageView.setShadowColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/lopspower/CircularImageView")));
                return true;
            case R.id.beer:
                new AlertDialog.Builder(this)
                        .setTitle(getResources().getString(R.string.pay_me_a_beer))
                        .setMessage(getResources().getString(R.string.offer_me_a_beer))
                        .setPositiveButton(getResources().getString(android.R.string.ok).toUpperCase(), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/LopezMikhael")));
                            }
                        })
                        .setNegativeButton(getResources().getString(android.R.string.cancel).toUpperCase(), null)
                        .show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
