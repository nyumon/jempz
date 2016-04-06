package com.nyumon.jempol;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Pemberitahuan extends AppCompatActivity {

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemberitahuan);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Pemberitahuan");

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.arrows);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonSuka(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_suka_mati:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_suka_Ikut:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_suka_semua:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void onRadioButtonKomentar(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_komentar_mati:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_komentar_Ikut:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_komentar_semua:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void onRadioButtonLanggan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_berlangganan_mati:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_berlangganan_Hidup:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

}
