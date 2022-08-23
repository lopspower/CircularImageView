<p align="center"><img src="/preview/header.png"></p>

CircularImageView
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="300" height="435" align="right" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Maven Central](https://img.shields.io/maven-central/v/com.mikhaellopez/circularimageview.svg?label=Maven%20Central)](https://search.maven.org/artifact/com.mikhaellopez/circularimageview)
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To make a circular ImageView add CircularImageView in your layout XML and add CircularImageView library in your project or you can also grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:circularimageview:4.3.1'
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
    app:civ_shadow_radius="10dp"
    app:civ_shadow_color="#3f51b5"/>
```

You must use the following properties in your XML to change your CircularImageView.

| Properties                       | Type                                                         | Default          |
| -------------------------------- | ------------------------------------------------------------ | ---------------- |
| `app:civ_circle_color`           | color                                                        | WHITE            |
| `app:civ_circle_color_start`     | color                                                        | civ_circle_color |
| `app:civ_circle_color_end`       | color                                                        | civ_circle_color |
| `app:civ_color_direction`        | left_to_right, right_to_left, top_to_bottom or bottom_to_top | left_to_right    |
| `app:civ_border`                 | boolean                                                      | true             |
| `app:civ_border_width`           | dimension                                                    | 4dp              |
| `app:civ_border_color`           | color                                                        | WHITE            |
| `app:civ_border_color_start`     | color                                                        | civ_border_color |
| `app:civ_border_color_end`       | color                                                        | civ_border_color |
| `app:civ_border_color_direction` | left_to_right, right_to_left, top_to_bottom or bottom_to_top | left_to_right    |
| `app:civ_shadow`                 | boolean                                                      | false            |
| `app:civ_shadow_color`           | color                                                        | BLACK            |
| `app:civ_shadow_radius`          | dimension                                                    | 8dp              |
| `app:civ_shadow_gravity`         | center, top, bottom, start or end                            | bottom           |

:information_source: You can also use `android:elevation` instead of `app:civ_shadow` to have default Material Design elevation.

KOTLIN
-----

```kotlin
val circularImageView = findViewById<CircularImageView>(R.id.circularImageView)
circularImageView.apply {
    // Set Color
    circleColor = Color.WHITE
    // or with gradient
    circleColorStart = Color.BLACK
    circleColorEnd = Color.RED
    circleColorDirection = CircularImageView.GradientDirection.TOP_TO_BOTTOM

    // Set Border
    borderWidth = 10f
    borderColor = Color.BLACK
    // or with gradient
    borderColorStart = Color.BLACK
    borderColorEnd = Color.RED
    borderColorDirection = CircularImageView.GradientDirection.TOP_TO_BOTTOM
    
    // Add Shadow with default param
    shadowEnable = true
    // or with custom param
    shadowRadius = 7f
    shadowColor = Color.RED
    shadowGravity = CircularImageView.ShadowGravity.CENTER
}
```

JAVA
-----

```java
CircularImageView circularImageView = findViewById(R.id.circularImageView);
// Set Color
circularImageView.setCircleColor(Color.WHITE);
// or with gradient
circularImageView.setCircleColorStart(Color.BLACK);
circularImageView.setCircleColorEnd(Color.RED);
circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
circularImageView.setBorderWidth(10f);
circularImageView.setBorderColor(Color.BLACK);
// or with gradient
circularImageView.setBorderColorStart(Color.BLACK);
circularImageView.setBorderColorEnd(Color.RED);
circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
circularImageView.setShadowEnable(true);
// or with custom param
circularImageView.setShadowRadius(7f);
circularImageView.setShadowColor(Color.RED);
circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
```

LIMITATIONS
-----

-   By default the ScaleType is **FIT_CENTER**. You can also used **CENTER_INSIDE** AND **CENTER_CROP**.
-   Enabling adjustViewBounds is not supported as this requires an unsupported ScaleType.

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/lopspower/CircularImageView/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/lopspower?tab=followers) for my next creations 👍

LICENCE
-----

CircularImageView by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
