package cece.com.bannerlib;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

import cece.com.bannerlib.adapter.MyPagerAdapter;
import cece.com.bannerlib.adapter.RollPagerAdapter;
import cece.com.bannerlib.callback.DefaultBinderListener;
import cece.com.bannerlib.callback.DefaultCardBinderListener;
import cece.com.bannerlib.callback.DotLayoutConfig;
import cece.com.bannerlib.callback.OnItemViewClickListener;
import cece.com.bannerlib.callback.OnPagerAdapterBinerListener;
import cece.com.bannerlib.callback.ViewpagerConfig;
import cece.com.bannerlib.config.BannerBuilder;
import cece.com.bannerlib.config.CardModeOptions;
import cece.com.bannerlib.config.CirculationModeOptions;
import cece.com.bannerlib.config.DotOptions;
import cece.com.bannerlib.transformer.ScaleInTransformer;
import cece.com.bannerlib.view.ChildViewPager;
import cece.com.bannerlib.view.PointRoundDrawable;

public class RollViewPage implements OnPageChangeListener {
    private static final int MESSAGE_WHAT_NEXT_PAGER = 232;
    private ChildViewPager viewPager;//子ViewPager
    private RelativeLayout relativeLayout;//这是要ViewPager的父控件
    private Context mContext;
    private boolean isShowDot;//是否显示轮播小点
    private Mode mode;//轮播点的位置
    private LinearLayout layoutDot;//轮播的小点父布局
    private boolean isAutoCirculation;//是否开始轮播
    private List banList;//图片地址和View混合集合
    private OnItemViewClickListener onItemViewClickListener;//ViewPager的条目点击事件回调
    private GradientDrawable selectorBg, normalBg;
    private int dotNormalColor;//小圆点正常颜色
    private int dotSelectorColor;//小圆点选中颜色
    private int dotSize;//小圆点尺寸
    private boolean isRepeat;//是否无限循环模式
    private int circulationTime;//切换时间
    private boolean isNotInterceptTouch;//是否不阻断触摸事件  当条目有子条目需要点击的时候,设置为阻断
    boolean isCardMode;
    private CountDownHandler handler;
    private int padding;
    private OnPagerAdapterBinerListener onBinerListener;
    private ViewpagerConfig viewpagerConfig;
    private DotLayoutConfig dotLayoutConfig;
    private OnPageChangeListener onPageChangeListener;

    public static RollViewPage build(BannerBuilder builder) {
        return new RollViewPage(builder);
    }

    private RollViewPage(BannerBuilder builder) {
        this.relativeLayout = builder.getRelativeLayout();
        this.isNotInterceptTouch = builder.isNotInterceptTouch();
        this.banList = builder.getBanList();
        this.mContext = builder.getContext();
        this.onItemViewClickListener = builder.getOnItemViewClickListener();
        this.viewpagerConfig = builder.getViewpagerConfig();
        this.dotLayoutConfig = builder.getDotLayoutConfig();
        this.onBinerListener = builder.getOnBinerListener();
        this.onPageChangeListener = builder.getOnPageChangeListener();
        //获取是否轮询配置
        CirculationModeOptions circulationModeOptions = builder.getCirculationModeOptions();
        if (circulationModeOptions != null) {
            this.isAutoCirculation = circulationModeOptions.isAutoCirculation();
            this.circulationTime = circulationModeOptions.getCirculationTime();
            this.isRepeat = circulationModeOptions.isRepeat();
        }

        //获取小圆点设置
        DotOptions dotOptions = builder.getDotOptions();
        if (dotOptions != null) {
            this.mode = dotOptions.getMode();
            this.isShowDot = dotOptions.isShowDot();
            this.dotSize = dotOptions.getDotSize();
            this.dotSelectorColor = dotOptions.getDotSelectorColor();
            this.dotNormalColor = dotOptions.getDotNormalColor();
        }
        //卡片模式配置
        CardModeOptions cardViewOptions = builder.getCardViewOptions();
        if (cardViewOptions != null) {
            this.isCardMode = cardViewOptions.isCardMode();
            this.padding = cardViewOptions.getPadding();
            if (this.padding == 0) {
                this.padding = dip2px(15);
            }

        }
        initDotBg();
        init();
        start();
    }


    /**
     * 初始化小圆点背景
     */
    private void initDotBg() {
        selectorBg = new PointRoundDrawable(dotSelectorColor);
        normalBg = new PointRoundDrawable(dotNormalColor);
    }


    private void init() {
        relativeLayout.removeAllViews();
        if (banList == null || banList.size() == 0) {
            return;
        } else {
            initViewpager();
            initDot(banList.size());
            initViewpagerListener();
        }
    }

