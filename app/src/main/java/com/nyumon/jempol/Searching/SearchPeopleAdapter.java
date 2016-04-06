package com.nyumon.jempol.Searching;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nyumon.jempol.R;

import java.util.ArrayList;

/**
 * Created by riskteria on 03/04/16.
 */
public class SearchPeopleAdapter extends RecyclerView.Adapter<SearchPeopleAdapter.ItemHolder> {

    private ArrayList<SearchPeopleDataSet> DataSet;
    private LayoutInflater inflater;

    public SearchPeopleAdapter(Context context, ArrayList<SearchPeopleDataSet> DataSet) {
        inflater = LayoutInflater.from(context);
        this.DataSet = DataSet;
    }

    public SearchPeopleAdapter.ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_search_people_data, viewGroup, false);
        return new ItemHolder(itemView);
    }

    public void onBindViewHolder(ItemHolder itemHolder, int position) {
        itemHolder.ItemUsername.setText(DataSet.get(position).getUsername());
        itemHolder.ItemDisplayName.setText(DataSet.get(position).getDisplayname());
        itemHolder.ItemTotalPost.setText((String.valueOf(DataSet.get(position).getPosts()) + " Posts"));
        itemHolder.ItemTotalSubscriber.setText((String.valueOf(DataSet.get(position).getSubscribers()) + " Berlangganan"));

        Boolean is_subcribe = DataSet.get(position).getSubscribe();
        if(is_subcribe) {
            itemHolder.ItemButtonSubscribe.setText("Langganan");
            itemHolder.ItemButtonSubscribe.setBackgroundResource(R.drawable.button_subscribe_1);
            itemHolder.ItemButtonSubscribe.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else {
            itemHolder.ItemButtonSubscribe.setText("Berlangganan");
            itemHolder.ItemButtonSubscribe.setBackgroundResource(R.drawable.button_subscribe_0);
            itemHolder.ItemButtonSubscribe.setTextColor(Color.parseColor("#00bcd4"));
        }
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

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

    public int getItemCount() { return DataSet.size(); }



}
