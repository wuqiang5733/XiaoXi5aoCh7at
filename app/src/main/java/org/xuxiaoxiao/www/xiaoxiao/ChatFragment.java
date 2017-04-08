package org.xuxiaoxiao.www.xiaoxiao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;

import org.xuxiaoxiao.www.xiaoxiao.chatConfig.ChatConfigActivity;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.BaseFragment;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionSeries;

/**
 * Created by WuQiang on 2017/3/30.
 */

/**
 * 在 ChatMessage 当中添加一个属性需要改的地方：
 * 1：Model 也就是 ChatMessage 当中把这个属性加上
 * 2：与 Model 对应的视图，XML 文件需要改
 * 3：bind 方法需要改
 * 4：在发送信息的时候，这个新加的属性也要发送出去
 */

public class ChatFragment extends BaseFragment {
    private RecyclerView mChatRecyclerView;

    private SyncReference mWilddogRef;
    private ValueEventListener mConnectedListener;
    private ChatMessageAdapter chatMessageAdapter;
    private EditText messageInputText;

    private boolean layoutToggle = false;
    private int mHeyBoardHeight = 0;
    private FrameLayout.LayoutParams params;
    private Button mAddView;
    private LinearLayout mHiddenView;


    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EmotionSeries testtest;
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
/*
        String temp = emotionLab.getEmotionSeries().get(1).getEmotions().get(0).getDescription();
        int itemp = emotionLab.getEmotionSeries().get(0).getMotionNum();

        Log.d("WQ",temp);
        Log.d("WQ",String.valueOf(itemp));

       */

        // Setup our Wilddog mWilddogRef
        mWilddogRef = WilddogSync.getInstance().getReference().child("chat");
//        soundPool.playSoundDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.chat_conf, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 转到配置页面
            case R.id.chat_config:
//                Toast.makeText(getActivity(),"dddd",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChatConfigActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mChatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        chatMessageAdapter = new ChatMessageAdapter(getActivity(), mWilddogRef.limitToLast(10), application);
        mChatRecyclerView.setAdapter(chatMessageAdapter);

        mHiddenView = (LinearLayout) view.findViewById(R.id.hidden);
        mAddView = (Button) view.findViewById(R.id.add_view);
        mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 表情部分
                Log.d("WQ", "ShowHiddenViewButtonClick");
                hideKeyboard(view);
                mHiddenView.setVisibility((mHiddenView.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
                messageInputText.clearFocus();
                ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
                // 启动表情部分的 PageView
                pager.setAdapter(new PageViewAdapter(getActivity(), getChildFragmentManager(),emotionLab));
            }
        });


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

        messageInputText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            // 输入信息文本获得焦点时
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    mHiddenView.setVisibility(View.GONE);

                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

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
            // Create a new, auto-generated child of that chat location, and save our chat data there
            String key = mWilddogRef.push().getKey();
            ChatMessage chat = new ChatMessage(input, user.getName(), key);
//            Log.d("WQ_ChatFragment", key);
            mWilddogRef.child(key).setValue(chat);
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
//        beatBox.release();
//        Log.d("WQ_ChatFragment","onDestroy");
    }

    public void showKeyboard(View v) {
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(messageInputText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyboard(View v) {
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(messageInputText.getWindowToken(), 0);
    }
}
