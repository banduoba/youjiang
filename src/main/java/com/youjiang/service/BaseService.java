package com.youjiang.service;

/**
 * Created by meng on 2017/5/3.
 */
public interface BaseService<T> {

    void add(T t) throws Exception;

    T find(T t) throws Exception;
}
