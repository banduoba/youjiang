package com.youjiang.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by meng on 2017/5/3.
 */

public class ResponseObj<T> {
    public final static int OK = 1,FAILED =0,EMPTY =-1;
    public final static String OK_STR = "成功",FAILED_STR ="失败",EMPTY_STR ="数据为空";

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Object data;

}
