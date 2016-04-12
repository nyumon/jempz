package fragment;

/**
 * Created by com.nyumon on 23/03/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nyumon.jempol.ExpandedGridView;
import com.nyumon.jempol.ImageAdapter;
import com.nyumon.jempol.MyPost;
import com.nyumon.jempol.R;


public class TwoFragment extends Fragment {
    private FragmentTabHost mTabHost;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.twofragment,container, false);
        GridView gridView = (GridView)rootView.findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(getActivity()));

        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    return false;
                }
                return false;
            }
        });
        ExpandedGridView gridView1 = (ExpandedGridView) rootView.findViewById(R.id.grid_view);
        gridView1.setAdapter(new ImageAdapter(getActivity()));
        gridView1.setExpanded(true);
        gridView1.setVerticalScrollBarEnabled(false);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity(), MyPost.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;

    }

}