Moticons

<p align="center">
  <a target="_blank" href="https://travis-ci.org/javiersantos/Moticons"><img src="https://travis-ci.org/javiersantos/Moticons.svg?branch=master"></a>
  <a target="_blank" href="http://android-arsenal.com/details/3/2853"><img src="https://img.shields.io/badge/Android%20Arsenal-Moticons-brightgreen.svg?style=flat"></a>
  <span class="badge-paypal"><a href="https://www.paypal.me/javiersantos" title="Donate using PayPal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a></span>
  <span class="badge-patreon"><a href="http://patreon.com/javiersantos" title="Donate using Patreon"><img src="https://img.shields.io/badge/patreon-donate-yellow.svg" /></a></span>
</p>

<h1 align="center">CircularImageView</h1>
=================

<p align="center">
  <a target="_blank" href="https://android-arsenal.com/details/1/2846"><img src="https://img.shields.io/badge/Android%20Arsenal-CircularImageView-brightgreen.svg?style=flat"></a>
  <a target="_blank" href="http://developer.android.com/index.html"><img src="https://img.shields.io/badge/platform-android-green.svg"></a>
  <a target="_blank" href="https://android-arsenal.com/api?level=11"><img src="https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat"></a>
  <a target="_blank" href="http://search.maven.org/#artifactdetails|com.mikhaellopez|circularimageview|2.0.2|"><img src="https://img.shields.io/maven-central/v/com.mikhaellopez/circularimageview.svg"></a>
  <a target="_blank" href="http://twitter.com/lopezmikhael"><img src="https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat"></a>
</p>

This is an Android project allowing to realize a circular ImageView in the simplest way possible.

Image Result
-----

![Capture Project](http://i40.tinypic.com/10eiqfk.png)

USAGE
-----

To make a circular ImageView add CircularImageView in your layout XML and add CircularImageView library in your project or you can also grab it via Gradle:

```groovy
compile 'com.mikhaellopez:circularimageview:2.0.2'
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
