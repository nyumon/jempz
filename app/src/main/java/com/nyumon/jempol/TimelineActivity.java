package com.nyumon.jempol;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fajar on 04/04/16.
 */
public class TimelineActivity extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<ItemObject> itemList;
    private Context context;

    public TimelineActivity(Context context, List<ItemObject> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.photoAkun.setImageResource(itemList.get(position).getPhotoAkun());
        holder.countryName.setText(itemList.get(position).getName());
        holder.countryTime.setText(itemList.get(position).getTime());
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        holder.countryJudul.setText(itemList.get(position).getJudul());
        holder.nama2.setText(itemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
