package com.jr.gitdemo.dao;

import com.jr.gitdemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);

    int insert(User user);



}