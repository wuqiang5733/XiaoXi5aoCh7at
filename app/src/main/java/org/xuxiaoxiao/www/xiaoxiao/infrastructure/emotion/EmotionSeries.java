package org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by WuQiang on 2017/4/8.
 */

public class EmotionSeries implements Parcelable {
    private String seriesName;  // 系列的名字
    private int motionNum;      // 系列下有几个表情
    private ArrayList<Emotion> emotions;  // 包含系列下所有表情的数组

    public EmotionSeries(String seriesName, int motionNum, ArrayList<Emotion> emotions) {
        this.seriesName = seriesName;
        this.motionNum = motionNum;
        this.emotions = emotions;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getMotionNum() {
        return motionNum;
    }

    public void setMotionNum(int motionNum) {
        this.motionNum = motionNum;
    }

    public ArrayList<Emotion> getEmotions() {
        return emotions;
    }

    public void setEmotions(ArrayList<Emotion> emotions) {
        this.emotions = emotions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.seriesName);
        dest.writeInt(this.motionNum);
        dest.writeTypedList(this.emotions);
    }

    protected EmotionSeries(Parcel in) {
        this.seriesName = in.readString();
        this.motionNum = in.readInt();
        this.emotions = in.createTypedArrayList(Emotion.CREATOR);
    }

    public static final Parcelable.Creator<EmotionSeries> CREATOR = new Parcelable.Creator<EmotionSeries>() {
        @Override
        public EmotionSeries createFromParcel(Parcel source) {
            return new EmotionSeries(source);
        }

        @Override
        public EmotionSeries[] newArray(int size) {
            return new EmotionSeries[size];
        }
    };
}
