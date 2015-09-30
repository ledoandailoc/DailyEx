package com.example.ledoa.dailyexsuper.connection.request;


import com.example.ledoa.dailyexsuper.connection.base.BaseRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListMessageResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Chat;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GetListMessageByUserIdRequest extends BaseRequest<ListMessageResponse> {

    public GetListMessageByUserIdRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected ListMessageResponse handleData(JSONObject json) throws JSONException {
        ListMessageResponse data = new ListMessageResponse();
        if (json.has("success")) {
            data.success = json.getBoolean("success");
        }
        if (json.has("statusCode")) {
            data.statusCode = json.getInt("statusCode");
        }
        if (json.has("message")) {
            data.message = json.getString("message");
        }
        if (data.success) {
            data.data = new ArrayList<>();
            JSONArray jData = json.getJSONArray("data");
            for (int i = 0; i < jData.length(); i++) {
                data.data.add(new Chat(jData.getJSONObject(i)));
            }
        }
        return data;
    }
}
