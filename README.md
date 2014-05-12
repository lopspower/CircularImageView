CircularImageView
=================

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

Image Result
-----

![Capture Project](http://i40.tinypic.com/10eiqfk.png)

USAGE
-----

To make a circular ImageView add CircularImageView library in your project and add CircularImageView in your layout XML.


XML
-----

```xml
<com.mikhaellopez.circularimageview.CircularImageView
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:src="@drawable/image"
        app:border_color="#EEEEEE"
        app:border_width="4dp"
        app:shadow="true" />
```

You must use the following properties in your XML to change your CircularImageView.


#####Properties:

* `app:border`       (boolean)   -> default true
* `app:border_color` (color)     -> default WHITE
* `app:border_width` (dimension) -> default 4dp
* `app:shadow`       (boolean)   -> default false

JAVA
-----

```java
CircularImageView circularImageView = (CircularImageView)findViewById(R.id.yourCircularImageView);
circularImageView.setBorderColor(getResources().getColor(R.color.GrayLight));
circularImageView.setBorderWidth(10);
circularImageView.addShadow();
```

LINK
-----

**Stack OverFlow:**

I realized this project using this post:
* [Create circular image view in android](http://stackoverflow.com/a/16208548/1832221)
* [How to add a shadow and a border on circular imageView android?](http://stackoverflow.com/q/17655264/1832221)


LICENCE
-----

CircularImageView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).
Based on a work at https://github.com/lopspower/CircularImageView.
