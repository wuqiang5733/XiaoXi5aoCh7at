package org.xuxiaoxiao.www.xiaoxiao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.wilddog.client.ChildEventListener;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.Query;
import com.wilddog.client.SyncError;

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.ChatApplication;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.SoundPool;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {
    ///////////
    private Query mRef;
    private Class<ChatMessage> mModelClass;
    private List<ChatMessage> mModels;
    private List<String> mKeys;
    private ChildEventListener mListener;
    ////////////////////////////////

    private Activity activity;
//    private List<Sound> mSounds;
//    private BeatBox mBeatBox;
    private SparseArray<Object> sparseArray;
    private User user;
    private SoundPool soundPool;
    private ChatApplication application;
    // 播放声音生成随机数
    Random rand = new Random();

    public ChatMessageAdapter(Activity activity, Query mRef, SparseArray<Object> sparseArray) {
        this.activity = activity;

        /////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////
        this.mRef = mRef;
        this.sparseArray = sparseArray;
//        this.mSounds = sounds;
//        this.mBeatBox = beatbox;
//        this.mBeatBox = (BeatBox)sparseArray.get(0);
//        this.mSounds = mBeatBox.getSounds();
        this.user = (User)sparseArray.get(1);
        this.soundPool = (SoundPool) sparseArray.get(0);
        this.application = (ChatApplication) sparseArray.get(2);

        mModels = new ArrayList<>();
        mKeys = new ArrayList<String>();
        // Look for all child events. We will then map them to our own internal ArrayList, which backs ListView
        mListener = this.mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                ChatMessage model = (ChatMessage) dataSnapshot.getValue(ChatMessage.class);
                String key = dataSnapshot.getKey();

                // Insert into the correct location, based on previousChildName
                if (previousChildName == null) {
                    mModels.add(0, model);
                    mKeys.add(0, key);
                } else {
                    int previousIndex = mKeys.indexOf(previousChildName);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mModels.size()) {
                        mModels.add(model);
                        mKeys.add(key);
                    } else {
                        mModels.add(nextIndex, model);
                        mKeys.add(nextIndex, key);
                    }
                }
//                Log.d("WQ_ChatMessageAdapter", key);
                notifyDataSetChanged();
                if (application.isAlarmOn){
                    soundPool.playSoundDestroy();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // One of the mModels changed. Replace it in our list and name mapping
                String key = dataSnapshot.getKey();
                ChatMessage newModel = (ChatMessage) dataSnapshot.getValue(ChatMessage.class);
                int index = mKeys.indexOf(key);

                mModels.set(index, newModel);

                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                // A model was removed from the list. Remove it from our list and the name mapping
                String key = dataSnapshot.getKey();
                int index = mKeys.indexOf(key);

                mKeys.remove(index);
                mModels.remove(index);

                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                // A model changed position in the list. Update our list accordingly
                String key = dataSnapshot.getKey();
                ChatMessage newModel = (ChatMessage) dataSnapshot.getValue(ChatMessage.class);
                int index = mKeys.indexOf(key);
                mModels.remove(index);
                mKeys.remove(index);
                if (previousChildName == null) {
                    mModels.add(0, newModel);
                    mKeys.add(0, key);
                } else {
                    int previousIndex = mKeys.indexOf(previousChildName);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mModels.size()) {
                        mModels.add(newModel);
                        mKeys.add(key);
                    } else {
                        mModels.add(nextIndex, newModel);
                        mKeys.add(nextIndex, key);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(SyncError syncError) {
                Log.e("WilddogListAdapter", "Listen was cancelled, no more updates will occur");
            }

        });

        ////////////////////////////////////////////////////////////

    }

    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.chat_message, parent, false);
        return new ChatMessageViewHolder(view, activity, user);
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        holder.bind(mModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public void cleanup() {
        // We're being destroyed, let go of our mListener and forget about all of the mModels
        mRef.removeEventListener(mListener);
        mModels.clear();
        mKeys.clear();
    }
}
