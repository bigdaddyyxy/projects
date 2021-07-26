package com.baizhi.dao;

import com.baizhi.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpDAO {

    List<Employee> findAll();

    void save(Employee employee);

    void deleteById(String id);

    Employee findOne(String id);

    void update(Employee employee);

}
