package com.nyumon.jempol;

/**
 * Created by fajar on 26/03/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoginActivity extends Activity {

    private Button   button_login, button_register;
    private EditText login_username, login_password;
    private TextView alert_login, forgot_password;

    private String username, password;
    public static final String USER_NAME = "USERNAME";

    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login    = (Button) findViewById(R.id.button_login);
        button_register = (Button) findViewById(R.id.button_register);

        login_username  = (EditText) findViewById(R.id.login_username);
        login_password  = (EditText) findViewById(R.id.login_password);

        alert_login     = (TextView)findViewById(R.id.alert_login);
        forgot_password = (TextView) findViewById(R.id.forgot_password);


    }

    public void invokeLogin(View view) {

        username = login_username.getText().toString();
        password = login_password.getText().toString();

        login(username, password);

    }

    public void invokeRegister(View view) {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    private void login(final String username, final String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loginDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loginDialog = ProgressDialog.show(LoginActivity.this, "Please Wait", "Waiting");
            }

            @Override
            protected String doInBackground(String... params) {

                String      uname       = params[0];
                String      passw       = params[1];

                InputStream inputStream = null;
                String      result      = null;

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", passw));

                try {
                    HttpClient   httpClient     = new DefaultHttpClient();
                    HttpPost     httpPost       = new HttpPost("http://nyumon.hol.es/login.php");

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse   = httpClient.execute(httpPost);
                    HttpEntity   httpEntity     = httpResponse.getEntity();
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
                loginDialog.dismiss();

                if(s.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }

            }

        }

        LoginAsync loginAsync = new LoginAsync();
        loginAsync.execute(username, password);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(LoginActivity.this);

            alertbox.setTitle("Do You Want To Exit ?");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // finish used for destroyed activity
                    exit();
                }
            });

            alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // Nothing will be happened when clicked on no button
                    // of Dialog
                }
            });

            alertbox.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
