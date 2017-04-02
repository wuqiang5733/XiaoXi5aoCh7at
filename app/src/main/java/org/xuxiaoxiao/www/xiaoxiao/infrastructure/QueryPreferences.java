package org.xuxiaoxiao.www.xiaoxiao.infrastructure;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by WuQiang on 2017/4/1.
 */

public class QueryPreferences {
    private static final String PREF_IS_ALARM_ON = "isAlarmOn";
    private static final String PREF_USER_NAME = "userName";

    public static boolean isAlarmOn(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_IS_ALARM_ON,false);
    }

    public static void setAlarmOn(Context context, boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_IS_ALARM_ON,isOn)
                .apply();
    }

    public static String getUserName (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_USER_NAME,"tre");
    }

    public static void setUserName(Context context, String userName){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_USER_NAME,userName)
                .apply();
    }
}
