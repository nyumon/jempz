package com.nyumon.jempol;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by fajar on 26/03/16.
 */

public class RegisterActivity extends Activity {

    private Button btnLogin, btnRegister;
    private EditText etUsername, etNamaLengkap, etEmail, etPassword;

    private String username, fullname, email, password, token;
    private Integer role, status;
    private Timestamp join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin        = (Button) findViewById(R.id.btnLogin);
        btnRegister     = (Button) findViewById(R.id.btnLogin);

        etNamaLengkap   = (EditText) findViewById(R.id.etNamalengkap);
        etEmail         = (EditText) findViewById(R.id.etEmail);
        etUsername      = (EditText) findViewById(R.id.etUsername);
        etPassword      = (EditText) findViewById(R.id.etPassword);

    }

    public void invokeRegister(View view) {

        fullname    = etNamaLengkap.getText().toString();
        email       = etEmail.getText().toString();
        username    = etUsername.getText().toString();
        password    = etPassword.getText().toString();

        register(fullname, email, username, password);

    }

    private void register(String fullname, String email, String username, final String password) {

        class RegisterAsync extends AsyncTask<String, Void, String> {

            private Dialog registerDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                registerDialog = ProgressDialog.show(RegisterActivity.this, "Menyambungkan", "Mohon tunggu sebentar");
            }

            @Override
            protected String doInBackground(String... params) {

                String  ufull            = params[0];
                String  umail            = params[1];
                String  uname            = params[2];
                String  upass            = params[3];

                String result            = null;
                InputStream inputStream  = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("fullname", ufull));
                nameValuePairs.add(new BasicNameValuePair("email"   , umail));
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", upass));

                try {

                    HttpClient httpClient   = new DefaultHttpClient();
                    HttpPost httpPost       = new HttpPost("http://nyumon.hol.es/register.php");

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse   = httpClient.execute(httpPost);
                    HttpEntity httpEntity       = httpResponse.getEntity();
                    inputStream                 = httpEntity.getContent();

                    BufferedReader reader       = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder stringBuilder = new StringBuilder();

                    String line = null;

                    while((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    result = stringBuilder.toString();

                }
                catch (ClientProtocolException e) {
                    e.printStackTrace();
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                return result;

            }

            @Override
            protected void onPostExecute(String result) {

                String s = result.trim();
                registerDialog.dismiss();

                if(s.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(getApplicationContext(), "Anda berhasil mendaftar, silahkan masuk", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Data yang anda masukkan salah", Toast.LENGTH_LONG).show();
                }

            }

        }

        RegisterAsync registerAsync = new RegisterAsync();
        registerAsync.execute(fullname, email, username, password);

    }

    public void invokeLogin(View view) {

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}

