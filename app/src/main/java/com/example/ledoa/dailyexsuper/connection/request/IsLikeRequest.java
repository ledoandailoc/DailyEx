package com.example.ledoa.dailyexsuper.connection.request;

import com.example.ledoa.dailyexsuper.connection.base.BaseRequest;
import com.example.ledoa.dailyexsuper.connection.response.LikesResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.PublicData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class IsLikeRequest extends BaseRequest<LikesResponse> {

    public IsLikeRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected LikesResponse handleData(JSONObject json) throws JSONException {
        LikesResponse data = new LikesResponse();
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
            data.data = new PublicData(json.getJSONObject("data"));
        }
        return data;
    }
}
