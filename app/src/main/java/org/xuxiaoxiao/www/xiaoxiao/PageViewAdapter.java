package org.xuxiaoxiao.www.xiaoxiao;

/**
 * Created by WuQiang on 2017/4/8.
 */

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageViewAdapter extends FragmentPagerAdapter {
    Context context;

    public PageViewAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return (PageViewFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public String getPageTitle(int position) {
        return (PageViewFragment.getTitle(context, position));
    }

}