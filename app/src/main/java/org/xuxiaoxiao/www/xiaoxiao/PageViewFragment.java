package org.xuxiaoxiao.www.xiaoxiao;

/**
 * Created by WuQiang on 2017/4/8.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PageViewFragment extends android.support.v4.app.Fragment {
    private static final String KEY_POSITION = "position";
    private RecyclerView mImgRecyclerView;

    static PageViewFragment newInstance(int position) {
        PageViewFragment frag = new PageViewFragment();
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return (frag);
    }


    static String getTitle(Context ctxt, int position) {
        return (String.format(ctxt.getString(R.string.hint), position + 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editor, container, false);
//        EditText editor = (EditText) view.findViewById(R.id.editor);
        int position = getArguments().getInt(KEY_POSITION, -1);

//        editor.setHint(getTitle(getActivity(), position));

        mImgRecyclerView = (RecyclerView) view.findViewById(R.id.img_recycler_view);
//        mImgRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        mImgRecyclerView.setLayoutManager(new GridLayoutManager(getParentFragment().getActivity(), 4));
        mImgRecyclerView.setAdapter(new ImgAdapter());
        Log.d("WQ", "PageViewFragment_onCreateView");
        return (view);
    }

    private class ImgAdapter extends RecyclerView.Adapter<ImgViewHolder> {
        private int[] temp = new int[12];


        public ImgAdapter() {
            Log.d("WQ", "ImgAdapter");
            for(int i = 0; i < 12 ; i ++){
                temp[i] = i;
            }
        }

        @Override
        public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("WQ", "ImgViewHolder");
            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.fragment_img_content, parent, false);
            return new ImgViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImgViewHolder holder, int position) {
            holder.mTextView.setText(String.valueOf(temp[position]));
        }

        @Override
        public int getItemCount() {
            return 12;
        }
    }

    private class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageView;
        private TextView mTextView;

        public ImgViewHolder(View itemView) {
            super(itemView);
            Log.d("WQ", "ImgViewHolder");
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