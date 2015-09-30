package com.example.ledoa.dailyexsuper.connection.request;

import com.example.ledoa.dailyexsuper.connection.base.BaseRequest;
import com.example.ledoa.dailyexsuper.connection.response.ListThongBaoResponse;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class GetListThongBaoRequest extends BaseRequest<ListThongBaoResponse>{
    public GetListThongBaoRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected ListThongBaoResponse handleData(JSONObject json) throws JSONException {

        ListThongBaoResponse data = new ListThongBaoResponse();
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
                data.data.add(new ThongBao(jData.getJSONObject(i)));
            }
        }
        return data;
    }
}
