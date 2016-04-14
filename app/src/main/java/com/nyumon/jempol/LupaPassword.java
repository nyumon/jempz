package com.nyumon.jempol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by fajar on 14/04/16.
 */
public class LupaPassword extends AppCompatActivity {
    Button batal;
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_lupa_password);
        batal = (Button)findViewById(R.id.btnBatal);
    }
    public void setBatal(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
