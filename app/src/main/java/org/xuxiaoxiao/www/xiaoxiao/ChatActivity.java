package org.xuxiaoxiao.www.xiaoxiao;

import android.support.v4.app.Fragment;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ChatFragment.newInstance();
    }
}
