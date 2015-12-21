<p align="center"><a target="_blank" href="https://play.google.com/store/apps/details?id=com.mikhaellopez.circularimageviewsample" title="Download on Google Play Store"><img src="http://i67.tinypic.com/2ij1d2r.jpg"></a></p>

CircularImageView
=================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CircularImageView-lightgrey.svg?style=flat)](https://android-arsenal.com/details/1/2846)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

<a target="_blank" href="https://play.google.com/store/apps/details?id=com.mikhaellopez.circularimageviewsample">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular ImageView add CircularImageView in your layout XML and add CircularImageView library in your project or you can also grab it via Gradle:

```groovy
compile 'com.mikhaellopez:circularimageview:2.1.1'
```

XML
-----

```xml
<com.mikhaellopez.circularimageview.CircularImageView
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:src="@drawable/image"
        app:border_color="#EEEEEE"
        app:border_width="4dp"
        app:shadow="true"
        app:shadow_radius="10"
        app:shadow_color="#8BC34A"/>
```

You must use the following properties in your XML to change your CircularImageView.


#####Properties:

* `app:border`          (boolean)   -> default true
* `app:border_color`    (color)     -> default WHITE
* `app:border_width`    (dimension) -> default 4dp
* `app:shadow`          (boolean)   -> default false
* `app:shadow_color`    (color)     -> default BLACK
* `app:shadow_radius`   (float)     -> default 8.0f

JAVA
-----

```java
CircularImageView circularImageView = (CircularImageView)findViewById(R.id.yourCircularImageView);
// Set Border
circularImageView.setBorderColor(getResources().getColor(R.color.GrayLight));
circularImageView.setBorderWidth(10);
// Add Shadow with default param
circularImageView.addShadow();
// or with custom param
circularImageView.setShadowRadius(15);
circularImageView.setShadowColor(Color.RED);
```

LINK
-----

**Stack OverFlow:**

I realized this project using this post:
* [Create circular image view in android](http://stackoverflow.com/a/16208548/1832221)
* [How to add a shadow and a border on circular imageView android?](http://stackoverflow.com/q/17655264/1832221)


LICENCE
-----

CircularImageView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
