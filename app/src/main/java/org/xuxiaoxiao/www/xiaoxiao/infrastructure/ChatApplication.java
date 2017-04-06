package org.xuxiaoxiao.www.xiaoxiao.infrastructure;


import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

import java.util.Random;


/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatApplication extends android.app.Application {

    private User user;
//    private SparseArray<Object> sparseArray;
    Random rand = new Random();
    public static boolean isAlarmOn = true;
    private SoundPool soundPool;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: change this to your own Wilddog URL

        WilddogOptions wilddogOptions = new WilddogOptions.Builder().setSyncUrl("https://wuxu1314.wilddogio.com").build();
        WilddogApp wilddogApp = WilddogApp.initializeApp(this,wilddogOptions);

        user = new User(String.valueOf(rand.nextInt(4000)));
        // 注意这儿的顺序是相当重要的！！必须先得把 SoundPool 跟 User 初始化了，才给附给 sparseArray
        soundPool = new SoundPool(this);
//        sparseArray = new SparseArray<>();
//        sparseArray.put(0,soundPool);
//        sparseArray.put(1,user);
//        sparseArray.put(3,this);
    }



    public User getUser() {
        return user;
    }

//    public SparseArray<Object> getSparseArray(){
//        return sparseArray;
//    }

    public SoundPool getSoundPool() {
        return soundPool;
    }
}
