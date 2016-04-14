package com.nyumon.jempol.kategoriSama;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.nyumon.jempol.kategoriSama.KategorisamaDataset;
import com.nyumon.jempol.R;

import java.util.ArrayList;

/**
 * Created by fajar on 14/04/16.
 */
public class KategoriSama extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KategorisamaAdapter adapter;
    private ArrayList<KategorisamaDataset> DataSet;
    private SwipeRefreshLayout refreshLayout;
    private GridLayoutManager llm;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_kategori_sama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Kategori yang Sama");

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.arrows);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_kategorisama);
        recyclerView  = (RecyclerView) findViewById(R.id.kategorisama_Result);

        DataSet       = new ArrayList<>();
        llm           = new GridLayoutManager(recyclerView.getContext(),3);

        llm.setOrientation(GridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new KategorisamaAdapter(new KategorisamaAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                adapter.setProgressMore(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DataSet.clear();
                        adapter.setProgressMore(false);

                        int start = adapter.getItemCount();
                        int end = start + 6;

                        for (int i = start; i < end; i++) {
                            DataSet.add(new KategorisamaDataset(1));
                        }

                        adapter.addItemMore(DataSet);
                        adapter.setMoreLoading(false);
                    }
                },2000);
            }
        }, KategoriSama.this );

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
        for(int i=0; i<12; i++) {
            DataSet.add(new KategorisamaDataset(1));
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
