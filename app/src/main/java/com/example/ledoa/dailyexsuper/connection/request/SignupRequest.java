package com.example.ledoa.dailyexsuper.connection.request;

import com.example.ledoa.dailyexsuper.connection.base.BaseRequest;
import com.example.ledoa.dailyexsuper.connection.response.SignupResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class SignupRequest extends BaseRequest<SignupResponse> {

    public SignupRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected SignupResponse handleData(JSONObject json) throws JSONException {
        SignupResponse data = new SignupResponse();
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
            data.data = new User(json.getJSONObject("data"));
        }
        return data;
    }
}
