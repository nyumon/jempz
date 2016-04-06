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
import com.nyumon.jempol.Searching.SearchEventAdapter;
import com.nyumon.jempol.Searching.SearchEventDataSet;

import java.util.ArrayList;

/**
 * Created by riskteria on 26/03/16.
 */
public class SearchFragmentTwo extends Fragment {

    private RecyclerView recyclerView;
    private SearchEventAdapter adapter;
    private ArrayList<SearchEventDataSet> DataSet;
    private LinearLayoutManager llm;

    public SearchFragmentTwo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceStated) {
        initDataSet();
        super.onCreate(savedInstanceStated);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStated) {

        View rootView = inflater.inflate(R.layout.search_fragment_two, container, false);
        rootView.setTag("RecyclerViewFragment");

        llm          =  new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.search_result_event);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(llm);

        adapter = new SearchEventAdapter(this.getContext(), DataSet);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void initDataSet() {
        DataSet = new ArrayList<SearchEventDataSet>();
        for(int i=0; i<4; i++) {
            DataSet.add(new SearchEventDataSet("Jalan Beo " + i, "Username " + i, "Kategori " + i, 7*i));
        }
    }

}
