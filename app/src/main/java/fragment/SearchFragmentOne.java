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

import com.nyumon.jempol.EndlessRecyclerOnScrollListener;
import com.nyumon.jempol.R;

import com.nyumon.jempol.Searching.SearchPeopleAdapter;
import com.nyumon.jempol.Searching.SearchPeopleDataSet;

import java.util.ArrayList;

/**
 * Created by riskteria on 26/03/16.
 */
public class SearchFragmentOne extends Fragment {

    private RecyclerView                    recyclerView;
    private SearchPeopleAdapter             adapter;
    private ArrayList<SearchPeopleDataSet>  DataSet;
    private SwipeRefreshLayout              refreshLayout;
    private LinearLayoutManager             llm;
    private View                            rootView;

    public SearchFragmentOne() {

    }

    @Override
    public void onCreate(Bundle savedInstanceStated) {

        super.onCreate(savedInstanceStated);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStated) {

        rootView      = inflater.inflate(R.layout.search_fragment_one, container, false);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_search_people);
        recyclerView  = (RecyclerView) rootView.findViewById(R.id.search_result_people);

        DataSet       = new ArrayList<SearchPeopleDataSet>();
        llm           = new LinearLayoutManager(getActivity());

        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new SearchPeopleAdapter(new SearchPeopleAdapter.OnLoadMoreListener() {
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
                                DataSet.add(new SearchPeopleDataSet("Username " + i, "Display Name " + i, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
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
        for(int i=0; i<=10; i++) {
            DataSet.add(new SearchPeopleDataSet("Username " + i, "Display Name " + i, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
        }
        adapter.addAll(DataSet);
    }

}
