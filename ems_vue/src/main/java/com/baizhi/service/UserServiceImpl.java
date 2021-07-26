package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(User user) {
        User userDB = userDAO.findByUsername(user.getUsername());
        if(!ObjectUtils.isEmpty(userDB)){
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }else{
                throw new RuntimeException("密码错误");
            }
        }else{
            throw new RuntimeException("用户名不存在");
        }
    }

    @Override
    public void register(User user) {
        //根据输入的用户名判断用户是否存在
        User userDB = userDAO.findByUsername(user.getUsername());
        if (userDB==null){
            user.setStatus("已激活");
            user.setRegisterTime(new Date());
            userDAO.save(user);
        }else{
            throw new RuntimeException("用户名已存在");
        }

    }
}
