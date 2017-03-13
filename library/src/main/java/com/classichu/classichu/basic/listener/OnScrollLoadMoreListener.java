package com.classichu.classichu.basic.listener;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.classichu.classichu.app.CLog;
import com.classichu.classichu.basic.tool.ViewTool;

/**
 * Created by louisgeek on 2017/3/10.
 */
@Deprecated
public abstract class OnScrollLoadMoreListener extends RecyclerView.OnScrollListener {

    private final int mScrollThreshold = 10;
    /**
     * 判断是否到达底部
     * 2016-11-3 16:24:00
     * item 很少的时候  有点问题
     */
    @Deprecated
    public static boolean isReachedBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1
                && state == RecyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 当屏幕中的 item 数量多到超出屏幕的时候，这时候的滚动是会触发 onScrolled(RecyclerView recyclerView, int dx, int dy) 方法的。
     屏幕中的 item 完全显示在屏幕中时，onScrolled(RecyclerView recyclerView, int dx, int dy) 是不会被触发的。
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /**
         * 解决 SwipeRefreshLayout 、RecyclerView 下拉冲突
         */
        int topRowVerticalPosition =
                (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
        //
        if (setupSwipeRefreshLayout()!=null){
        setupSwipeRefreshLayout().setEnabled(topRowVerticalPosition >= 0);
        }

        /**
         *滑动处理的门槛
         */
        if (Math.abs(dy) > mScrollThreshold) {
            if (dy > 0) {
                //往上滑动  往下拉
             if (ViewTool.isReachedBottom(recyclerView)){
                 //到达底部
                 onReachedBottom();
             }
            } else if (dy < 0) {
                //往下滑动  往上拉
                if (ViewTool.isReachedTop(recyclerView)){
                    //到达顶部

                }
            }
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        CLog.d("newState:"+newState);
        CLog.d("isReachedBottom:"+isReachedBottom(recyclerView));
    }

    public abstract void onReachedBottom();
    public abstract SwipeRefreshLayout setupSwipeRefreshLayout();

}
