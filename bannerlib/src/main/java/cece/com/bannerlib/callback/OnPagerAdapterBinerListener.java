package cece.com.bannerlib.callback;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liugongce on 2017/11/29.
 */

public interface OnPagerAdapterBinerListener {
    View bind(ViewGroup container, List bannerList, int position);
}
