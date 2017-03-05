package com.louisgeek.classichu.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.louisgeek.classichu.R;
import com.louisgeek.classichu.app.CLog;
import com.louisgeek.classichu.basic.listener.OnNotFastClickListener;
import com.louisgeek.classichu.basic.tool.ToastTool;
import com.louisgeek.classichu.classic.ClassicFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends ClassicFragment {


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_blank;
    }
    TextView id_tv_2;
    @Override
    protected void initView(View rootLayout) {
        id_tv_2=findById(R.id.id_tv_2);
    }

    @Override
    protected void initListener() {
        //
        setOnNotFastClickListener(id_tv_2,
                new OnNotFastClickListener() {
            @Override
            protected void onNotFastClick(View v) {
             ToastTool.showShort("xxxx");
                CLog.d("xxxx");
            }
        });
    }

}
