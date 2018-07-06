package cece.com.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cece.com.bannerlib.Mode;
import cece.com.bannerlib.RollViewPage;
import cece.com.bannerlib.config.BannerBuilder;
import cece.com.bannerlib.config.CardModeOptions;
import cece.com.bannerlib.config.CirculationModeOptions;
import cece.com.bannerlib.config.DotOptions;

public class MainActivity extends AppCompatActivity {
    final List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("http://www.fuhaodq.com/d/file/201706/16/2uucyj1vlhyvjjr2779.jpg");
        list.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=240148720,3695524497&fm=11&gp=0.jpg");
        list.add("http://dynamic-image.yesky.com/740x-/uploadImages/2016/058/45/F5GG44HP48QH.jpg");
        list.add("http://pic.gansudaily.com.cn/0/10/41/83/10418360_999008.jpg");
        RollViewPage.build(new BannerBuilder(this)
                .setBanList(list)
                .setNotInterceptTouch(true)
                .setRelativeLayout((RelativeLayout) findViewById(R.id.rl_banner1))
                .setDotOptions(new DotOptions().setShowDot(true).setMode(Mode.middle)
                        .setDotSize(dip2px(this, 10)))
                .setCirculationModeOptions(new CirculationModeOptions()
                        .setAutoCirculation(true)
                        .setRepeat(false)));

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
    }
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
