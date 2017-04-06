package org.xuxiaoxiao.www.xiaoxiao;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by WuQiang on 2017/4/5.
 */

public class SelectBottomSheetFragment extends Fragment {

    private static final String KEY_POSITION="position";

    static SelectBottomSheetFragment newInstance(int position) {
        SelectBottomSheetFragment frag=new SelectBottomSheetFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.hint), position + 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.editor, container, false);
        EditText editor=(EditText)result.findViewById(R.id.editor);
        int position=getArguments().getInt(KEY_POSITION, -1);

        editor.setHint(getTitle(getActivity(), position));

        return(result);
    }
}
