package cece.com.bannerlib.config;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import java.util.List;

import cece.com.bannerlib.callback.DotLayoutConfig;
import cece.com.bannerlib.callback.OnItemViewClickListener;
import cece.com.bannerlib.callback.OnPagerAdapterBinerListener;
import cece.com.bannerlib.callback.ViewpagerConfig;

/**
 * Created by liugongce on 2017/11/6.
 */

public class BannerBuilder {
    private Context context;
    private RelativeLayout relativeLayout;
    private OnItemViewClickListener onItemViewClickListener;
    private ViewpagerConfig viewpagerConfig;
    private boolean isNotInterceptTouch;
    private List banList;
    private CirculationModeOptions circulationModeOptions;
    private DotOptions dotOptions;
    private CardModeOptions cardViewOptions;
    private DotLayoutConfig dotLayoutConfig;
    private OnPagerAdapterBinerListener onBinerListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public BannerBuilder(Context context) {
        this.context = context;
    }

    public BannerBuilder setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
        return this;
    }

    public BannerBuilder setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
        return this;
    }

    public BannerBuilder setNotInterceptTouch(boolean notInterceptTouch) {
        isNotInterceptTouch = notInterceptTouch;
        return this;
    }

    public BannerBuilder setBanList(List banList) {
        this.banList = banList;
        return this;
    }

    public BannerBuilder setCirculationModeOptions(CirculationModeOptions circulationModeOptions) {
        this.circulationModeOptions = circulationModeOptions;
        return this;
    }

    public BannerBuilder setDotOptions(DotOptions dotOptions) {
        this.dotOptions = dotOptions;
        return this;
    }

    public BannerBuilder setCardViewOptions(CardModeOptions cardViewOptions) {
        this.cardViewOptions = cardViewOptions;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public OnItemViewClickListener getOnItemViewClickListener() {
        return onItemViewClickListener;
    }

    public boolean isNotInterceptTouch() {
        return isNotInterceptTouch;
    }

    public List getBanList() {
        return banList;
    }

    public CirculationModeOptions getCirculationModeOptions() {
        return circulationModeOptions;
    }

    public DotOptions getDotOptions() {
        return dotOptions;
    }

    public CardModeOptions getCardViewOptions() {
        return cardViewOptions;
    }

    public ViewpagerConfig getViewpagerConfig() {
        return viewpagerConfig;
    }

    public BannerBuilder setViewpagerConfig(ViewpagerConfig viewpagerConfig) {
        this.viewpagerConfig = viewpagerConfig;
        return this;
    }

    public DotLayoutConfig getDotLayoutConfig() {
        return dotLayoutConfig;
    }

    public BannerBuilder setDotLayoutConfig(DotLayoutConfig dotLayoutConfig) {
        this.dotLayoutConfig = dotLayoutConfig;
        return this;
    }

    public OnPagerAdapterBinerListener getOnBinerListener() {
        return onBinerListener;
    }

    public BannerBuilder setOnBinerListener(OnPagerAdapterBinerListener onBinerListener) {
        this.onBinerListener = onBinerListener;
        return this;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public BannerBuilder setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        return this;
    }
}
