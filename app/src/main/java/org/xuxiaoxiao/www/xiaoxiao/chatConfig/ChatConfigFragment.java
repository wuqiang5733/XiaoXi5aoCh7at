package org.xuxiaoxiao.www.xiaoxiao.chatConfig;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import org.xuxiaoxiao.www.xiaoxiao.R;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.BaseFragment;

/**
 * Created by WuQiang on 2017/3/31.
 */

public class ChatConfigFragment extends BaseFragment {
    private Switch soundConfig;

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

        soundConfig = (Switch) view.findViewById(R.id.soundConfig);

        soundConfig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    // 这个要用永久的方法保存下来，要不不起作用
                    // 开启switch，设置提示信息
                    beatBox.release();

                } else {
                    // 关闭swtich，设置提示信息
                    beatBox.release();

                }
            }
        });

        return view;
    }
}