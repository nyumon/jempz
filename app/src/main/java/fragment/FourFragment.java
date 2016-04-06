package fragment;

/**
 * Created by com.nyumon on 23/03/16.
 */
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nyumon.jempol.CustomAdapter;
import com.nyumon.jempol.FourAdapter;
import com.nyumon.jempol.ImageAdapter;
import com.nyumon.jempol.MainActivity;
import com.nyumon.jempol.NewPostActivity;
import com.nyumon.jempol.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Handler;


public class FourFragment extends Fragment{

    /*String firstString = "";
    String secondString = "";
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;*/
    private FourAdapter mAdapter;
    private LinkedList<ClipData.Item> itemList;
    private SwipeRefreshLayout swipeRefresh;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

/*
    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

        /*initDataset();*/

    }

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fourfragment, container, false);

        itemList = new LinkedList<>();
        swipeRefresh = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefresh);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FourAdapter(new FourAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d("MainActivity_","onLoadMore");
                mAdapter.setProgressMore(true);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        itemList.clear();
                        mAdapter.setProgressMore(false);
                        int start = mAdapter.getItemCount();
                        int end = start + 15;
                        for (int i = start + 1; i <= end; i++) {
                            itemList.add(new ClipData.Item("Bawah" + i));
                        }
                        mAdapter.addItemMore(itemList);
                        mAdapter.setMoreLoading(false);
                    }
                },2000);
            }
        });
        mAdapter.setLinearLayoutManager(mLayoutManager);
        mAdapter.setRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("MainActivity_","onRefresh");
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*itemList.clear();*/
                        swipeRefresh.setRefreshing(false);
                        loadData1();
                    }
                },2000);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MainActivity_","onStart");
        loadData1();
    }

    private void loadData1(){
        itemList.clear();
        for (int i = 1; i <= 20; i++) {
            itemList.add(new ClipData.Item("Atas" + i));
        }
        mAdapter.addAll(itemList);
    }

}