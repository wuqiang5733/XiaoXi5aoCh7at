package org.xuxiaoxiao.www.xiaoxiao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.User;


/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView msg;

    private TextView msgID;

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
    }

    public void bind(ChatMessage chatmessage) {
        mChatMessage = chatmessage;
        boolean isMine = mChatMessage.getAuthor() != null && mChatMessage.getAuthor().equals(user.getName());

        msg.setText(mChatMessage.getMessage());
        msgID.setText(mChatMessage.getMessageID());

        layout.setBackgroundResource(isMine ? R.drawable.message_right : R.drawable.message_left);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        if (!isMine) {
            params.gravity = Gravity.LEFT;
        } else {
            params.gravity = Gravity.RIGHT;
        }
        layout.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(activity, mChatMessage.getMessage(), Toast.LENGTH_SHORT).show();

    }
}

