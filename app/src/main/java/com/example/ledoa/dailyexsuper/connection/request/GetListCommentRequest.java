package com.example.ledoa.dailyexsuper.connection.request;

import com.example.ledoa.dailyexsuper.connection.base.BaseRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListCommentResponse;
import com.example.ledoa.dailyexsuper.connection.response.ListThongBaoResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Comment;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GetListCommentRequest extends BaseRequest<ListCommentResponse>{
    public GetListCommentRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected ListCommentResponse handleData(JSONObject json) throws JSONException {

        ListCommentResponse data = new ListCommentResponse();
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
                data.data.add(new Comment(jData.getJSONObject(i)));
            }
        }
        return data;
    }
}
