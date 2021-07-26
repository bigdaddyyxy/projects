package com.baizhi.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T,K> {

    void save(T t);

    void update(T t);

    void delete(K k);

    List<T> findAll();

    T findOne(K k);

    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer findTotals();


}
