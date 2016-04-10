package com.nyumon.jempol.Searching;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nyumon.jempol.R;

import java.util.ArrayList;

/**
 * Created by riskteria on 10/04/16.
 */
public class SearchPlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SearchPlaceDataSet> DataSet;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public SearchPlaceAdapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        DataSet = new ArrayList<SearchPlaceDataSet>();
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public void setRecyclerView(RecyclerView mView) {
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (!isMoreLoading && (totalItemCount - visibleItemCount)<= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return DataSet.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == VIEW_ITEM)
            return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_search_place_data, viewGroup, false));
        else
            return new ProgressViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_view, viewGroup, false));
    }

    public void addAll(ArrayList<SearchPlaceDataSet> lst){
        DataSet.clear();
        DataSet.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(ArrayList<SearchPlaceDataSet> lst){
        DataSet.addAll(lst);
        notifyItemRangeChanged(0, DataSet.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {

        if(itemHolder instanceof ItemHolder) {

            ((ItemHolder) itemHolder).PlaceName.setText(DataSet.get(position).getPlacename());
            ((ItemHolder) itemHolder).TotalPosts.setText(String.valueOf(DataSet.get(position).getTotalposts()) + "Posts");
        }
    }

    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    public int getItemCount() { return DataSet.size(); }

    public void setProgressMore(final boolean isProgress) {
        if (isProgress) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    DataSet.add(null);
                    notifyItemInserted(DataSet.size() - 1);
                }
            });
        } else {
            DataSet.remove(DataSet.size() - 1);
            notifyItemRemoved(DataSet.size());
        }
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        public TextView PlaceName;
        public TextView TotalPosts;

        public ItemHolder(View v) {
            super(v);
            PlaceName   = (TextView) v.findViewById(R.id.search_place_name);
            TotalPosts  = (TextView) v.findViewById(R.id.search_place_totalposts);
        }

    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pBar;
        public ProgressViewHolder(View v) {
            super(v);
            pBar = (ProgressBar) v.findViewById(R.id.pBar);
        }
    }

}
