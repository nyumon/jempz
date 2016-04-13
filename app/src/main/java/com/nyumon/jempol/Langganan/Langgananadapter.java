package com.nyumon.jempol.Langganan;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nyumon.jempol.R;

import java.util.ArrayList;

/**
 * Created by riskteria on 03/04/16.
 */
public class Langgananadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<LanggananDataset> DataSet;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    public Langgananadapter() {

    }


    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public Langgananadapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        this.DataSet = new ArrayList<LanggananDataset>();
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
            return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showberlangganan, viewGroup, false));
        else
            return new ProgressViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_view, viewGroup, false));
    }

    public void addAll(ArrayList<LanggananDataset> lst){
        DataSet.clear();
        DataSet.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(ArrayList<LanggananDataset> lst){
        DataSet.addAll(lst);
        notifyItemRangeChanged(0, DataSet.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {

        if(itemHolder instanceof ItemHolder) {

            ((ItemHolder) itemHolder).ItemUsername.setText(DataSet.get(position).getUsername());
            ((ItemHolder) itemHolder).ItemDisplayName.setText(DataSet.get(position).getDisplayname());
            ((ItemHolder) itemHolder).ItemTotalPost.setText((String.valueOf(DataSet.get(position).getPosts()) + " Posts"));
            ((ItemHolder) itemHolder).ItemTotalSubscriber.setText((String.valueOf(DataSet.get(position).getSubscribers()) + " Berlangganan"));
            Boolean is_subcribe = DataSet.get(position).getSubscribe();
            if (is_subcribe) {
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setText("Langganan");
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setBackgroundResource(R.drawable.button_subscribe_1);
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setText("Berlangganan");
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setBackgroundResource(R.drawable.button_subscribe_0);
                ((ItemHolder) itemHolder).ItemButtonSubscribe.setTextColor(Color.parseColor("#00bcd4"));
            }

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

        public TextView ItemUsername;
        public TextView ItemDisplayName;
        public TextView ItemTotalPost;
        public TextView ItemTotalSubscriber;
        public Button   ItemButtonSubscribe;

        public ItemHolder(View v) {
            super(v);
            ItemUsername           = (TextView) v.findViewById(R.id.search_item_username);
            ItemDisplayName        = (TextView) v.findViewById(R.id.search_item_display_name);
            ItemTotalPost          = (TextView) v.findViewById(R.id.search_item_post_total);
            ItemTotalSubscriber    = (TextView) v.findViewById(R.id.search_item_subscriber_total);
            ItemButtonSubscribe    = (Button)   v.findViewById(R.id.search_item_subscribe_button);

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