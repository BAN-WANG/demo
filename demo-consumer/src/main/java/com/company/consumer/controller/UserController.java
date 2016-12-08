package com.company.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.company.api.dto.UserDTO;
import com.company.api.iapi.UserApi;
import com.company.base.exception.DemoException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.Date;


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

    @RequestMapping("addUser")
    @ResponseBody
    public Boolean addUser(String name,@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday) throws Exception{
        if(StringUtils.isEmpty(name))
            throw new DemoException("前置参数name为空");

        return userApi.add(name,birthday);
    }


}
