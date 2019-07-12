package com.xia.demo.utils;

@FunctionalInterface
public interface IAction<T> {
    void run(T param);
}