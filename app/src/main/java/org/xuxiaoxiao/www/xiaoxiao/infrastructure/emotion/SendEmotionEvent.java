package org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion;

/**
 * Created by WuQiang on 2017/4/9.
 */

public class SendEmotionEvent {

    private String emotionName;

    public SendEmotionEvent(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getEmotionName() {
        return emotionName;
    }
}
