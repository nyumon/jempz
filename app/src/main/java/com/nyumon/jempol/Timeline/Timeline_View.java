package com.nyumon.jempol.Timeline;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ActionBarContainer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.nyumon.jempol.R;

import org.w3c.dom.Text;

/**
 * Created by fajar on 14/04/16.
 */
public class Timeline_View extends AppCompatActivity {
    private Toolbar toolbar;
    int total = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView like = (ImageView) findViewById(R.id.like);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("View");

        final ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.arrows);
        Intent i = getIntent();

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView tot = (TextView) findViewById(R.id.etlike);
                total += 1;
                tot.setText(String.valueOf(total));
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView countryName;
        public TextView countryTime;
        public ImageView countryPhoto;
        public ImageView photoAkun;
        public TextView countryJudul;
        public TextView nama2;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);

            countryName     = (TextView) itemView.findViewById(R.id.person_name);
            countryTime     = (TextView) itemView.findViewById(R.id.person_age);
            countryPhoto    = (ImageView) itemView.findViewById(R.id.person_photo);
            photoAkun       = (ImageView) itemView.findViewById(R.id.photoAkun);
            countryJudul    = (TextView) itemView.findViewById(R.id.judul);
            nama2           = (TextView) itemView.findViewById(R.id.nama2);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
