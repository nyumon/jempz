package  com.nyumon.jempol.AllTrending;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyumon.jempol.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SampleAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    private final String[] mGroups = {
            "Hari ini",
            "Minggu ini",
            "Bulan ini",
    };

    private final int[] mGroupDrawables = {
            R.drawable.add,
            R.drawable.add,
            R.drawable.add,
    };

    private final String[][] mChilds = {
            {"1.5"},
            {"1.6"},
            {"2.0","2.0.1","2.1"}
    };

    public SampleAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mGroups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_group_item, parent, false);
        }

        final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_group_item_text);
        text.setText(mGroups[groupPosition]);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChilds[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChilds[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_child_item, parent, false);
        }

        final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_child_item_text);
        text.setText(mChilds[groupPosition][childPosition]);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}