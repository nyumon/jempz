package com.nyumon.jempol.Timeline;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nyumon.jempol.MainActivity;
import com.nyumon.jempol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fajar on 04/04/16.
 */
public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TimelineDataSet> DataSet;
    public Context context;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private boolean isMoreLoading = false;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager linearLayoutManager;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public TimelineAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context            = context;
        this.onLoadMoreListener = onLoadMoreListener;
        this.DataSet            = new ArrayList<TimelineDataSet>();
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        if(viewType == VIEW_ITEM)
            return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timeline, viewGroup, false));
        else
            return new ProgressViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_view, viewGroup, false));
    }

    public void addAll(ArrayList<TimelineDataSet> lst){
        DataSet.clear();
        DataSet.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(ArrayList<TimelineDataSet> lst){
        DataSet.addAll(lst);
        notifyItemRangeChanged(0, DataSet.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemHolder) {

            ((ItemHolder) holder).photoAkun.setImageResource(DataSet.get(position).getPhotoAkun());
            ((ItemHolder) holder).countryName.setText(DataSet.get(position).getName());
            ((ItemHolder) holder).countryTime.setText(DataSet.get(position).getTime());
            ((ItemHolder) holder).countryPhoto.setImageResource(DataSet.get(position).getPhoto());
            ((ItemHolder) holder).countryJudul.setText(DataSet.get(position).getJudul());
            ((ItemHolder) holder).nama2.setText(DataSet.get(position).getName());

        }
    }

    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    public int getItemCount() {
        return DataSet.size();
    }

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

    static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView countryName;
        public TextView countryTime;
        public ImageView countryPhoto;
        public ImageView photoAkun;
        public TextView countryJudul;
        public TextView nama2;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            countryName     = (TextView) itemView.findViewById(R.id.person_name);
            countryTime     = (TextView) itemView.findViewById(R.id.person_age);
            countryPhoto    = (ImageView) itemView.findViewById(R.id.person_photo);
            photoAkun       = (ImageView) itemView.findViewById(R.id.photoAkun);
            countryJudul    = (TextView) itemView.findViewById(R.id.judul);
            nama2           = (TextView) itemView.findViewById(R.id.nama2);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Posting dari " + countryName.getText(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), Timeline_View.class);
            view.getContext().startActivity(intent);
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
