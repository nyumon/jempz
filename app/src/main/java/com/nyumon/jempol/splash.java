package com.nyumon.jempol;

/**
 * Created by ricky on 24/03/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashmain);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(splash.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
