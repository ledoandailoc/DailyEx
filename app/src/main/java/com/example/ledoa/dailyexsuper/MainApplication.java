package com.example.ledoa.dailyexsuper;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();

    private static Context mContext;
    //private static MySocket mMySocket;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        /*if (mMySocket != null) {
            mMySocket.disconnectSocket();
        }*/
    }

    public static Context getContext() {
        return mContext;
    }

/*    public static MySocket getMySocket() {
        return mMySocket;
    }*/

 /*   public static void setMySocket(MySocket mySocket) {
        mMySocket = mySocket;
    }*/

}