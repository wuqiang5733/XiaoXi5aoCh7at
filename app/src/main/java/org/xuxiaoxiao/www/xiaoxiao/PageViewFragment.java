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

import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionLab;
import org.xuxiaoxiao.www.xiaoxiao.infrastructure.emotion.EmotionSeries;

import java.util.ArrayList;

public class PageViewFragment extends android.support.v4.app.Fragment {
    private static final String KEY_POSITION = "position";
    private static final String KEY_EMOTIONSERIES = "emotionSeries";
    private RecyclerView mImgRecyclerView;
    private ArrayList<EmotionSeries> emotionSeries;
    private EmotionLab emotionLab;


    static PageViewFragment newInstance(int position, EmotionLab emotionLab) {
//        emotionSeries = emotionSeries;
        // 这个 position 是整个页面的 position
        PageViewFragment frag = new PageViewFragment();
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
//        args.putParcelableArrayList();
//        args.putSparseParcelableArray();
//        args.putParcelableArray(KEY_EMOTIONSERIES,emotionSeries);
        args.putParcelable(KEY_EMOTIONSERIES, emotionLab);
        frag.setArguments(args);
//        emotionSeries.get(0).getEmotions().get(0).getDescription();
        return (frag);
    }

//    public PageViewFragment(ArrayList<EmotionSeries> emotionSeries) {
//        this.emotionSeries = emotionSeries;
//    }
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
//        mImgRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        mImgRecyclerView.setLayoutManager(new GridLayoutManager(getParentFragment().getActivity(), 4));
        // 设置这个 ImgAdapter 需要两个参数，一个是整个页面，
        mImgRecyclerView.setAdapter(new ImgAdapter(position, emotionLab));
//        Log.d("WQ", "PageViewFragment_onCreateView");
//        emotionLab

        return (view);
    }

    private class ImgAdapter extends RecyclerView.Adapter<ImgViewHolder> {
        private int mPagePosition;
        private EmotionLab emotionLab;
        private EmotionSeries emotionSeries;


        public ImgAdapter(int pagePosition, EmotionLab emotionLab) {
//            Log.d("WQ", "ImgAdapter");
            this.mPagePosition = pagePosition;
            this.emotionLab = emotionLab;
            emotionSeries = emotionLab.getEmotionSeries().get(pagePosition);

        }

        @Override
        public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            Log.d("WQ", "ImgViewHolder");
            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.fragment_img_content, parent, false);
            return new ImgViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImgViewHolder holder, int position) {
            holder.mTextView.setText(emotionSeries.getEmotions().get(position).getDescription());
//            holder.mImageView.setImageDrawable(getResources().getDrawable(R.drawable.pretendimg));
            holder.mImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.imgdemo, null));
        }

        @Override
        public int getItemCount() {
            return emotionSeries.getMotionNum();
        }
    }

    private class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextView;

        public ImgViewHolder(View itemView) {
            super(itemView);
//            Log.d("WQ", "ImgViewHolder");
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getParentFragment().getActivity(), "6789", Toast.LENGTH_SHORT).show();
        }
    }
}