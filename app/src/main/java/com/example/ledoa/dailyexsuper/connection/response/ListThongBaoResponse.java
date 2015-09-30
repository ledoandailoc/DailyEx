package com.example.ledoa.dailyexsuper.connection.response;

import com.example.ledoa.dailyexsuper.connection.base.BaseResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class ListThongBaoResponse extends BaseResponse {

    public List<ThongBao> data = new ArrayList<>();

}
