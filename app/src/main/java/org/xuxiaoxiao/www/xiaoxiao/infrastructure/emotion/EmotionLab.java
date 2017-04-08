package org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by WuQiang on 2017/4/8.
 */

public class EmotionLab {

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
        ArrayList<Emotion> emotionTemp = new ArrayList<>();

        for (String name : seriesName) {
            for (int i = 0; i < eachSeriesEmotionNum; i++) {
                emotionTemp.add(new Emotion(name + i));
            }
            emotionSeries.add(new EmotionSeries(name, 12, emotionTemp));
        }
    }

    public ArrayList<EmotionSeries> getEmotionSeries() {
        return emotionSeries;
    }

}

