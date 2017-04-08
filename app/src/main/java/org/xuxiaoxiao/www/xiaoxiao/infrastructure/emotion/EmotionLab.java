package org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by WuQiang on 2017/4/8.
 */

public class EmotionLab implements Parcelable {

    private static EmotionLab emotionLab;
    private ArrayList<EmotionSeries> emotionSeries = new ArrayList<>();
    private static String[] seriesName = {"First", "Second", "Third", "Fourth", "Fifth"};
    private static int eachSeriesEmotionNum = 12;

    public static EmotionLab getEmotionLab(Context context) {
        if (emotionLab == null) {
            emotionLab = new EmotionLab(context);
        }
        return emotionLab;
    }

    private EmotionLab(Context context) {

     // 那一个数组，原来在这儿
        for (String name : seriesName) {
            ArrayList<Emotion> emotionTemp = new ArrayList<>();  // 现在在这儿了
            for (int i = 0; i < eachSeriesEmotionNum; i++) {
                emotionTemp.add(new Emotion(name + i));
//                Log.d("WQ",name);
            }
//            Log.d("WQ",name);

            emotionSeries.add(new EmotionSeries(name, eachSeriesEmotionNum, emotionTemp));
        }
//        Log.d("WQWQ",emotionSeries.get(3).getEmotions().get(8).getDescription());
    }

    /**
     * Log.d("WQ",emotionLab.getEmotionSeries().get(3).getEmotions().get(6).getDescription());
     * Log.d("WQ",emotionLab.getEmotionSeries().get(3).getSeriesName());
     *
     * @return
     */

    public ArrayList<EmotionSeries> getEmotionSeries() {
        return emotionSeries;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.emotionSeries);
    }

    protected EmotionLab(Parcel in) {
        this.emotionSeries = in.createTypedArrayList(EmotionSeries.CREATOR);
    }

    public static final Parcelable.Creator<EmotionLab> CREATOR = new Parcelable.Creator<EmotionLab>() {
        @Override
        public EmotionLab createFromParcel(Parcel source) {
            return new EmotionLab(source);
        }

        @Override
        public EmotionLab[] newArray(int size) {
            return new EmotionLab[size];
        }
    };
}

