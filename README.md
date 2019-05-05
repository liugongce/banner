# banner
## show
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
		implementation 'com.github.liugongce:banner:1.0'
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

|METHOD|  |
|:---|:---|
|setBanList() | view或者图片地址的集合,可混合传入|
|setNotInterceptTouch()|是否阻断手指触摸事件|
|setRelativeLayout()| 一个空的RelativeLayout|
|setCardViewOptions()|传入CardModeOptions对象,配置是否显示卡片模式和PageTransformer切换特效|
|setDotOptions()|传入DotOptions对象,可配置小圆点的显示隐藏、选中颜色和未选中颜色、小圆点的大小、显示位置(左中右)|
|setDotLayoutConfig()|使用回调配置小圆点布局,方便特殊时候自定义|
|setOnBinerListener()|使用此回调自行配置布局,可自己设计卡片样式|
|setOnPageChangeListener()|卡片切换事件|
|setOnItemViewClickListener()|条目点击事件|
|setViewpagerConfig()|回调出viewpager本体,特殊需求时自行操作|
|setCirculationModeOptions()|传入CirculationModeOptions对象,设置是否自动轮播,是否无限循环,卡片切换时间|
