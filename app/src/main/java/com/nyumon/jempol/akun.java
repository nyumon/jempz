package com.nyumon.jempol;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class akun extends AppCompatActivity {

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView pil_switch;
    private Switch aSwitch;
    private TextView Sunting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        aSwitch = (Switch)findViewById(R.id.toggle_buttonn);
        pil_switch = (TextView) findViewById(R.id.pil_switch);
        aSwitch.setChecked(false);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String aktif = "Bila akun Anda bersifat privat,hanya orang yang Anda terima yang " +
                        "dapat melihat foto Anda.Pengikut Anda saat ini tidak akan terkena efeknya";
                String tdk_aktif = "Bila akun Anda bersifat tidak privat,semua orang yang Anda terima " +
                        "dapat melihat foto Anda.Pengikut Anda saat ini tidak akan terkena efeknya";
                if(aSwitch.isChecked()){
                    pil_switch.setText(aktif.toString());
                }else{
                    pil_switch.setText(tdk_aktif.toString());
                }

            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Akun");

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

    public void Sunting1(View view){
        Intent intent = new Intent(this, SuntingProfil.class);
        startActivity(intent);
    }

    public void keprofil(View view){
        Intent intent = new Intent(this, ProfilActivity2.class);
        startActivity(intent);
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