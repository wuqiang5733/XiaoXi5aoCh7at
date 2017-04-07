package org.xuxiaoxiao.www.xiaoxiao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.User;

//import static org.xuxiaoxiao.www.xiaoxiao.R.id.author;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView msg;

    private TextView msgID;

    /////////////
    private LinearLayout layout;

    private ChatMessage mChatMessage;
    private Activity activity;
    private User user;


    public ChatMessageViewHolder(View itemView, Activity activity, User user) {
        super(itemView);
        this.activity = activity;
        this.user = user;
        msg = (TextView) itemView.findViewById(R.id.msg);

        msgID = (TextView) itemView.findViewById(R.id.msgid);

        layout = (LinearLayout) itemView.findViewById(R.id.layout);

        itemView.setOnClickListener(this);
        ////////////////////////

    }

    public void bind(ChatMessage chatmessage) {
        mChatMessage = chatmessage;
        boolean isMine = mChatMessage.getAuthor() != null && mChatMessage.getAuthor().equals(user.getName());

        msg.setText(mChatMessage.getMessage());
        msgID.setText(mChatMessage.getMessageID());
//        TextView test = new TextView(activity);
//        test.setText("武强");

        layout.setBackgroundResource(isMine ? R.drawable.message_right : R.drawable.message_left);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        if (!isMine) {
            params.gravity = Gravity.LEFT;
        } else {
            params.gravity = Gravity.RIGHT;
        }
        layout.setLayoutParams(params);
//        layout.addView(test);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(activity, mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();

    }
}

/**
 * layout.setBackgroundResource(R.drawable.message_right);
 *
 * @Override protected void populateView(Context context, MessageViewHolder holder, Chat chat) {
 * // 设置发送者
 * String author = chat.getAuthor();
 * // 根据是不是自己发的，改变 发送者 颜色
 * if (author != null && author.equals(mUsername)) {
 * holder.rightLayout.setVisibility(View.VISIBLE);
 * holder.leftLayout.setVisibility(View.GONE);
 * holder.rightMsg.setText(chat.getMessage());
 * } else {
 * // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
 * holder.leftLayout.setVisibility(View.VISIBLE);
 * holder.rightLayout.setVisibility(View.GONE);
 * holder.leftMsg.setText(chat.getMessage());
 * }
 * }
 * <p>
 * <p>
 * <p>
 * private class ChatMessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
 * private TextView mAuthorTextView;
 * private TextView mMessageTextView;
 * private ChatMessage mChatMessage;
 * <p>
 * public ChatMessageHolder(LayoutInflater inflater, ViewGroup parent) {
 * super(inflater.inflate(R.layout.chat_message, parent, false));
 * <p>
 * mAuthorTextView = (TextView) itemView.findViewById(R.id.author);
 * mMessageTextView = (TextView) itemView.findViewById(R.id.message);
 * itemView.setOnClickListener(this);
 * }
 * <p>
 * public void bind(ChatMessage chatmessage) {
 * mChatMessage = chatmessage;
 * mAuthorTextView.setText(chatmessage.getAuthor());
 * mMessageTextView.setText(chatmessage.getMessage());
 * <p>
 * }
 * @Override public void onClick(View view) {
 * Toast.makeText(getActivity(), mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();
 * }
 * <p>
 * }
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
 @Override public void onClick(View view) {
 Toast.makeText(getActivity(), mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();
 }

 }
 */
