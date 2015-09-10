package com.example.ledoa.dailyexsuper.connection;


import com.example.ledoa.dailyexsuper.MainApplication;
import com.example.ledoa.dailyexsuper.R;

public class ApiLink {

    public static String getLoginLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_login);
    }

    public static String getSignUpLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_signup);
    }

    public static String getUserLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_user);
    }

    public static String getAllRoomLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_all_room);
    }

    public static String getAllUserLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_all_user);
    }

    public static String getRoomMessageByRoomIdLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_room_message_by_room_id);
    }

    public static String getRoomMessageByUserIdLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_room_message_by_user_id);
    }

    public static String createEventLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_create_event);
    }

    public static String getEventByIdLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_event_by_id);
    }

    public static String getUploadFileLink() {
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_upload_file);
    }

    public static String getChatHistoryLink(){
        return MainApplication.getContext().getString(R.string.base_url) + MainApplication.getContext().getString(R.string.api_get_chat_history);
    }
}
