package com.nyumon.jempol.Searching;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyumon.jempol.R;

import java.util.ArrayList;

/**
 * Created by riskteria on 04/04/16.
 */
public class SearchEventAdapter extends RecyclerView.Adapter<SearchEventAdapter.ItemHolder>{

    private ArrayList<SearchEventDataSet> DataSet;
    private LayoutInflater inflater;

    public SearchEventAdapter(Context context, ArrayList<SearchEventDataSet> DataSet) {
        this.DataSet = DataSet;
        inflater = LayoutInflater.from(context);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public TextView ItemPlace;
        public TextView ItemCatagory;
        public TextView ItemUsername;
        public TextView ItemJempol;
        public ImageView ItemImage;

        public ItemHolder(View v) {
            super(v);
            ItemPlace       = (TextView) v.findViewById(R.id.search_event_place);
            ItemCatagory    = (TextView) v.findViewById(R.id.search_event_catagory);
            ItemUsername    = (TextView) v.findViewById(R.id.search_event_username);
            ItemJempol      = (TextView) v.findViewById(R.id.search_event_jempol_total);
            ItemImage       = (ImageView) v.findViewById(R.id.search_event_image);
        }

    }

    public SearchEventAdapter.ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_search_event_data, viewGroup, false);
        return new ItemHolder(itemView);
    }

    public void onBindViewHolder(ItemHolder itemHolder, int position) {

        itemHolder.ItemPlace.setText(DataSet.get(position).getUsername());
        itemHolder.ItemCatagory.setText(DataSet.get(position).getCatagory());
        itemHolder.ItemUsername.setText(DataSet.get(position).getUsername());
        itemHolder.ItemJempol.setText(String.valueOf(DataSet.get(position).getJempol_total()));

        ImageCompress("android.resource://com.nyumon.jempol" + R.drawable.cth, itemHolder);
    }

    public int getItemCount() { return DataSet.size(); }

    private void ImageCompress(String path, ItemHolder itemHolder) {

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        itemHolder.ItemImage.setImageBitmap(bm);
    }


}
