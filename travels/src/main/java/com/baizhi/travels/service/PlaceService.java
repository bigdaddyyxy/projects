package com.baizhi.travels.service;

import com.baizhi.travels.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceService {
    //    //参数： 当前页 ， 每页显示记录数
//    List<Place> findByPage(Integer page, Integer rows);
//
//    //查询总条数
//    Integer findTotals();
//
    //保存景点方法
    void save(@Param("place") Place place);

    //删除景点
    void delete(String id);

    //查询一个景点信息
    Place findOne(String id);

    //修改一个景点信息
    void update(Place place);

    List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId);

    Integer findByProvinceIdCounts( String provinceId);

}