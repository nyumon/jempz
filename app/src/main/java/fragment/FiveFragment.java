package fragment;

/**
 * Created by com.nyumon on 23/03/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.nyumon.jempol.R;


public class FiveFragment extends Fragment{


    private Switch aSwitch;

    public FiveFragment() {
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
        return inflater.inflate(R.layout.fivefragment, container, false);

    }
    private void closefragment() {

        getActivity().getFragmentManager().popBackStack();
    }

}