package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.ChatAdapter;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ItemChat;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends Activity {

    int DungLai=-1;
    int MyAccountId,FriendAccountId;
    String HienThi;



    Button sendmessage;
    EditText edText;
    String itemText[] = {"Xin chao:D", "Xin chao!", "Rat vui duoc gap ban"};
    ArrayList<ItemChat> listChat;
    ListView listViewChat;
    ChatAdapter adapterChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        TextView actionbar_tvTitile = (TextView)findViewById(R.id.actionbar_tvTitile);
        actionbar_tvTitile.setText("Trò truyện");



        sendmessage = (Button)findViewById(R.id.button);
        sendmessage.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_LONG).show();
                edText.setText("");
            }
        });

        listChat = new ArrayList<ItemChat>();
        listViewChat = (ListView)findViewById(R.id.listView);
		

        adapterChat = new ChatAdapter(MessageActivity.this, R.layout.custom_layout_chat, listChat);
        listViewChat.setAdapter(adapterChat);

        edText = (EditText)findViewById(R.id.editText);



    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DungLai = 1;
        this.finish();
    }
}
