<p align="center"><img src="http://i67.tinypic.com/2ij1d2r.jpg"></p>

CircularImageView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="435" align="right" vspace="52" />

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
<br>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CircularImageView-lightgrey.svg?style=flat)](https://android-arsenal.com/details/1/2846)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular ImageView add CircularImageView in your layout XML and add CircularImageView library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:circularimageview:3.2.0'
```

XML
-----

```xml
<com.mikhaellopez.circularimageview.CircularImageView
        android:layout_width="250dp"
        android:layout_height="250dp"
        android:src="@drawable/image"
        app:civ_border_color="#EEEEEE"
        app:civ_border_width="4dp"
        app:civ_shadow="true"
        app:civ_shadow_radius="10"
        app:civ_shadow_color="#8BC34A"/>
```

You must use the following properties in your XML to change your CircularImageView.


##### Properties:

* `app:civ_border`              (boolean)   -> default true
* `app:civ_border_color`        (color)     -> default WHITE
* `app:civ_border_width`        (dimension) -> default 4dp
* `app:civ_background_color`    (color) -> default WHITE
* `app:civ_shadow`              (boolean)   -> default false
* `app:civ_shadow_color`        (color)     -> default BLACK
* `app:civ_shadow_radius`       (float)     -> default 8.0f
* `app:civ_shadow_gravity`      (center, top, bottom, start or end) -> default bottom

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
circularImageView.setBackgroundColor(Color.RED);
circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
```

LIMITATIONS
-----

* By default the ScaleType is CENTER_CROP. You can also used CENTER_INSIDE but the others one are not supported.
* Enabling adjustViewBounds is not supported as this requires an unsupported ScaleType.

LINK
-----

**Stack OverFlow:**

I realized this project using this post:
* [Create circular image view in android](http://stackoverflow.com/a/16208548/1832221)
* [How to add a shadow and a border on circular imageView android?](http://stackoverflow.com/q/17655264/1832221)


LICENCE
-----

CircularImageView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
