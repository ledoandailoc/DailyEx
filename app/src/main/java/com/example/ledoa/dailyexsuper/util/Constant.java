package com.example.ledoa.dailyexsuper.util;

public class Constant {

    public enum DrawerMenuItemId {

        Event(1), Notification(2), Message(3), Profile(4), Logout(5);

        private int value;

        DrawerMenuItemId(int value) {
            this.value = value;
        }
    }

    public static final long DELAY_ON_DRAWER_CLICK = 250L;

    public static final int STATUS_CODE_SUCCESS = 200;
    public static final int JSON_PARSE_ERROR_CODE = 1002;

    // SOCKET EVENT
    public static final String SOCKET_EVENT_JOIN = "join";
    public static final String SOCKET_EVENT_ADD = "add";
    public static final String SOCKET_EVENT_LEAVE = "leave";
    public static final String SOCKET_EVENT_CHANGE_ROOM_TITLE = "changeRoomTitle";
    public static final String SOCKET_EVENT_CHAT = "chat";

    // CHAT TYPE
    public static final int CHAT_TYPE_TEXT = 1;
    public static final int CHAT_TYPE_IMAGE = 2;

    public static final int CHAT_TYPE_TEXT_LEFT_WITH_AVATAR = 0;
    public static final int CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR = 1;
    public static final int CHAT_TYPE_TEXT_LEFT_WITHOUT_AVATAR = 2;
    public static final int CHAT_TYPE_TEXT_RIGHT_WITHOUT_AVATAR = 3;
    public static final int CHAT_TYPE_IMAGE_RIGHT_WITH_AVATAR = 4;
    public static final int CHAT_TYPE_IMAGE_LEFT_WITH_AVATAR = 5;

    //CHOOSE IMAGE FROM....
    public static final int PICK_FROM_CAMERA = 1;
    public static final int PICK_FROM_FILE = 2;
}
