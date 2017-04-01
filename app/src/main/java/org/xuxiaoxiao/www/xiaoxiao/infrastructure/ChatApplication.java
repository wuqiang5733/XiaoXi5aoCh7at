package org.xuxiaoxiao.www.xiaoxiao.infrastructure;


import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;


/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatApplication extends android.app.Application {

    private BeatBox mBeatBox;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: change this to your own Wilddog URL

        WilddogOptions wilddogOptions = new WilddogOptions.Builder().setSyncUrl("https://myfirstandroidapp2017.wilddogio.com").build();
        WilddogApp wilddogApp = WilddogApp.initializeApp(this,wilddogOptions);

        mBeatBox = new BeatBox(this);
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }
}
