<p align="center"><img src="/preview/header.png"></p>

CircularImageView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="435" align="right" vspace="52" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Download](https://api.bintray.com/packages/lopspower/maven/com.mikhaellopez:circularimageview/images/download.svg?version=4.0.2)](https://bintray.com/lopspower/maven/com.mikhaellopez:circularimageview/4.0.2/link)
<br>
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/57b73cd8e4b242389acf4341b7ca7269)](https://www.codacy.com/app/lopspower/CircularImageView?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lopspower/CircularImageView&amp;utm_campaign=Badge_Grade)

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular ImageView add CircularImageView in your layout XML and add CircularImageView library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:circularimageview:4.0.2'
```

XML
-----

```xml
<com.mikhaellopez.circularimageview.CircularImageView
    android:layout_width="250dp"
    android:layout_height="250dp"
    android:src="@drawable/image"
    app:civ_border_color="#3f51b5"
    app:civ_border_width="4dp"
    app:civ_shadow="true"
    app:civ_shadow_radius="10"
    app:civ_shadow_color="#3f51b5"/>
```

You must use the following properties in your XML to change your CircularImageView.

| Properties               | Type                              | Default |
| ------------------------ | --------------------------------- | ------- |
| `app:civ_circle_color`   | color                             | WHITE   |
| `app:civ_border`         | boolean                           | true    |
| `app:civ_border_width`   | dimension                         | 4dp     |
| `app:civ_border_color`   | color                             | WHITE   |
| `app:civ_shadow`         | boolean                           | false   |
| `app:civ_shadow_color`   | color                             | BLACK   |
| `app:civ_shadow_radius`  | float                             | 8.0f    |
| `app:civ_shadow_gravity` | center, top, bottom, start or end | bottom  |

:information_source: You can also use `android:elevation` instead of `app:civ_shadow` to have default Material Design elevation.

KOTLIN
-----

```kotlin
val circularImageView = findViewById<CircularImageView>(R.id.circularImageView)
circularImageView.apply {
    // Set Circle color for transparent image
    circleColor = Color.WHITE
    // Set Border
    borderColor = Color.RED
    borderWidth = 10f
    // Add Shadow with default param
    shadowEnable = true
    // or with custom param
    shadowRadius = 15f
    shadowColor = Color.RED
    shadowGravity = CircularImageView.ShadowGravity.CENTER
}
```

JAVA
-----

```java
CircularImageView circularImageView = findViewById(R.id.circularImageView);
// Set Circle color for transparent image
circularImageView.setCircleColor(Color.WHITE);
// Set Border
circularImageView.setBorderColor(Color.RED);
circularImageView.setBorderWidth(10);
// Add Shadow with default param
circularImageView.setShadowEnable(true);
// or with custom param
circularImageView.setShadowRadius(15);
circularImageView.setShadowColor(Color.RED);
circularImageView.setBackgroundColor(Color.RED);
circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
```

LIMITATIONS
-----

-   By default the ScaleType is **CENTER_CROP**. You can also used **CENTER_INSIDE** but the others one are not supported.
-   Enabling adjustViewBounds is not supported as this requires an unsupported ScaleType.

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/lopspower/CircularImageView/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/lopspower?tab=followers) for my next creations 👍

LICENCE
-----

CircularImageView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
