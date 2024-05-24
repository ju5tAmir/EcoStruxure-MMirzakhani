package com.se.ecostruxure_mmirzakhani.bll;

public interface IService<T> {
    void create(T object);
    void remove(T object);
    void update(T object);
    void delete(T object);
}
