package com.louisgeek.classichu.patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.widget.ClassicEmptyView;
import com.classichu.classichu.basic.tool.ToastTool;
import com.classichu.classichu.classic.ClassicMvpFragment;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.patient.adapter.PatientAdapter;
import com.louisgeek.classichu.patient.bean.PatientInfoBean;
import com.louisgeek.classichu.patient.contract.PatientContract;
import com.louisgeek.classichu.patient.presenter.PatientPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientInfoFragment extends ClassicMvpFragment<PatientPresenterImpl>
  implements  PatientContract.View<PatientInfoBean> {


    public PatientInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientInfoFragment newInstance(String param1, String param2) {
        PatientInfoFragment fragment = new PatientInfoFragment();
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
        return R.layout.fragment_patient_info;
    }

    @Override
    protected void initView(View rootLayout) {
        toRefreshData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showProgress() {
        showSwipeRefreshLayout();
    }

    @Override
    public void hideProgress() {
        hideSwipeRefreshLayout();
    }

    @Override
    public void showMessage(String msg) {
        ToastTool.showShort(msg);
    }

    @Override
    public void setupData(PatientInfoBean patientInfoBean) {
        List<String> stringList=new ArrayList<>();
        PatientInfoBean.PatientBean patientBean=patientInfoBean.getPatient();
        stringList.add(String.format("%s%s","病人姓名：",patientBean.getBRXM()));
        stringList.add(String.format("%s%s","住院号码：",patientBean.getZYHM()));
        stringList.add(String.format("%s%s","病人性别：",patientBean.getBRXB()==1 ? "男" : "女"));
        if (!TextUtils.isEmpty(patientBean.getRYRQ())&&patientBean.getRYRQ().contains("T")){
            String ryrq=patientBean.getRYRQ().split("T")[0];
            stringList.add(String.format("%s%s","入院日期：",ryrq));
        }
        stringList.add(String.format("%s%s","病人床号：",patientBean.getBRCH()));
        stringList.add(String.format("%s%s","病人科室：",patientBean.getKSMC()));
        stringList.add(String.format("%s%s","主治医生：",patientBean.getYSMC()));
        stringList.add(String.format("%s%s","费用性质：",patientBean.getXZMC()));
        stringList.add(String.format("%s%s","病人床号：",patientBean.getBRCH()));
        stringList.add(String.format("%s%s","病人床号：",patientBean.getBRCH()));
        stringList.add(String.format("%s%s","病人床号：",patientBean.getBRCH()));

        if (patientInfoBean.getExpenseTotal()!=null){
            PatientInfoBean.ExpenseTotalBean expenseTotalBean=patientInfoBean.getExpenseTotal();
            stringList.add(String.format("%s%s","总费用：",expenseTotalBean.getZJJE()));
            stringList.add(String.format("%s%s","自负金额：",expenseTotalBean.getZFJE()));
            stringList.add(String.format("%s%s","交款金额：",expenseTotalBean.getJKJE()));
            stringList.add(String.format("%s%s","费用余额：",expenseTotalBean.getFYYE()));
        }

        mClassicRVHeaderFooterAdapter.refreshDataList(stringList);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupMoreData(PatientInfoBean patientInfoBean) {

    }

    @Override
    protected PatientPresenterImpl setupPresenter() {
        return new PatientPresenterImpl(this);
    }

    @Override
    protected int configRecyclerViewResId() {
        return R.id.id_recycler_view;
    }

    @Override
    protected int configSwipeRefreshLayoutResId() {
        return R.id.id_swipe_refresh_layout;
    }


    @Override
    protected void toRefreshData() {
        super.toRefreshData();
        mPresenter.gainData();
    }


    @Override
    protected ClassicRVHeaderFooterAdapter configClassicRVHeaderFooterAdapter() {
        List<String> stringList = new ArrayList<>();
        ClassicRVHeaderFooterAdapter adapter
                = new PatientAdapter(mContext, stringList, R.layout.item_list_classic);
        ClassicEmptyView classicEmptyView = new ClassicEmptyView(getContext());
        classicEmptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        classicEmptyView.setOnEmptyViewClickListener(new ClassicEmptyView.OnEmptyViewClickListener() {
            @Override
            public void onClickTextView(View view) {
                super.onClickTextView(view);
                toRefreshData();
            }

            @Override
            public void onClickImageView(View view) {
                super.onClickImageView(view);
                toRefreshData();
            }

            @Override
            public void onClickEmptyView(View view) {
                super.onClickEmptyView(view);
                toRefreshData();
            }
        });
        adapter.setEmptyView(classicEmptyView);
        adapter.setOnItemClickListener(new ClassicRVHeaderFooterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                super.onItemClick(itemView, position);
                ToastTool.showShortCenter("sda" + position);
               // startAty(PatientActivity.class);
            }
        });
        mRecyclerView.setVisibility(View.GONE);//初始化 不显示
        return adapter;
    }
}
