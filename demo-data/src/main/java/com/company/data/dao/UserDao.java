package com.company.data.dao;

import com.company.data.model.User;
import com.demo.base.mybatis.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    User qryById(Long id);
    List<User> pageUser(@Param("name")String name,Page<User> page);
}
