package cece.com.bannerlib.config;

import android.support.v4.view.ViewPager;

/**
 * Created by liugongce on 2017/11/6.
 */

public class CardModeOptions {
    private boolean isCardMode;
    private int padding;
    private ViewPager.PageTransformer transformer;

    public CardModeOptions setCardMode(boolean cardMode) {
        this.isCardMode = cardMode;
        return this;
    }

    public CardModeOptions setTransformer(ViewPager.PageTransformer transformer) {
        this.transformer = transformer;
        return this;
    }

    public int getPadding() {
        return padding;
    }

    public CardModeOptions setPadding(int padding) {
        this.padding = padding;
        return this;
    }



    public boolean isCardMode() {
        return isCardMode;
    }

    public ViewPager.PageTransformer getTransformer() {
        return transformer;
    }
}
