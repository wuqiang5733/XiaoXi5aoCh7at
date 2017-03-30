package org.xuxiaoxiao.www.xiaoxiao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//import static org.xuxiaoxiao.www.xiaoxiao.R.id.author;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView leftMsg;
    private TextView rightMsg;

    /////////////
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;

    private ChatMessage mChatMessage;
    private Activity activity;



    public ChatMessageViewHolder(View itemView,Activity activity) {
        super(itemView);
        this.activity = activity;
        leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
        rightMsg = (TextView) itemView.findViewById(R.id.right_msg);

        leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
        rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);

        itemView.setOnClickListener(this);
        ////////////////////////

    }

    public void bind(ChatMessage chatmessage) {
        mChatMessage = chatmessage;

        if (mChatMessage.getAuthor() != null && mChatMessage.getAuthor().equals("WQ")) {
            rightLayout.setVisibility(View.VISIBLE);
            leftLayout.setVisibility(View.GONE);
            rightMsg.setText(mChatMessage.getMessage());
        } else {
            // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            leftLayout.setVisibility(View.VISIBLE);
            rightLayout.setVisibility(View.GONE);
            leftMsg.setText(mChatMessage.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(activity, mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();

    }
}

/**
 *
 *     @Override
protected void populateView(Context context, MessageViewHolder holder, Chat chat) {
// 设置发送者
String author = chat.getAuthor();
// 根据是不是自己发的，改变 发送者 颜色
if (author != null && author.equals(mUsername)) {
holder.rightLayout.setVisibility(View.VISIBLE);
holder.leftLayout.setVisibility(View.GONE);
holder.rightMsg.setText(chat.getMessage());
} else {
// 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
holder.leftLayout.setVisibility(View.VISIBLE);
holder.rightLayout.setVisibility(View.GONE);
holder.leftMsg.setText(chat.getMessage());
}
}
 */
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
