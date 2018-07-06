package cece.com.bannerlib.config;

import android.graphics.Color;

import cece.com.bannerlib.Mode;


/**
 * Created by liugongce on 2017/11/6.
 */

public class DotOptions {
    private Mode mode = Mode.right;
    private int dotNormalColor= Color.WHITE;
    private int dotSelectorColor= Color.RED;
    private int dotSize;
    private boolean isShowDot = true;

    public DotOptions setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public DotOptions setDotNormalColor(int dotNormalColor) {
        this.dotNormalColor = dotNormalColor;
        return this;
    }

    public DotOptions setDotSelectorColor(int dotSelectorColor) {
        this.dotSelectorColor = dotSelectorColor;
        return this;
    }

    public DotOptions setDotSize(int dotSize) {
        this.dotSize = dotSize;
        return this;
    }

    public DotOptions setShowDot(boolean showDot) {
        isShowDot = showDot;
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    public int getDotNormalColor() {
        return dotNormalColor;
    }

    public int getDotSelectorColor() {
        return dotSelectorColor;
    }

    public int getDotSize() {
        return dotSize;
    }

    public boolean isShowDot() {
        return isShowDot;
    }
}
