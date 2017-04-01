package org.xuxiaoxiao.www.xiaoxiao.infrastructure;


import android.util.SparseArray;

import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

import java.util.Random;


/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatApplication extends android.app.Application {

    private BeatBox mBeatBox;
    private User user;
    private SparseArray<Object> sparseArray;
    Random rand = new Random();


    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: change this to your own Wilddog URL

        WilddogOptions wilddogOptions = new WilddogOptions.Builder().setSyncUrl("https://myfirstandroidapp2017.wilddogio.com").build();
        WilddogApp wilddogApp = WilddogApp.initializeApp(this,wilddogOptions);

        mBeatBox = new BeatBox(this);
        user = new User(String.valueOf(rand.nextInt(4000)));
        sparseArray = new SparseArray<>();
        sparseArray.put(0,mBeatBox);
        sparseArray.put(1,user);
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }

    public User getUser() {
        return user;
    }

    public SparseArray<Object> getSparseArray(){
        return sparseArray;
    }
}
