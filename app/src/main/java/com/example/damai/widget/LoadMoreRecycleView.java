package com.example.damai.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by guodazhao on 2018/2/1 0001.
 */

public class LoadMoreRecycleView extends RecyclerView {

    public LoadMoreRecycleView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(new OnScrollListener() {
            int lastPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (getAdapter() == null)
                        return;


                    if (lastPosition == getAdapter().getItemCount() - 1) {

                        if (!isRefreshing && l != null) {
                            isRefreshing = true;
                            l.loadMore();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    int lastVisiableItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                    if (lastVisiableItemPosition!=-1) {
                        lastPosition = lastVisiableItemPosition;
                    }
                }
            }
        });
    }

    private boolean isRefreshing;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    private OnLoadMoreListener l;

    public void setOnLoadMoreListener(OnLoadMoreListener l) {
        this.l = l;
    }

    public interface OnLoadMoreListener {
        void loadMore();

    }
}
