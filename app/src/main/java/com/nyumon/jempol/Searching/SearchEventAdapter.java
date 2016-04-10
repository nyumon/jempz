package com.nyumon.jempol.Searching;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nyumon.jempol.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by riskteria on 04/04/16.
 */
public class SearchEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<SearchEventDataSet> DataSet;
    private Context context;

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

    public SearchEventAdapter(OnLoadMoreListener onLoadMoreListener, Context context) {
        this.onLoadMoreListener = onLoadMoreListener;
        DataSet         = new ArrayList<SearchEventDataSet>();
        this.context    = context;
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
            return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_search_event_data, viewGroup, false));
        else
            return new ProgressViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loadmore_view, viewGroup, false));
    }

    public void addAll(ArrayList<SearchEventDataSet> lst){
        DataSet.clear();
        DataSet.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(ArrayList<SearchEventDataSet> lst){
        DataSet.addAll(lst);
        notifyItemRangeChanged(0, DataSet.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position) {

        if(itemHolder instanceof ItemHolder) {

            ((ItemHolder) itemHolder).ItemPlace.setText(DataSet.get(position).getPlace());
            ((ItemHolder) itemHolder).ItemCatagory.setText(DataSet.get(position).getCatagory());
            ((ItemHolder) itemHolder).ItemUsername.setText(DataSet.get(position).getUsername());
            ((ItemHolder) itemHolder).ItemJempol.setText(String.valueOf(DataSet.get(position).getJempol_total()));

            //Bitmap gambar = resizeBitMapImage(String.valueOf(R.drawable.cth), 270, 160);

            //((ItemHolder) itemHolder).ItemImage.setImageBitmap(gambar);

            Picasso.with(context).load(R.drawable.cth).resize(270, 160).into(((ItemHolder) itemHolder).ItemImage);

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

    public static Bitmap resizeBitMapImage(String filePath, int targetWidth, int targetHeight) {

        Bitmap bitMapImage = null;
        // First, get the dimensions of the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        double sampleSize = 0;
        // Only scale if we need to
        // (16384 buffer for img processing)
        Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math
                .abs(options.outWidth - targetWidth);

        if (options.outHeight * options.outWidth * 2 >= 1638) {
            // Load, scaling to smallest power of 2 that'll get it <= desired
            // dimensions
            sampleSize = scaleByHeight ? options.outHeight / targetHeight
                    : options.outWidth / targetWidth;
            sampleSize = (int) Math.pow(2d,
                    Math.floor(Math.log(sampleSize) / Math.log(2d)));
        }

        // Do the actual decoding
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[128];
        while (true) {
            try {
                options.inSampleSize = (int) sampleSize;
                bitMapImage = BitmapFactory.decodeFile(filePath, options);

                break;
            } catch (Exception ex) {
                try {
                    sampleSize = sampleSize * 2;
                } catch (Exception ex1) {

                }
            }
        }

        return bitMapImage;
    }



    static class ItemHolder extends RecyclerView.ViewHolder {

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

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pBar;
        public ProgressViewHolder(View v) {
            super(v);
            pBar = (ProgressBar) v.findViewById(R.id.pBar);
        }
    }

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
