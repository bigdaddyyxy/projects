package com.baizhi.travels.service;

import com.baizhi.travels.entity.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProvinceService {
    //参数： 当前页 ， 每页显示记录数
    List<Province> findByPage(Integer page, Integer rows);

    //查询总条数
    Integer findTotals();

    //保存省份方法
    void save(@Param("province") Province province);

    //删除省份
    void delete(String id);

    //查询一个省份信息
    Province findOne(String id);

    //修改一个省份信息
    void update(Province province);
}
