package com.nyumon.jempol;

/**
 * Created by fajar on 04/04/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public TextView countryTime;
    public ImageView countryPhoto;
    public TextView countryJudul;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.person_name);
        countryTime = (TextView)itemView.findViewById(R.id.person_age);
        countryPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        countryJudul = (TextView)itemView.findViewById(R.id.judul);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
