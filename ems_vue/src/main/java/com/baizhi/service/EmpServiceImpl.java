package com.baizhi.service;

import com.baizhi.dao.EmpDAO;
import com.baizhi.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDAO empDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Employee> findAll() {
        return empDAO.findAll();
    }

    @Override
    public void save(Employee employee) {
        empDAO.save(employee);
    }

    @Override
    public void deleteById(String id) {
        empDAO.deleteById(id);
    }

    @Override
    public Employee findOne(String id) {
        return empDAO.findOne(id);
    }

    @Override
    public void update(Employee employee) {
        empDAO.update(employee);
    }
}
