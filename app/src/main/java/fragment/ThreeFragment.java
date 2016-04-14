package fragment;

/**
 * Created by com.nyumon on 23/03/16.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.nyumon.jempol.CustomAdapter;
import com.nyumon.jempol.MainActivity;
import com.nyumon.jempol.NewPostActivity;
import com.nyumon.jempol.R;


public class ThreeFragment extends Fragment {
    private View rootView;
    private ViewFlipper mViewFlipper, mViewFlipper2, mViewFlipper3;
    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private Animation.AnimationListener mAnimationListener;
    private Context mContext;
    @SuppressWarnings("deprecation")
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    int[] resource = {
            R.drawable.jempol,
            R.drawable.macet,
            R.drawable.macet35,
            R.drawable.macet,
            R.drawable.jempol
    };
    int[] resource2 = {
            R.drawable.add1,
            R.drawable.macet,
            R.drawable.macet35,
            R.drawable.macet,
            R.drawable.add1
    };
    int[] resource3 = {
            R.drawable.macet,
            R.drawable.jempol,
            R.drawable.macet35,
            R.drawable.jempol,
            R.drawable.macet
    };

    public ThreeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.threefragment, container, false);
        mContext = getActivity();
        mViewFlipper = (ViewFlipper) rootView.findViewById(R.id.viewFlipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        mViewFlipper2 = (ViewFlipper) rootView.findViewById(R.id.viewFlipper2);
        mViewFlipper2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        mViewFlipper3 = (ViewFlipper) rootView.findViewById(R.id.viewFlipper3);
        mViewFlipper3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(1000);
        mViewFlipper.startFlipping();

        mViewFlipper2.setAutoStart(true);
        mViewFlipper2.setFlipInterval(1000);
        mViewFlipper2.startFlipping();

        mViewFlipper3.setAutoStart(true);
        mViewFlipper3.setFlipInterval(1000);
        mViewFlipper3.startFlipping();


        for(int i = 0; i < resource.length; i++){
            ImageView imageview = new ImageView(this.getActivity());
            imageview.setImageResource(resource[i]);
            mViewFlipper.addView(imageview);
        }
        for(int i = 0; i < resource2.length; i++){
            ImageView imageview = new ImageView(this.getActivity());
            imageview.setImageResource(resource2[i]);
            mViewFlipper2.addView(imageview);
        }
        for(int i = 0; i < resource3.length; i++) {
            ImageView imageview = new ImageView(this.getActivity());
            imageview.setImageResource(resource3[i]);
            mViewFlipper3.addView(imageview);
        }

        mAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mViewFlipper2.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper2.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mViewFlipper3.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper3.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    mViewFlipper2.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    mViewFlipper3.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
                    mViewFlipper2.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper2.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
                    mViewFlipper3.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper3.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showPrevious();
                    mViewFlipper2.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper2.showPrevious();
                    mViewFlipper3.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper3.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }


}