package org.xuxiaoxiao.www.xiaoxiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WuQiang on 2017/4/5.
 */

public class SelectBottomSheetContainter extends BottomSheetDialogFragment {

    public SelectBottomSheetContainter() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.select_bottom_sheet_container, container);

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());

        return view;
    }

    private PagerAdapter buildAdapter() {
        return (new SelectBottomSheetAdapter(getContext(),getChildFragmentManager()));
//        return(new SelectBottomSheetAdapter(this, getFragmentManager()));
    }

}
