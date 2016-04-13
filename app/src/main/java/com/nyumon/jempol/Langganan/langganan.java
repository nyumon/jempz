package com.nyumon.jempol.Langganan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.nyumon.jempol.R;

import java.util.ArrayList;

public class langganan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Langgananadapter adapter;
    private ArrayList<LanggananDataset> DataSet;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager llm;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_langganan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Langganan");

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.arrows);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_langganan_people);
        recyclerView  = (RecyclerView) findViewById(R.id.langganan_result_people);

        DataSet       = new ArrayList<>();
        llm           = new LinearLayoutManager(recyclerView.getContext());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new Langgananadapter(new Langgananadapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                adapter.setProgressMore(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DataSet.clear();
                        adapter.setProgressMore(false);

                        int start = adapter.getItemCount();
                        int end = start + 5;

                        for (int i = start; i < end; i++) {
                            DataSet.add(new LanggananDataset("Username " + i, "Display Name " + i, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
                        }

                        adapter.addItemMore(DataSet);
                        adapter.setMoreLoading(false);
                    }
                },2000);
            }
        });

        adapter.setLinearLayoutManager(llm);
        adapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        initDataSet();
                    }
                }, 2000);
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        initDataSet();
    }

    public void initDataSet() {

        DataSet.clear();
        for(int i=0; i<=10; i++) {
            DataSet.add(new LanggananDataset("Username " + i, "Display Name " + i, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
        }
        adapter.addAll(DataSet);
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
