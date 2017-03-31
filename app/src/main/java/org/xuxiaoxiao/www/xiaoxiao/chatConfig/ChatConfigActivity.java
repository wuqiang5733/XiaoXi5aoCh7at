package org.xuxiaoxiao.www.xiaoxiao.chatConfig;

import android.support.v4.app.Fragment;

import org.xuxiaoxiao.www.xiaoxiao.SingleFragmentActivity;

/**
 * Created by WuQiang on 2017/3/31.
 */

public class ChatConfigActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return ChatConfigFragment.newInstance();
    }
}
