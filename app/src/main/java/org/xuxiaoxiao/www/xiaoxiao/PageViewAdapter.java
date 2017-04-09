package org.xuxiaoxiao.www.xiaoxiao;

/**
 * Created by WuQiang on 2017/4/8.
 */

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionLab;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionSeries;

import java.util.ArrayList;

public class PageViewAdapter extends FragmentPagerAdapter {
    Context context;
    private EmotionLab emotionLab;
    private ArrayList<EmotionSeries> emotionSeries;
//    private EventBus eventBus;


    public PageViewAdapter(Context context, FragmentManager fm,EmotionLab emotionLab) {
        super(fm);
        this.emotionLab = emotionLab;
        this.emotionSeries = emotionLab.getEmotionSeries();
//        this.eventBus = eventBus;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return (PageViewFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return emotionSeries.size();
    }

    @Override
    public String getPageTitle(int position) {
        // 传送表情包的名称
//        return (PageViewFragment.getTitle(context, position));
//        Log.d("WQ",emotionLab.getEmotionSeries().get(position).getEmotions().get(position).getDescription());
        return emotionSeries.get(position).getSeriesName();
    }

}