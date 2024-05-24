package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

public interface IService<T> {
    void create(T object) throws ExceptionHandler;
    void remove(T object) throws ExceptionHandler;
    void update(T object) throws ExceptionHandler;
    void delete(T object) throws ExceptionHandler;
}
