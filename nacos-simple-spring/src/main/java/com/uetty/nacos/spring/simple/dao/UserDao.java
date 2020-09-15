package com.uetty.nacos.spring.simple.dao;

import com.github.pagehelper.Page;
import com.uetty.nacos.spring.simple.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getById(String id);

    User getByUsername(String userName);

    boolean insert(User user);

    boolean update(User user);

    boolean delete(String id);

    Page<User> getAllUser();

}