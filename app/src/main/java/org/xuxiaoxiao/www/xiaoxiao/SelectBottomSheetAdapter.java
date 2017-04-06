package org.xuxiaoxiao.www.xiaoxiao;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by WuQiang on 2017/4/5.
 */

public class SelectBottomSheetAdapter extends FragmentPagerAdapter {
    Context context;

    public SelectBottomSheetAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return (SelectBottomSheetFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (SelectBottomSheetFragment.getTitle(context,position));
    }
}
