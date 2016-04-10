package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.nyumon.jempol.R;
import com.nyumon.jempol.Searching.SearchEventAdapter;
import com.nyumon.jempol.Searching.SearchEventDataSet;
import com.nyumon.jempol.Searching.SearchPeopleDataSet;

import java.util.ArrayList;

/**
 * Created by riskteria on 26/03/16.
 */
public class SearchFragmentTwo extends Fragment {

    private RecyclerView                    recyclerView;
    private SearchEventAdapter              adapter;
    private ArrayList<SearchEventDataSet>   DataSet;
    private SwipeRefreshLayout              refreshLayout;
    private LinearLayoutManager             llm;
    private View                            rootView;

    public SearchFragmentTwo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStated) {

        rootView        = inflater.inflate(R.layout.search_fragment_two, container, false);
        refreshLayout   = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_search_event);
        recyclerView    = (RecyclerView) rootView.findViewById(R.id.search_result_event);

        DataSet         = new ArrayList<SearchEventDataSet>();
        llm             = new LinearLayoutManager(getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SearchEventAdapter(new SearchEventAdapter.OnLoadMoreListener() {
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
                            DataSet.add(new SearchEventDataSet("Jalan Beo " + i, "Username " + i, "Kategori " + i, 7*i, 0));
                        }

                        adapter.addItemMore(DataSet);
                        adapter.setMoreLoading(false);
                    }
                }, 2000);

            }
        }, this.getContext());

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

        for(int i=0; i<5; i++) {
            DataSet.add(new SearchEventDataSet("Jalan Beo " + i, "Username " + i, "Kategori " + i, 7*i,0));
        }

        adapter.addAll(DataSet);
    }

}
