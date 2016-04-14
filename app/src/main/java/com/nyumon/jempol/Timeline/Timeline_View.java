package com.nyumon.jempol.Timeline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ActionBarContainer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.nyumon.jempol.ChatArrayAdapter;
import com.nyumon.jempol.ChatMessage;
import com.nyumon.jempol.R;

import org.w3c.dom.Text;

/**
 * Created by fajar on 14/04/16.
 */
public class Timeline_View extends AppCompatActivity {
    private Toolbar toolbar;
    int total = 0;
    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText msg, chatText;
    private ImageView tet;
    private Button send;
    private boolean side = false;
    TextView name;
    String text;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final ImageView like = (ImageView) findViewById(R.id.like);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (TextView)findViewById(R.id.person_name);
        text = name.getText().toString();

        setTitle("Komentar");
        send = (Button) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);
        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

        final ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.arrows);
        Intent i = getIntent();

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView tot = (TextView) findViewById(R.id.etlike);
                total += 1;
                tot.setText(String.valueOf(total));
            }
        });
    }

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString(),tet));
        chatText.setText("");/*
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());*/

        side = !side;
        return true;
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


}
