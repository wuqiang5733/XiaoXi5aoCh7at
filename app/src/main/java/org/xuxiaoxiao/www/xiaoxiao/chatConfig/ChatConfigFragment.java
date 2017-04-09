package org.xuxiaoxiao.www.xiaoxiao.chatConfig;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import org.greenrobot.eventbus.EventBus;
import org.xuxiaoxiao.www.xiaoxiao.R;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.BaseFragment;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.SendEmotionEvent;

/**
 * Created by WuQiang on 2017/3/31.
 */

public class ChatConfigFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    private ToggleButton soundConfig;
    private EditText userNameEditText;
    private String userName;

    public static ChatConfigFragment newInstance() {
        return new ChatConfigFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_config, container, false);
        soundConfig = (ToggleButton) view.findViewById(R.id.soundConfig);
        soundConfig.setOnCheckedChangeListener(this);
        soundConfig.setChecked(application.isAlarmOn ? true : false);
        userNameEditText = (EditText) view.findViewById(R.id.userEditText);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userNameEditText.getText() != null) {
            // 设置用户名
            userName = userNameEditText.getText().toString();
            user.setName(userName);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.soundConfig) {
            application.isAlarmOn = isChecked;
        }
    }


}
