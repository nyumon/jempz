package com.nyumon.jempol;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import fragment.SearchFragmentOne;
import fragment.SearchFragmentTwo;
import fragment.SearchFragmentThree;


public class SearchActivity extends AppCompatActivity {

    private ActionBar actionbar;
    private Toolbar toolbar;
    private SearchView searchbox;
    private InputMethodManager imm;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView search_query;
    private int[] tabIcons = {
            R.drawable.quotes1,
            R.drawable.people1,
            R.drawable.time1,
            R.drawable.notif1,
            R.drawable.gear1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.arrows);

        setTitle("");

        searchbox = (SearchView) findViewById(R.id.searchbox);

        searchbox.setIconifiedByDefault(true);
        searchbox.setFocusable(true);
        searchbox.setIconified(false);
        searchbox.requestFocus();
        searchbox.requestFocusFromTouch();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        searchbox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){
        // Handle Back Button
        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setText("ORANG");
        tabLayout.getTabAt(1).setText("KEJADIAN");
        tabLayout.getTabAt(2).setText("TEMPAT");

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new SearchFragmentOne(), "ONE");
        adapter.addFrag(new SearchFragmentTwo(), "Two");
        adapter.addFrag(new SearchFragmentThree(), "Three");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }


    }




}
