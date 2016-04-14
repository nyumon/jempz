package com.nyumon.jempol;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nyumon.jempol.R;

/**
 * Created by ricky on 03/04/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet,
            R.drawable.macet
    };

    public Integer[]addThumb=
            {
              R.drawable.addpicture,
              R.drawable.addpicture,
              R.drawable.addpicture,
              R.drawable.addpicture

            };
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setLayoutParams(new GridView.LayoutParams(180, 180));
        return imageView;
    }

}


