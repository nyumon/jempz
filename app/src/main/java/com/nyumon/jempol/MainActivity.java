package com.nyumon.jempol;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.Gravity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import fragment.FiveFragment;
import fragment.FourFragment;
import fragment.OneFragment;
import fragment.ThreeFragment;
import fragment.TwoFragment;

public class MainActivity extends AppCompatActivity {

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.quotes1,
            R.drawable.people1,
            R.drawable.time1,
            R.drawable.notif1,
            R.drawable.gear1
    };
    private int[] tabIconsActive = {
            R.drawable.quotes,
            R.drawable.people,
            R.drawable.time,
            R.drawable.notif,
            R.drawable.gear
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        tabLayout.getTabAt(0).setIcon(tabIconsActive[0]);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void NewPost(View view){
        Intent intent = new Intent(this, NewPostActivity.class);
        startActivity(intent);
    }

    public void setPemberitahuan(View view){
        Intent intent = new Intent(this, Pemberitahuan.class);
        startActivity(intent);
    }

    public void setPrivasi(View view){
        Intent intent = new Intent(this, akun.class);
        startActivity(intent);
    }

    public void Logout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }








    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new TwoFragment(), "TWO");
        adapter.addFrag(new ThreeFragment(), "Three");
        adapter.addFrag(new FourFragment(), "Four");
        adapter.addFrag(new FiveFragment(), "Five");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setupTabIcons();
                switch (position){
                    case 0:
                        tabLayout.getTabAt(0).setIcon(tabIconsActive[0]);
                        setTitle("JEMPOL");
                        break;

                    case 1:
                        tabLayout.getTabAt(1).setIcon(tabIconsActive[1]);
                        setTitle("PROFIL");
                        break;

                    case 2:
                        tabLayout.getTabAt(2).setIcon(tabIconsActive[2]);
                        setTitle("TERHANGAT");
                        break;

                    case 3:
                        tabLayout.getTabAt(3).setIcon(tabIconsActive[3]);
                        setTitle("PEMBERITAHUAN");
                        break;

                    case 4:
                        tabLayout.getTabAt(4).setIcon(tabIconsActive[4]);
                        setTitle("PENGATURAN");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        // Handle Selected Item
        switch (item.getItemId()){

            case R.id.action_search:
                Intent intent = new Intent(this,SearchActivity.class );
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
