package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.nyumon.jempol.R;

/**
 * Created by riskteria on 26/03/16.
 */
public class SearchFragmentThree extends Fragment {

    public SearchFragmentThree() {

    }

    @Override
    public void onCreate(Bundle savedInstanceStated) {
        super.onCreate(savedInstanceStated);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStated) {
        return inflater.inflate(R.layout.search_fragment_three, container, false);
    }

}
