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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nyumon.jempol.CustomAdapter;
import com.nyumon.jempol.ItemObject;
import com.nyumon.jempol.MainActivity;
import com.nyumon.jempol.NewPostActivity;
import com.nyumon.jempol.R;
import com.nyumon.jempol.RecyclerViewHolder;
import com.nyumon.jempol.TimelineActivity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import static com.nyumon.jempol.RecyclerViewHolder.*;


public class OneFragment extends Fragment{
    private LinearLayoutManager llayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        List<ItemObject> rowListItem = getAllItemList();
        llayout = new LinearLayoutManager(OneFragment.this.getActivity());

        RecyclerView rView = (RecyclerView) rootView.findViewById(R.id.rv);
        rView.setLayoutManager(llayout);

        TimelineActivity rcAdapter = new TimelineActivity(OneFragment.this.getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        return rootView;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject(R.drawable.people, "Fajar Satria Akbar", "12 minute ago", R.drawable.macet, "Jalanan Macet"));
        allItems.add(new ItemObject(R.drawable.people, "Ricky Ramadhan", "12 minute ago", R.drawable.macet, "Jalan disini macet"));
        allItems.add(new ItemObject(R.drawable.people, "Rizky Hasibuan", "12 minute ago", R.drawable.macet, "Jgn lewat sini"));
        allItems.add(new ItemObject(R.drawable.people, "Ridho Gusti", "12 minute ago", R.drawable.macet, "wiih macet kali cuy"));
        allItems.add(new ItemObject(R.drawable.people, "Preman", "12 minute ago", R.drawable.macet, "bener kata atas gw"));
        return allItems;
    }


    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */


}