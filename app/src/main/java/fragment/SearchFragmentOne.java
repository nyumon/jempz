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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.nyumon.jempol.R;
import com.nyumon.jempol.Searching.SearchPeopleAdapter;
import com.nyumon.jempol.Searching.SearchPeopleDataSet;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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
    private SearchView                      searchView;
    private ProgressBar                     pbar;

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
        searchView    = (SearchView) getActivity().findViewById(R.id.searchbox);

        DataSet       = new ArrayList<SearchPeopleDataSet>();
        llm           = new LinearLayoutManager(getActivity());
        pbar          = (ProgressBar) rootView.findViewById(R.id.pBar);

        pbar.setVisibility(View.GONE);

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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                getData();

                return false;
            }
        });

        return rootView;
    }

    private void getData() {

        String        q             = searchView.getQuery().toString();
        String        url           = config.DATA_URL + q.trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pbar.setVisibility(View.VISIBLE);
                showJSON(response);

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {

        String username;
        String displayname;

        try {

            JSONObject object  = new JSONObject(response);
            JSONArray  array   = object.getJSONArray(config.JSON_ARRAY);
            JSONObject collect = array.getJSONObject(0);
            Integer length     = array.length();

            username    = collect.getString(config.KEY_USERNAME);
            displayname = collect.getString(config.KEY_DISPLAYNAME);

            for(Integer i=0; i<length; i++) {
                DataSet.add(new SearchPeopleDataSet(username, displayname, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.addAll(DataSet);

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

    class config {

        public static final String DATA_URL         = "http://nyumon.hol.es/search/people?q=";
        public static final String KEY_USERNAME     = "username";
        public static final String KEY_DISPLAYNAME  = "displayname";
        public static final String JSON_ARRAY       = "result";

    }

}
