package org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WuQiang on 2017/4/8.
 */

public class Emotion implements Parcelable {

    //    public static int mPageSum;     // 一共需要几个页面
//    private int mPageIndex;         // 在第几个页面上
//    private int mNumber;            // 一个页面内，有几个表情
    private String mDescription;    // 表情的文字描述，或者用户自定义的搜索关键字
//    private Image mImage;         // 它的图像
//    private URI mUri                  // 它的网络地址

    public Emotion(String mDescription) {
        this.mDescription = mDescription;
    }


    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDescription);
    }

    protected Emotion(Parcel in) {
        this.mDescription = in.readString();
    }

    public static final Parcelable.Creator<Emotion> CREATOR = new Parcelable.Creator<Emotion>() {
        @Override
        public Emotion createFromParcel(Parcel source) {
            return new Emotion(source);
        }

        @Override
        public Emotion[] newArray(int size) {
            return new Emotion[size];
        }
    };
}
