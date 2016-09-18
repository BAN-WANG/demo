package com.company.service;

import com.company.data.dao.UserDao;
import com.company.data.model.User;
import com.demo.base.mybatis.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User qryUserById(Long id){
        return userDao.qryById(id);
    }

    public Page<User> pageUser(String name,Page<User> page){
        List<User> userList = userDao.pageUser(name,page);
        page.setDataList(userList);
        return page;
    }
}


