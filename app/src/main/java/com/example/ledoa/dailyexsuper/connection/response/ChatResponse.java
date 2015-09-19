package com.example.ledoa.dailyexsuper.connection.response;

import com.example.ledoa.dailyexsuper.connection.base.BaseResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Chat;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Data;
import com.example.ledoa.dailyexsuper.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatResponse extends BaseResponse {

    public Chat data;

    public ChatResponse() {
        data = new Chat();
    }

    public ChatResponse(JSONObject json) throws JSONException {
        if (json.has("success")) {
            this.success = json.getBoolean("success");
        }
        if (json.has("statusCode")) {
            this.statusCode = json.getInt("statusCode");
        }
        if (json.has("message")) {
            this.message = json.getString("message");
        }
        if (this.success && this.statusCode == Constant.STATUS_CODE_SUCCESS) {
            JSONObject jo = json.getJSONObject("data");
            this.data = new Chat(jo);
        } else {
            this.data = new Chat();
        }
    }

    public ChatResponse(String jsonString) throws JSONException {
        this(new JSONObject(jsonString));
    }

}
