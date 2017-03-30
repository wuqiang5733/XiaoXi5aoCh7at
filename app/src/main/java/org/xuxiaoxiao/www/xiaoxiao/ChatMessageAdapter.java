package org.xuxiaoxiao.www.xiaoxiao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    private Activity activity;
    private List<ChatMessage> chatMessages;

    public ChatMessageAdapter(Activity activity, List<ChatMessage> chatMessages){
        this.activity = activity;
        this.chatMessages = chatMessages;

    }
    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.chat_message,parent,false);
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
//        chatMessages.get(position);
        holder.bind(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
}
