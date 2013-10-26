CircularImageView
=================

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

Image Result
-----

![Capture Project](http://i42.tinypic.com/123lhkg.png)

USAGE
-----

To make your circular ImageView, you must retrieve the class CircularImageView and copy your project, and then use it in your XML not forget to change the `com.mikhaellopez` with your name paquage.


XML
-----

```xml
<com.mikhaellopez.circularimageview.CircularImageView
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:src="@drawable/image"
        app:border_color="@color/GrayLight"
        app:border_width="10"
        app:shadow="true" />
```

JAVA
-----

```java
package com.mikhaellopez.circularimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircularImageView extends ImageView {

	public CircularImageView(Context context) {
		super(context);
	}

	public CircularImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (drawable == null)
			return;
		if (getWidth() == 0 || getHeight() == 0)
			return;
		Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap().copy(Bitmap.Config.ARGB_8888, true);		
		Bitmap roundBitmap =  getCircleBitmap(bitmap, getWidth());
		
		Paint paint = new Paint();    
        paint.setColor(Color.parseColor("#FFFFFF"));
		
		canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, paint);
		canvas.drawBitmap(roundBitmap, 0, 0, null);
	}
	
	public static Bitmap getCircleBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;
		if(bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
		
		Paint paint = new Paint();
		Canvas c = new Canvas(output);        
		c.drawARGB(0, 0, 0, 0);
		c.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2, sbmp.getWidth() / 2 - 10, paint);
		
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		
		Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
		c.drawBitmap(sbmp, rect, rect, paint);

		return output;
	}
}
```

LINK
-----

**Stack OverFlow:**

I realized this project using this post:
* [Create circular image view in android](http://stackoverflow.com/a/16208548/1832221)
* [How to add a shadow and a border on circular imageView android?](http://stackoverflow.com/q/17655264/1832221)
