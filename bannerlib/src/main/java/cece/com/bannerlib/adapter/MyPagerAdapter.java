package cece.com.bannerlib.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;

import cece.com.bannerlib.callback.OnItemViewClickListener;
import cece.com.bannerlib.callback.OnPagerAdapterBinerListener;


/**
 * Created by liugongce on 2017/11/6.
 */

public abstract class MyPagerAdapter extends PagerAdapter {
    protected OnItemViewClickListener onItemViewClickListener;
    protected OnPagerAdapterBinerListener onBinerListener;
    protected Context mContext;

    public MyPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public OnPagerAdapterBinerListener getOnBinerListener() {
        return onBinerListener;
    }

    public void setOnBinerListener(OnPagerAdapterBinerListener onBinerListener) {
        this.onBinerListener = onBinerListener;
    }

    public OnItemViewClickListener getOnItemViewClickListener() {
        return onItemViewClickListener;
    }

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
