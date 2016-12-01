package com.company.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.company.api.dto.UserDTO;
import com.company.api.iapi.UserApi;
import com.company.data.model.User;
import com.company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wsyi on 2016/11/29.
 */
@Service
public class UserApiImpl implements UserApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public UserDTO qryById(Long id) {
        UserDTO userDTO = new UserDTO();
        User user = userService.qryUserById(id);
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }
}
