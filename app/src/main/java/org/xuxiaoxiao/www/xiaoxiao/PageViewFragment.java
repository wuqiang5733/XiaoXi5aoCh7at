package org.xuxiaoxiao.www.xiaoxiao;

/**
 * Created by WuQiang on 2017/4/8.
 */

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.BaseFragment;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.Emotion;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionLab;

import java.util.ArrayList;

public class PageViewFragment extends BaseFragment {
//public class PageViewFragment extends android.support.v4.app.Fragment {

    private static final String KEY_POSITION = "position";
    private static final String KEY_EMOTIONSERIES = "emotionSeries";
    private RecyclerView mImgRecyclerView;


    static PageViewFragment newInstance(int position, EmotionLab emotionLab) {

        PageViewFragment frag = new PageViewFragment();
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
        args.putParcelable(KEY_EMOTIONSERIES, emotionLab);
        frag.setArguments(args);
        return (frag);
    }
/*
    static String getTitle(Context ctxt, int position) {
        // 这是原来传送 表情包 名称的地方
        return ("+++");
//        return (String.format(ctxt.getString(R.string.hint), position + 1));
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editor, container, false);
        int position = getArguments().getInt(KEY_POSITION, -1);
        EmotionLab emotionLab = getArguments().getParcelable(KEY_EMOTIONSERIES);


        mImgRecyclerView = (RecyclerView) view.findViewById(R.id.img_recycler_view);
        mImgRecyclerView.setLayoutManager(new GridLayoutManager(getParentFragment().getActivity(), 4));
        // 设置这个 ImgAdapter 需要两个参数，一个是整个页面，
        mImgRecyclerView.setAdapter(new ImgAdapter(position, emotionLab));

        return (view);
    }

    private class ImgAdapter extends RecyclerView.Adapter<ImgViewHolder> {
        private ArrayList<Emotion> emotions;


        public ImgAdapter(int pagePosition, EmotionLab emotionLab) {
            // 为了加快绑定事件的速度，先准备好表情数组
            this.emotions = emotionLab.getEmotionSeries().get(pagePosition).getEmotions();

        }

        @Override
        public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.fragment_img_content, parent, false);
            return new ImgViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImgViewHolder holder, int position) {
            holder.bind(emotions.get(position));
        }

        @Override
        public int getItemCount() {
            return emotions.size();
        }
    }

    private class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextView;
        private Emotion emotion;

        public ImgViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String emotionName = emotion.getDescription();
            Toast.makeText(getParentFragment().getActivity(), emotionName, Toast.LENGTH_SHORT).show();
//            EventBus.getDefault().post(new SendEmotionEvent(emotionName));
        }



        public void bind(Emotion emotion) {
            this.emotion = emotion;
            mTextView.setText(emotion.getDescription());
            mImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.imgdemo, null));
        }
    }



//
//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }



}