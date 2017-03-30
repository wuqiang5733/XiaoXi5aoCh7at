package org.xuxiaoxiao.www.xiaoxiao;

/**
 *  Created by WuQiang on 2017/3/29.
 *  Copyright © 2017年 WuQiang. All rights reserved.
*/
public class ChatMessage {

    private String message;
    private String author;

    // Required default constructor for Wilddog object mapping
    @SuppressWarnings("unused")
    private ChatMessage() {
    }

    ChatMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
