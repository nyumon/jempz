package com.nyumon.jempol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPost extends AppCompatActivity {
    private Toolbar toolbar;
    int total =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView jempol = (ImageView)findViewById(R.id.jempol);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Postingan");

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.arrows);
        Intent i = getIntent();




        jempol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView  tot = (TextView)findViewById(R.id.totjempol);
                total += 1;
                tot.setText(String.valueOf(total));

            }
        });


    }

    public void kekomentar(View view){
        Intent intent = new Intent(this, Comment.class);
        startActivity(intent);
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
