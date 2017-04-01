package org.xuxiaoxiao.www.xiaoxiao.infrastructure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by WuQiang on 2017/4/1.
 */

public class BaseFragment extends Fragment {
    protected ChatApplication application;
    protected BeatBox beatBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ChatApplication) getActivity().getApplication();
        beatBox = application.getBeatBox();
    }
}
