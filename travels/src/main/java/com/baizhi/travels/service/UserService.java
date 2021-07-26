package com.baizhi.travels.service;

import com.baizhi.travels.entity.User;

public interface UserService {


    public User login(User user);

    public void register(User user);
}
