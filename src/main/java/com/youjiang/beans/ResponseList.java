package com.youjiang.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseList<T> {
    private int code;
    private String msg;
    private Object data;
    private int pageNum;
    private int pageSize;
    private int totalNum;
}
