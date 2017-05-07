package com.youjiang.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by meng on 2017/5/5.
 */
@Getter
@Setter
@ToString
public class UserLog implements Serializable {
    private long id;
    private String loginId, sessionId, ipAddrV4, ipAddrV6, osName, osVersion, broName, broVersion, requestBody, description, other, method;
    private Date time;
}
