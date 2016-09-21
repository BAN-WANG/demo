package com.company.service;

import com.company.data.dao.UserDao;
import com.company.data.model.User;
import com.company.base.exception.DemoException;
import com.company.base.mybatis.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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

    @Transactional(rollbackFor = DemoException.class)
    public Boolean add(User user) throws DemoException{
        int count = userDao.add(user);

        if(count != 1){
            throw new DemoException("数据库更新不为1");
        }

        LOGGER.info("新增user主键id=>{}",user.getId());

        if(1 == 1) {
            throw new DemoException("测试事务事务是否回滚");
        }
        return true;
    }
}


