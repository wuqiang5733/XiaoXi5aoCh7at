package org.xuxiaoxiao.www.xiaoxiao;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
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
    private Button mPopupBottomSheetDialog;
    //    private SelectBottomSheetFragment mBottomSheetDialog;
    private SelectBottomSheetContainter mSelectBottomSheetContainter;
    private LinearLayout mTopPanel;

    private boolean layoutToggle = false;
    private int mHeyBoardHeight = 0;
    private FrameLayout.LayoutParams params;


    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
        ViewParent parentView = view.getParent();
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 虚拟键盘是否打开，用下面这个方法可以得知虚拟键盘的高度
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                view.getWindowVisibleDisplayFrame(r);

                mHeyBoardHeight = view.getRootView().getHeight() - (r.bottom - r.top);
                if (mHeyBoardHeight > 500) { // if more than 100 pixels, its probably a keyboard...
                    Log.d("WQ_ObseerverKeyHeight",String.valueOf(mHeyBoardHeight));
                    Log.d("WQ_ObserverRootView",String.valueOf(view.getRootView().getHeight()));
//                    Log.d("WQ_ViewTreeObserver",String.valueOf(heightDiff));
                    params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    params.topMargin -= 50;
//                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        mChatRecyclerView = (RecyclerView) view.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mPopupBottomSheetDialog = (Button) view.findViewById(R.id.popupBottomSheetDialog);
//        mTopPanel = (LinearLayout) view.findViewById(R.id.messageTopPanel);
//        params = (RelativeLayout.LayoutParams) mTopPanel.getLayoutParams();

        chatMessageAdapter = new ChatMessageAdapter(getActivity(), mWilddogRef.limitToLast(10), application);
        mChatRecyclerView.setAdapter(chatMessageAdapter);


//        // 添加可折叠部分
//        final ExpandableRelativeLayout expandableLayout
//                = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout);
//        mPopupBottomSheetDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // toggle expand, collapse
//
//                 params = (RelativeLayout.LayoutParams) mTopPanel.getLayoutParams();
//                if (layoutToggle) {
//                    params.topMargin += 140;
//
//                } else {
//                    params.topMargin -= 140;
//
//                }
//                layoutToggle = !layoutToggle;
//                mTopPanel.requestLayout();
//                expandableLayout.toggle();
////                view.invalidate();
////                mSelectBottomSheetContainter = new SelectBottomSheetContainter();
////                mSelectBottomSheetContainter.show(getFragmentManager(),"12345");
////                mBottomSheetDialog = new SelectBottomSheetFragment(getActivity());
////                mBottomSheetDialog.setContentView(R.layout.fragment_bottom_sheet);
////                mBottomSheetDialog.show();
//            }
//        });

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

//        messageInputText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                // 此处为得到焦点时的处理内容
//                    params.topMargin -= mHeyBoardHeight;
//                } else {
//                // 此处为失去焦点时的处理内容
//                }
//            }
//        });

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
}
