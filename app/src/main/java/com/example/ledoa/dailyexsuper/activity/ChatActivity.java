package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.MessageAdapter;
import com.example.ledoa.dailyexsuper.connection.ApiLink;
import com.example.ledoa.dailyexsuper.connection.base.Method;
import com.example.ledoa.dailyexsuper.connection.request.GetListMessageByUserIdRequest;
import com.example.ledoa.dailyexsuper.connection.response.ChatResponse;
import com.example.ledoa.dailyexsuper.connection.response.ListMessageResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Chat;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Data;

import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import com.example.ledoa.dailyexsuper.util.Constant;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ChatActivity extends Activity {
    List<User> mListUser;
    private EditText mEdtMessage;
    private User mCurrentUser;
    private Socket mSocket;
    private ArrayList<Chat> mList = new ArrayList<>();
    Button mBtnSend;
    String receiverId;
    ListView mLvMessage;
    private MessageAdapter mAdapter;
    private String userIDrequest;
    UserPref userPref;
    private GetListMessageByUserIdRequest mGetListMessageByUserIdRequest;

    private EventBus mBus = EventBus.getDefault();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView title  = (TextView)findViewById(R.id.actionbar_tvTitile);
        title.setText("Tin nhắn");

        receiverId = getIntent().getExtras().getString("UserId");
        mBtnSend = (Button)findViewById(R.id.mBtnSend);
        mLvMessage = (ListView)findViewById(R.id.listView);
        mAdapter = new MessageAdapter(this, mList);
        mLvMessage.setAdapter(mAdapter);
        userPref = new UserPref();
        mCurrentUser = userPref.getUser();

        mBtnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
            }
        });
        getMessageHistoryByUserId();

        mBus.register(this);


    }

    private void getMessageHistoryByUserId() {

        userIDrequest = receiverId;
        mGetListMessageByUserIdRequest = new GetListMessageByUserIdRequest(Method.GET, ApiLink.getRoomMessageByUserIdLink() + userIDrequest, null, null) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(ListMessageResponse entity, int statusCode, String message) {
                mList.clear();
                //Collections.reverse(entity.data);
                mList.addAll(entity.data);
                buildTypeDisplay();
                mAdapter.notifyDataSetChanged();

                        //mLvMessage.smoothScrollToPosition(mList.size());

                mLvMessage.setSelection(mList.size()-1);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(ChatActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListMessageByUserIdRequest.execute();
    }

    public void onEvent(ChatResponse chatResponse) {

        Chat data = chatResponse.data;

        mList.add(data);
        buildTypeDisplay();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mLvMessage.smoothScrollToPosition(mList.size());
            }
        });

    }


    private void buildTypeDisplay() {
        if(mList.size() < 1)
            Toast.makeText(ChatActivity.this, "New room", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < mList.size(); i++) {

            if (true) {
                if (i == 0) {
                    if (mList.get(i).user._id.equals(mCurrentUser._id)) {
                        mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                    } else {
                        mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                    }
                } else {
                    if (mList.get(i).user._id.equals(mCurrentUser._id)) {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i - 1).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITHOUT_AVATAR;
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                        } else {
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                        }
                    } else {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i - 1).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITHOUT_AVATAR;
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        } else {
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        }
                    }
                }
            }
        }
    }
    private void sendTextMessage() {
        mListUser =  new ArrayList<User>();
        mEdtMessage = (EditText)findViewById(R.id.edt_chat_message);

        mCurrentUser = userPref.getUser();
        mSocket = MainApplication.getMySocket().getSocket();

        if (mEdtMessage.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please type your message", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject messageObject = new JSONObject();
            try {
                // send chat to server by socket
                messageObject.put("userId", mCurrentUser._id);

                messageObject.put("message", mEdtMessage.getText().toString().trim());

                messageObject.put("senderName", mCurrentUser.username);
                messageObject.put("targetId", receiverId);



                //String sequence = String.valueOf(CommonUtils.getCurrentTimeMillis());


                mSocket.emit(Constant.SOCKET_EVENT_CHAT, messageObject);
                // add item into list
                Data data = new Data();

                data.message = mEdtMessage.getText().toString().trim();
                data.userId = mCurrentUser._id;
                //data.sequence = sequence;
              /*  mList.add(data);
                buildTypeDisplay();
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mLvMessage.smoothScrollToPosition(mList.size());
                    }
                });*/
                // reset text in edit text
                mEdtMessage.setText(null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
