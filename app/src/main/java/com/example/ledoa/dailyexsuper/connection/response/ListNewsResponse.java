package com.example.ledoa.dailyexsuper.connection.response;

import com.example.ledoa.dailyexsuper.connection.base.BaseResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.News;

import java.util.ArrayList;
import java.util.List;

public class ListNewsResponse extends BaseResponse {

    public List<News> data = new ArrayList<>();

}
