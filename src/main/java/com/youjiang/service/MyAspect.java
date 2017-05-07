package com.youjiang.service;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by meng on 2017/5/3.
 */
@Aspect
public class MyAspect {
    @Around(value = "execution(* *..StudentServiceImpl.*(..))")
    public void before() {
        System.out.println("前置增强-环绕通知");
    }

    public void after() {
        System.out.println("后置增强");
    }
}
