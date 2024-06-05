package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

public interface IService<T> {
    boolean create(T object) throws ExceptionHandler;
    boolean update(T object) throws ExceptionHandler;
    boolean delete(T object) throws ExceptionHandler;
}
