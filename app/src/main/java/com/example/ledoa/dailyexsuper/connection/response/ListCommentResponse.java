package com.example.ledoa.dailyexsuper.connection.response;

import com.example.ledoa.dailyexsuper.connection.base.BaseResponse;
import com.example.ledoa.dailyexsuper.sqlite.DTO.Comment;

import java.util.ArrayList;
import java.util.List;

public class ListCommentResponse extends BaseResponse {

    public List<Comment> data = new ArrayList<>();

}
