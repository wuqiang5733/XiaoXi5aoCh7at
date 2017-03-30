package org.xuxiaoxiao.www.xiaoxiao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder {
    private TextView mAuthorTextView;
    private TextView mMessageTextView;

    private ChatMessage mChatMessage;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        mAuthorTextView = (TextView) itemView.findViewById(R.id.author);
        mMessageTextView = (TextView) itemView.findViewById(R.id.message);
    }

    public void bind(ChatMessage chatmessage) {
        mChatMessage = chatmessage;
        mAuthorTextView.setText(chatmessage.getAuthor());
        mMessageTextView.setText(chatmessage.getMessage());

    }
}
/**
 *
 *
 private class ChatMessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
 private TextView mAuthorTextView;
 private TextView mMessageTextView;
 private ChatMessage mChatMessage;

 public ChatMessageHolder(LayoutInflater inflater, ViewGroup parent) {
 super(inflater.inflate(R.layout.chat_message, parent, false));

 mAuthorTextView = (TextView) itemView.findViewById(R.id.author);
 mMessageTextView = (TextView) itemView.findViewById(R.id.message);
 itemView.setOnClickListener(this);
 }

 public void bind(ChatMessage chatmessage) {
 mChatMessage = chatmessage;
 mAuthorTextView.setText(chatmessage.getAuthor());
 mMessageTextView.setText(chatmessage.getMessage());

 }
 @Override
 public void onClick(View view) {
 Toast.makeText(getActivity(), mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();
 }

 }
 */
