# banner

![](https://github.com/liugongce/banner/blob/master/banner.gif)

## download

Step 1. Add the JitPack repository to your build file 

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```
dependencies {
		implementation 'com.github.User:Repo:Tag'
	}
```
## usage

```
    RollViewPage.build(new BannerBuilder(this)
                .setBanList(list)
                .setNotInterceptTouch(true)
                .setRelativeLayout((RelativeLayout) findViewById(R.id.rl_banner2))
                .setCardViewOptions(new CardModeOptions().setCardMode(true))
                .setDotOptions(new DotOptions().setShowDot(true).setMode(Mode.middle)
                        .setDotSize(dip2px(this, 10)))
                .setCirculationModeOptions(new CirculationModeOptions()
                        .setAutoCirculation(true)
                        .setRepeat(true)));
```

|params|参数使用|
|:---|:---|
