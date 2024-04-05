package com.example.labjee.services;

public interface GenericService<T> {
    public T getByCode(String code);
}
