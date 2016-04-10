package fragment;

/**
 * Created by com.nyumon on 23/03/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nyumon.jempol.Timeline.TimelineDataSet;
import com.nyumon.jempol.R;
import com.nyumon.jempol.Timeline.TimelineAdapter;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment {

    private RecyclerView                recyclerView;
    private TimelineAdapter             adapter;
    private ArrayList<TimelineDataSet>  DataSet;
    private SwipeRefreshLayout          refreshLayout;
    private LinearLayoutManager         llm;
    private View                        rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView        = inflater.inflate(R.layout.fragment_main, container, false);
        refreshLayout   = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_timeline);
        recyclerView    = (RecyclerView) rootView.findViewById(R.id.timeline_result);

        DataSet       = new ArrayList<TimelineDataSet>();
        llm           = new LinearLayoutManager(getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new TimelineAdapter(this.getContext(), new TimelineAdapter.OnLoadMoreListener() {
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
                            DataSet.add(new TimelineDataSet(R.drawable.people, "Pengguna"+i, i+"minute ago", R.drawable.macet, "Jalanan Macet"));
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

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        initDataSet();
    }

    public void initDataSet() {

        DataSet.clear();

        DataSet.add(new TimelineDataSet(R.drawable.people, "Fajar Satria Akbar", "12 minute ago", R.drawable.macet, "Jalanan Macet"));
        DataSet.add(new TimelineDataSet(R.drawable.people, "Ricky Ramadhan", "12 minute ago", R.drawable.macet, "Jalan disini macet"));
        DataSet.add(new TimelineDataSet(R.drawable.people, "Rizky Hasibuan", "12 minute ago", R.drawable.macet, "Jgn lewat sini"));
        DataSet.add(new TimelineDataSet(R.drawable.people, "Ridho Gusti", "12 minute ago", R.drawable.macet, "wiih macet kali cuy"));
        DataSet.add(new TimelineDataSet(R.drawable.people, "Preman", "12 minute ago", R.drawable.macet, "bener kata atas gw"));

        adapter.addAll(DataSet);
    }


    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */


}