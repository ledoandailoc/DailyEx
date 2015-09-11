package com.example.ledoa.dailyexsuper.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.adapter.MessageAdapter;
import com.example.ledoa.dailyexsuper.listener.MainListener;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Data;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;
import com.example.ledoa.dailyexsuper.util.CommonUtils;
import com.example.ledoa.dailyexsuper.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends Activity {

    private static final String ARG_LIST_USER = "key_list_user";
    private static final String ARG_LIST_RECENT = "key_list_recent";
    private static final String ARG_LIST_FRIEND = "key_list_friend";
    private static final String GET_MESSAGE_BY_USER_ID = "key_get_message_by_userId";
    private static final String GET_MESSAGE_BY_ROOM_ID = "key_get_message_by_roomId";

    private MainListener mListener;
    private ArrayList<Data> mList = new ArrayList<>();
    private MessageAdapter mAdapter;
    private ListView mLvMessage;
    private EditText mEdtMessage;
    private ImageButton mBtnSend;
    private boolean checkHideMore = true;

    private TextView tvTitle;
    private ImageButton ibAddUser;
    private String mRoomId;
    private String userIDrequest;
    private List<User> mListUser;
    private List<User> mListFriends;
    private List<User> mListPickedFriends;



    private ListView mlvPopupListFriends;
    private GridView mgvPickerFriends;
    private PopupWindow mPopupListFriends = new PopupWindow();
    private View popupView;

    private Uri mImageCaptureUri;
    private String path;
    private String timeStamp;
    private String mGetMessageStyle;
    private String title = "";





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEdtMessage = (EditText)findViewById(R.id.edt_chat_message);
        mBtnSend = (ImageButton)findViewById(R.id.btn_chat_send);
        mLvMessage = (ListView)findViewById(R.id.lv_chat);
        //mLvMessage.setDivider(null);
        mAdapter = new MessageAdapter(this, mList);
        //mLvMessage.setAdapter(mAdapter);

 /*       mLvMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mEdtMessage.clearFocus();
                Log.d("Touch layout", null, null);

                checkHideMore = true;
                return false;
            }
        });*/
     /*   mEdtMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!checkHideMore) {
                    checkHideMore = true;
                }
                return false;
            }
        });*/
 /*       mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
                if (mRoomId != null) {
                    mList.clear();
                }
            }
        });*/

    }


    private void sendTextMessage() {
       /* if (mEdtMessage.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please type your message", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject messageObject = new JSONObject();
            try {
                // send chat to server by socket
                messageObject.put("senderId", mCurrentUser._id);
                if (mListUser.size() > 2) {
                    messageObject.put("isGroup", true);
                } else {
                    messageObject.put("isGroup", false);
                }
                messageObject.put("message", mEdtMessage.getText().toString().trim());
                messageObject.put("type", Constant.CHAT_TYPE_TEXT);
                messageObject.put("senderName", mCurrentUser.username);

                if (mRoomId != null) {
                    messageObject.put("roomId", mRoomId);
                } else {
                    JSONArray memberArray = new JSONArray();
                    for (User user : mListUser) {
                        memberArray.put(user._id);
                    }
                    messageObject.put("members", memberArray);
                }

                String sequence = String.valueOf(CommonUtils.getCurrentTimeMillis());
                messageObject.put("sequence", sequence);



                buildTypeDisplay();
                getApplication().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mLvMessage.smoothScrollToPosition(mList.size());
                    }
                });
                // reset text in edit text
                mEdtMessage.setText(null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
    }





    private void buildTypeDisplay() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).type == Constant.CHAT_TYPE_TEXT) {
                if (i == 0) {
                    if (true) {
                        mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                    } else {
                        mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                    }
                } else {
                    if (false) {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_RIGHT_WITHOUT_AVATAR;
                        } else {
                            mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                        }
                    } else {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i - 1).typeDisplay = Constant.CHAT_TYPE_TEXT_LEFT_WITHOUT_AVATAR;
                            mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        } else {
                            mList.get(i).typeDisplay = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        }
                    }
                }
            } else if (mList.get(i).type == Constant.CHAT_TYPE_IMAGE) {
                if (true) {
                    mList.get(i).typeDisplay = Constant.CHAT_TYPE_IMAGE_RIGHT_WITH_AVATAR;
                } else {
                    mList.get(i).typeDisplay = Constant.CHAT_TYPE_IMAGE_LEFT_WITH_AVATAR;
                }
            }
        }
    }




    public List<User> getListMembers() {
        return mListUser;
    }




}


