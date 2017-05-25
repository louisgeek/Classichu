package com.louisgeek.classichu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.classichu.classichu.classic.ClassicFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFragment extends ClassicFragment {

    public BFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BFragment newInstance(String param1, String param2, int param3) {
        BFragment fragment = new BFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_b;
    }

    @Override
    protected void initView(View rootLayout) {

    }

    @Override
    protected void initListener() {

    }

}