    private void initViewpagerListener() {
        initAdapter();
        if (isCardMode) {//卡片式的
            viewPager.setPadding(padding, 0, padding, 0);
            viewPager.setClipToPadding(false);
            viewPager.setClipChildren(false);
            viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
            viewPager.setPageTransformer(false, new ScaleInTransformer());
        }

        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(this);
        if (onPageChangeListener != null) {
            viewPager.addOnPageChangeListener(onPageChangeListener);
        }
        if (viewpagerConfig != null) {
            viewpagerConfig.configViewpager(viewPager);
        }
    }

    private void initAdapter() {
        MyPagerAdapter adapter = new RollPagerAdapter(mContext, isRepeat, banList);
        adapter.setOnItemViewClickListener(onItemViewClickListener);
        if (onBinerListener == null) {
            if (isCardMode) {
                onBinerListener = new DefaultCardBinderListener(mContext, dip2px(10));
            } else {
                onBinerListener = new DefaultBinderListener(mContext);
            }
        }
        adapter.setOnBinerListener(onBinerListener);
        viewPager.setAdapter(adapter);
    }

    private void initViewpager() {
        if (viewPager == null) {
            viewPager = new ChildViewPager(mContext, isNotInterceptTouch);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewPager.setLayoutParams(lp);
            handler = new CountDownHandler(viewPager, banList.size(), isRepeat);
        }
        relativeLayout.addView(viewPager);
    }

    /**
     * 初始化小圆点
     *
     * @param count
     */
    private void initDot(int count) {
        if (isShowDot) {//控制是否显示小点
            initDotLayout();
            if (count > 1) {
                if (mode.equals(Mode.left)) {
                    layoutDot.setGravity(Gravity.LEFT);
                } else if (mode.equals(Mode.middle)) {
                    layoutDot.setGravity(Gravity.CENTER);
                } else if (mode.equals(Mode.right)) {
                    layoutDot.setGravity(Gravity.RIGHT);
                }
                for (int i = 0; i < count; i++) {
                    ImageView iv_dot = new ImageView(mContext);
                    LinearLayout.LayoutParams lp_dot = new LinearLayout.LayoutParams(dotSize, dotSize);
                    lp_dot.setMargins(dotSize, 0, 0, 0);
                    iv_dot.setLayoutParams(lp_dot);
                    iv_dot.setBackground(i == 0 ? selectorBg : normalBg);
                    layoutDot.addView(iv_dot);
                }
                relativeLayout.addView(layoutDot);
            }
        }
    }

    /**
     * 小圆点父布局
     */
    private void initDotLayout() {
        if (layoutDot == null) {
            layoutDot = new LinearLayout(mContext);
        } else {
            layoutDot.removeAllViews();
        }
        layoutDot.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(RelativeLayout.ABOVE);
        lp.setMargins(0, 0, 0, dip2px(5));
        layoutDot.setLayoutParams(lp);
        if (dotLayoutConfig != null) {
            dotLayoutConfig.configDotLayout(layoutDot);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int imageSize = banList.size();
        if (isShowDot) {
            for (int i = 0; i < layoutDot.getChildCount(); i++) {
                if (isRepeat) {
                    layoutDot.getChildAt(i).setBackground(i == position % imageSize ? selectorBg : normalBg);
                } else {
                    layoutDot.getChildAt(i).setBackground(i == position ? selectorBg : normalBg);
                }
            }
        }
        cancel();
        start();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case 1://滑动
                onPause();
                break;
            case 0://滑动完毕
                onResume();
                break;
            default:
                break;
        }
    }

    //停止
    public void onDestory() {
        isAutoCirculation = false;
        banList.clear();
        handler.removeMessages(MESSAGE_WHAT_NEXT_PAGER);
    }

    //暂停
    public void onPause() {
        cancel();
    }

    //重启
    public void onResume() {
        start();
    }

    private void start() {
        if (isAutoCirculation)
            handler.sendEmptyMessageDelayed(MESSAGE_WHAT_NEXT_PAGER, circulationTime);
    }

    private void cancel() {
        handler.removeMessages(MESSAGE_WHAT_NEXT_PAGER);
    }

    private static class CountDownHandler extends Handler {
        private Reference<ViewPager> reference;

        private int count;
        private boolean isRepeat;

        public CountDownHandler(@NonNull ViewPager pager, int count, boolean isRepeat) {
            reference = new WeakReference<>(pager);
            this.count = count;
            this.isRepeat = isRepeat;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_WHAT_NEXT_PAGER:
                    if (reference != null) {
                        ViewPager pager = reference.get();
                        if (pager != null) {
                            int index = pager.getCurrentItem();
                            index++;
                            if (!isRepeat) {//循环模式
                                if (index == count) {
                                    index = 0;
                                }
                            }
                            pager.setCurrentItem(index, true);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * dip转化成px
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
