package com.louisgeek.classichu;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.classichu.classichu.basic.listener.OnViewClickEnabledListener;
import com.classichu.classichu.classic.ClassicFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@Deprecated
public class BFragment extends ClassicFragment {

    @BindView(R.id.id_tv)
    TextView id_tv;
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
        setOnViewClickEnabledListener(id_tv, new OnViewClickEnabledListener() {
            @Override
            protected void onViewClick(View view) {
                RxPermissions rxPermissions=new RxPermissions(mActivity);
                rxPermissions.request(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    // 获取权限
                                } else {
                                    //  未获取权限
                                }
                            }
                        });
                rxPermissions.request(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    // 获取了所有权限
                                } else {
                                    //  未获取所有权限
                                }
                            }
                        });
                rxPermissions.requestEach(Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.name.equals(Manifest.permission.CAMERA)) {
                                    if (permission.granted) {
                                        // 获取权限
                                    } else {
                                        //  未获取权限
                                    }

                                } else if (permission.name.equals(Manifest.permission.RECORD_AUDIO)) {

                                    if (permission.granted) {
                                        // 获取权限
                                    } else {
                                        //  未获取权限
                                    }

                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void initListener() {

    }

}
