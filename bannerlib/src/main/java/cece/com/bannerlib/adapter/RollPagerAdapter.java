package cece.com.bannerlib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liugongce on 2017/11/6.
 */

public class RollPagerAdapter extends MyPagerAdapter {
    private boolean isCirMode;
    private List bannerList;

    public RollPagerAdapter(Context context, boolean isCirMode, List bannerList) {
        super(context);
        this.isCirMode = isCirMode;
        this.bannerList = bannerList;
    }

    @Override
    public int getCount() {
        return isCirMode ? Integer.MAX_VALUE : (bannerList == null ? 0 : bannerList.size());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (isCirMode) {
            position %= bannerList.size();
        }
        if (onBinerListener != null) {
            final View view = onBinerListener.bind(container, bannerList, position);
            final int finalPosition = position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemClick(view, finalPosition);
                    }
                }
            });
            return view;
        }
        return null;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
