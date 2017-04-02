package org.xuxiaoxiao.www.xiaoxiao.infrastructure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Created by WuQiang on 2017/4/1.
 */

public class BaseFragment extends Fragment {
    protected ChatApplication application;
    protected User user;
    protected SparseArray<Object> sparseArray;
    protected SoundPool soundPool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (ChatApplication) getActivity().getApplication();
//        user = application.getUser();
        sparseArray = application.getSparseArray();
        soundPool = application.getSoundPool();
        user = application.getUser();
    }
}
