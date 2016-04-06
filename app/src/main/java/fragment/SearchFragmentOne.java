package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.nyumon.jempol.R;

import com.nyumon.jempol.Searching.SearchPeopleAdapter;
import com.nyumon.jempol.Searching.SearchPeopleDataSet;

import java.util.ArrayList;

/**
 * Created by riskteria on 26/03/16.
 */
public class SearchFragmentOne extends Fragment {

    private RecyclerView recyclerView;
    private SearchPeopleAdapter adapter;
    private ArrayList<SearchPeopleDataSet> DataSet;

    private LinearLayoutManager rlm;

    public SearchFragmentOne() {

    }

    @Override
    public void onCreate(Bundle savedInstanceStated) {

        initDataSet();

        super.onCreate(savedInstanceStated);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStated) {

        View rootView = inflater.inflate(R.layout.search_fragment_one, container, false);
        rootView.setTag("RecyclerViewFragment");

        rlm          =  new LinearLayoutManager(getActivity());
        rlm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.search_result_people);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(rlm);

        adapter = new SearchPeopleAdapter(this.getContext(), DataSet);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void initDataSet() {

        DataSet = new ArrayList<SearchPeopleDataSet>();

        for(int i=0; i<10; i++) {
            DataSet.add(new SearchPeopleDataSet("Username " + i, "Display Name " + i, 1000+(24*i), 1000+(24*i), (i%2)==0?true:false));
        }
    }

}
