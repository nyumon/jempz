package com.nyumon.jempol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fajar on 26/03/16.
 */

public class RegisterActivity extends Activity {
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin    = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnLogin);

    }

    public void invokeRegister(View view) {



    }

    public void invokeLogin(View view) {

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}

