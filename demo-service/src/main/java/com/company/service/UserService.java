package com.company.service;

import com.company.data.dao.UserDao;
import com.company.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User qryUserById(Long id){
        return userDao.qryById(id);
    }
}


