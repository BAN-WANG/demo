package com.company.data.dao;

import com.company.data.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {
    User qryById(Long id);
}
