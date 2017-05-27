package com.louisgeek.classichu.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterViewHolder;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.logic.douban.BookSearchBean;

import java.util.List;

/**
 * Created by Classichu on 2017/5/27.
 */

public class MainAdapter extends ClassicRVHeaderFooterAdapter<BookSearchBean.BooksBean>{

    public MainAdapter(Context mContext, List<BookSearchBean.BooksBean> mDataList, int mItemLayoutId) {
        super(mContext, mDataList, mItemLayoutId);
    }

    @Override
    public RVHeaderFooterAdapterDelegate setupDelegate() {
        return new RVHeaderFooterAdapterDelegate() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public int getItemViewType(int pos) {
                return 0;
            }
        };
    }

    @Override
    public void findBindView(int pos, ClassicRVHeaderFooterViewHolder classicRVHeaderFooterViewHolder) {
      TextView tv1= classicRVHeaderFooterViewHolder.findBindItemView(R.id.id_tv_item_title);
        tv1.setText(mDataList.get(pos).getTitle());
    }
}
