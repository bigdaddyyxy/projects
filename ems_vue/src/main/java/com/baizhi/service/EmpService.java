package com.baizhi.service;

import com.baizhi.entity.Employee;

import java.util.List;

public interface EmpService {


    List<Employee> findAll();

    void save(Employee employee);

    void deleteById(String id);

    Employee findOne(String id);

    void update(Employee employee);

}
