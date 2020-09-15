package com.uetty.nacos.spring.simple.service.impl;

import com.uetty.nacos.spring.simple.dao.UserDao;
import com.uetty.nacos.spring.simple.entity.User;
import com.uetty.nacos.spring.simple.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    UserDao userDao;

    @Override
    public User getAdminUser() {
        return userDao.getByUsername("admin");
    }
}
