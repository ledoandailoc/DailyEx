package com.example.ledoa.dailyexsuper.socketio;

import android.app.Activity;
import android.nfc.Tag;
import android.util.Log;

import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;
import com.example.ledoa.dailyexsuper.connection.response.ChatResponse;

import com.example.ledoa.dailyexsuper.util.Constant;
import com.example.ledoa.dailyexsuper.util.UserPref;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;

import java.net.URISyntaxException;

import de.greenrobot.event.EventBus;


public class MySocket {

    private static final String TAG = MySocket.class.getSimpleName();

    private Socket mSocket;
    private Activity mActivity;
    private EventBus mBus = EventBus.getDefault();

    public MySocket(Activity activity) {
        this.mActivity = activity;
    }

    public Socket getSocket() {
        return mSocket;
    }

    public void connectSocket() {
        String s = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWY1MmNlNWE5NTZiOGRjMTZiYzk0MjEiLCJ1c2VybmFtZSI6ImFuaHR1YW4iLCJhdmF0YXIiOiJodHRwczovL3MzLmFtYXpvbmF3cy5jb20vU2l5YXBsYWNlRGV2L21hbGUucG5nIiwiZ2VuZGVyIjoxLCJyb2xlIjoyLCJpYXQiOjE0NDIxNDgxNjl9.STqDix0m4AOhQPeELPeBk5SdPafatnW8DeidBjnJwOw";
        String dailoc = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWY1MzQ3MzIzNGMxMGE4MTM1NWRmMDkiLCJ1c2VybmFtZSI6ImRhaWxvYyIsImF2YXRhciI6Imh0dHBzOi8vczMuYW1hem9uYXdzLmNvbS9TaXlhcGxhY2VEZXYvbWFsZS5wbmciLCJnZW5kZXIiOjEsInJvbGUiOjIsImlhdCI6MTQ0MjIzOTM5N30.F6dWlqH1m9CObfBucu3Xw3R0jF0iapHW6dbq98vBe5o";
        String a = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWY1MzQ3MzIzNGMxMGE4MTM1NWRmMDkiLCJ1c2VybmFtZSI6ImRhaWxvYyIsImF2YXRhciI6Imh0dHBzOi8vczMuYW1hem9uYXdzLmNvbS9TaXlhcGxhY2VEZXYvbWFsZS5wbmciLCJnZW5kZXIiOjEsInJvbGUiOjIsImlhdCI6MTQ0MjI0MDQ1N30.SeuDifDMVnqVMt_E7Lvibj_hC0Omi2ub8LKeNSWSwCE";
                    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NWY1MmNlNWE5NTZiOGRjMTZiYzk0MjEiLCJ1c2VybmFtZSI6ImFuaHR1YW4iLCJhdmF0YXIiOiJodHRwczovL3MzLmFtYXpvbmF3cy5jb20vU2l5YXBsYWNlRGV2L21hbGUucG5nIiwiZ2VuZGVyIjoxLCJyb2xlIjoyLCJpYXQiOjE0NDIxNDgxNjl9.STqDix0m4AOhQPeELPeBk5SdPafatnW8DeidBjnJwOw

        UserPref userPref = new UserPref();
        /*Log.d(TAG, userPref.getUser().token);*/
        try {
            mSocket = IO.socket(MainApplication.getContext().getString(R.string.base_url) + "/?token=" + userPref.getUser().token);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout);
            mSocket.on(Socket.EVENT_CONNECT, onConnect);
            mSocket.on(Constant.SOCKET_EVENT_JOIN, onJoinRoom);
            mSocket.on(Constant.SOCKET_EVENT_ADD, onAddUser);
            mSocket.on(Constant.SOCKET_EVENT_LEAVE, onLeaveRoom);
            mSocket.on(Constant.SOCKET_EVENT_CHANGE_ROOM_TITLE, onChangeRoomTitle);
            mSocket.on(Constant.SOCKET_EVENT_CHAT, onChat);
            mSocket.on("addFriend", onAddFriend);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public void disconnectSocket() {
        mSocket.disconnect();
        if (mSocket != null) {
            mSocket.disconnect();
            mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout);
            mSocket.off(Socket.EVENT_CONNECT, onConnect);
            mSocket.off(Constant.SOCKET_EVENT_JOIN, onJoinRoom);
            mSocket.off(Constant.SOCKET_EVENT_ADD, onAddUser);
            mSocket.off(Constant.SOCKET_EVENT_LEAVE, onLeaveRoom);
            mSocket.off(Constant.SOCKET_EVENT_CHANGE_ROOM_TITLE, onChangeRoomTitle);
            mSocket.off(Constant.SOCKET_EVENT_CHAT, onChat);
            mSocket.off("addFriend", onAddFriend);
        }
    }

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "Connect socket error");
        }
    };

    private Emitter.Listener onConnectTimeout = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "Connect socket timeout");
        }
    };

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "Connect socket success");
        }
    };

    private Emitter.Listener onJoinRoom = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.d(TAG, "onJoinRoom");
        }
    };

    private Emitter.Listener onAddUser = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.d(TAG, "onAddUser");
        }
    };

    private Emitter.Listener onLeaveRoom = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.d(TAG, "onLeaveRoom");
        }
    };

    private Emitter.Listener onChangeRoomTitle = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.d(TAG, "onChangeRoomTitle");
        }
    };

    private Emitter.Listener onChat = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args[0] != null) {
                String result = args[0].toString();
                Log.d(TAG, "recive: "+ result);
               try {
                    ChatResponse chatResponse = new ChatResponse(result);
                    mBus.post(chatResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Emitter.Listener onAddFriend = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args[0] != null) {
                String result = args[0].toString();
                Log.d(TAG, "AddFriend: "+ result);
              /*  try {
                    ChatResponse chatResponse = new ChatResponse(result);
                    mBus.post(chatResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }
    };


}
