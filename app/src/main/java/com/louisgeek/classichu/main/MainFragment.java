package com.louisgeek.classichu.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.widget.ClassicEmptyView;
import com.classichu.classichu.basic.tool.ToastTool;
import com.classichu.classichu.classic.ClassicMvpFragment;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.logic.douban.BookSearchBean;
import com.louisgeek.classichu.main.adapter.MainAdapter;
import com.louisgeek.classichu.main.contract.MainContract;
import com.louisgeek.classichu.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends ClassicMvpFragment<MainPresenter> implements MainContract.View<BookSearchBean>{



    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2, int param3) {
        MainFragment fragment = new MainFragment();
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
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View rootLayout) {
            toRefreshData();
    }

    @Override
    protected void initListener() {

    }


    private void switchGridOrList() {
        if (mRecyclerView==null){
            return;
        }
        if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        }else {
         mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));

            //     mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }
        //重新设置 让合并单元格的设置生效
        mRecyclerView.setAdapter(mClassicRVHeaderFooterAdapter);
      //  mClassicRVHeaderFooterAdapter.notifyDataSetChanged();
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
    public void setupData(BookSearchBean data) {
        mClassicRVHeaderFooterAdapter.refreshDataList(data.getBooks());
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupMoreData(BookSearchBean data) {
        List<BookSearchBean.BooksBean> books= data.getBooks();
        mClassicRVHeaderFooterAdapter.addDataListAtEnd(books);
        if (books.size() == 0) {
            //所有数据加载完毕
            mClassicRVHeaderFooterAdapter.showFooterViewLoadComplete();
        } else {
            //一次加载完成
            mClassicRVHeaderFooterAdapter.turnNextPageNum();
            mClassicRVHeaderFooterAdapter.showFooterViewNormal();
        }
    }
    @Override
    protected void toRefreshData() {
        super.toRefreshData();
        mPresenter.gainCountData(mClassicRVHeaderFooterAdapter.getNowPageCount());
    }

    @Override
    protected void toLoadMoreData() {
        super.toLoadMoreData();
        mPresenter.gainMoreData(mClassicRVHeaderFooterAdapter.getNextPageNum());
    }


    @Override
    protected MainPresenter setupPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int configRecyclerViewResId() {
        return R.id.id_recyclerview;
    }

    @Override
    protected int configSwipeRefreshLayoutResId() {
        return R.id.id_swipe_refresh_layout;
    }

    @Override
    protected ClassicRVHeaderFooterAdapter configClassicRVHeaderFooterAdapter() {
        List<BookSearchBean.BooksBean> listBeanList = new ArrayList<>();
        ClassicRVHeaderFooterAdapter adapter
                = new MainAdapter(mContext, listBeanList, R.layout.item_list_classic);
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
             /*   TaiZhangBean.ListBean listBean= (TaiZhangBean.ListBean) mClassicRVHeaderFooterAdapter.getData(position);
                Bundle bundle=createBundleExtraStr1(listBean.getPrimary_id());
                bundle.putString("TaiZhangName",listBean.getName());
                startAty(YZTZActivity.class,bundle );*/
            }
        });
        mRecyclerView.setVisibility(View.GONE);//初始化 不显示
        return adapter;
    }

    public void callAtAty_RightBtnClick() {
        switchGridOrList();
    }
}
