package org.xuxiaoxiao.www.xiaoxiao.chatConfig;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xuxiaoxiao.www.xiaoxiao.R;

/**
 * Created by WuQiang on 2017/3/31.
 */

public class ChatConfigFragment extends Fragment {
    public static ChatConfigFragment newInstance(){
        return new ChatConfigFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_config,container,false);
        return view;
    }
}
