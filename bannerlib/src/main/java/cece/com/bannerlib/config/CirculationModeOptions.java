package cece.com.bannerlib.config;

/**
 * Created by liugongce on 2017/11/6.
 */

public class CirculationModeOptions {
    private boolean isAutoCirculation = true;
    private int circulationTime = 2000;
    private boolean isRepeat;


    public CirculationModeOptions setAutoCirculation(boolean autoCirculation) {
        isAutoCirculation = autoCirculation;
        return this;
    }

    public CirculationModeOptions setCirculationTime(int circulationTime) {
        this.circulationTime = circulationTime;
        return this;
    }

    public CirculationModeOptions setRepeat(boolean repeat) {
        isRepeat = repeat;
        return this;
    }

    public boolean isAutoCirculation() {
        return isAutoCirculation;
    }

    public int getCirculationTime() {
        return circulationTime;
    }

    public boolean isRepeat() {
        return isRepeat;
    }
}
