package org.xuxiaoxiao.www.xiaoxiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatFragment extends Fragment {
    private RecyclerView mChatRecyclerView;

    private SyncReference mWilddogRef;
    private ValueEventListener mConnectedListener;
    private ChatMessageAdapter chatMessageAdapter;
    private EditText messageInputText;

    private BeatBox mBeatBox;


    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup our Wilddog mWilddogRef
        mWilddogRef = WilddogSync.getInstance().getReference().child("chat");
        // 初始化声音方面
        mBeatBox = new BeatBox(getActivity());
        // 生成声音


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mChatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        chatMessageAdapter = new ChatMessageAdapter(getActivity(), mWilddogRef.limitToLast(10),mBeatBox.getSounds(),mBeatBox);
        mChatRecyclerView.setAdapter(chatMessageAdapter);

        chatMessageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mChatRecyclerView.scrollToPosition((chatMessageAdapter.getItemCount() - 1));
            }
        });

        // 指示网络的连接状态
        mConnectedListener = mWilddogRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(getActivity(), "连接到网络", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "已从网络断开", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(SyncError wilddogError) {
                // No-op
            }
        });
        // 输入信息发送
        messageInputText = (EditText) view.findViewById(R.id.messageInput);

        messageInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        view.findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        return view;
    }

    private void sendMessage() {

        String input = messageInputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            ChatMessage chat = new ChatMessage(input, "WQ");
            // Create a new, auto-generated child of that chat location, and save our chat data there
            mWilddogRef.push().setValue(chat);
            messageInputText.setText("");
        }
    }



        @Override
    public void onStop() {
        super.onStop();
        mWilddogRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
//        chatMessageAdapter.cleanup();
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        mWilddogRef.getRoot().child(".info/connected").addChildEventListener();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }
}
