package com.mikhaellopez.circularimageviewsample;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.ScaleAnimation;

/**
 * Created by Mikhael LOPEZ on 09/10/15.
 */
public class MainActivity extends AppCompatActivity {

    private View imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        imageView = findViewById(R.id.circularImage);
    }

    public void onButtonClicked(View v) {
        //Scale button for profiling memory usage
        final ValueAnimator anim = ValueAnimator.ofFloat(1.0f, 0.5f);
        anim.setDuration(3000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                imageView.setScaleX((float)anim.getAnimatedValue());
                imageView.invalidate();
            }
        });
        anim.start();

    }
}
