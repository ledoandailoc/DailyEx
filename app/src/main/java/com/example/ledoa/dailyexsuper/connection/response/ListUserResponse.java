package com.example.ledoa.dailyexsuper.connection.response;

import com.example.ledoa.dailyexsuper.connection.base.BaseResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserResponse extends BaseResponse {

    public List<User> data = new ArrayList<>();

}
