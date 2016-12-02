package com.company.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.company.api.dto.UserDTO;
import com.company.api.iapi.UserApi;
import com.company.base.exception.DemoException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("user")
public class UserController {
    @Reference
    private UserApi userApi;

    @RequestMapping("qryUserById")
    @ResponseBody
    public UserDTO qryUserById(Long id) throws DemoException{
        if(id == null){
            throw new DemoException("id为空");
        }

        return userApi.qryById(id);
    }

}
