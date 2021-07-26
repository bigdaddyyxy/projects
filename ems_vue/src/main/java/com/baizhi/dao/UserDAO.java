package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper   //用来创建DAO对象的
public interface UserDAO {

    void save(User user);

    User findByUsername(String username);
}
