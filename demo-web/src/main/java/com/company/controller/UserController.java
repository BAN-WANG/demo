package com.company.controller;

import com.company.aspectj.Log;
import com.company.data.model.User;
import com.company.service.UserService;
import com.company.base.exception.DemoException;
import com.company.base.mybatis.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("qryUserById")
    @ResponseBody
    @Log
    public User qryUserById(Long id) throws DemoException{
        if(id == null){
            throw new DemoException("id为空");
        }

        return userService.qryUserById(id);
    }

    @RequestMapping("pageUser")
    @ResponseBody
    public Page<User> pageUser(String name,Page page) throws DemoException{
        if(!StringUtils.isEmpty(name)){
            name = "%"+name+"%";
        }

        return userService.pageUser(name,page);
    }

    @RequestMapping("add")
    @ResponseBody
    public Boolean add(User user) throws DemoException{
        return userService.add(user);
    }

}
