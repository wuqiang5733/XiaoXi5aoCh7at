/***
 Copyright (c) 2012-14 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package org.xuxiaoxiao.www.xiaoxiao;

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

        public ImgAdapter() {
            Log.d("WQ", "ImgAdapter");
        }

        @Override
        public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("WQ", "ImgViewHolder");
            View view = getParentFragment().getActivity().getLayoutInflater().inflate(R.layout.fragment_img_content, parent, false);
            return new ImgViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ImgViewHolder holder, int position) {
            holder.mTextView.setText("text");
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