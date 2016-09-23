package com.company.controller;

import com.company.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @RestController
 */
@Controller
@RequestMapping("demo")
public class DemoController {
    private static final Logger LOG= LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("sayHello")
    @ResponseBody
    public String sayHello(String name){
        LOG.info("前置参数name=>{}",name);
        return "你好,"+name+"!";
    }

    @RequestMapping("getUser")
    @ResponseBody
    public UserDTO getUser(@DateTimeFormat(pattern = "yyyy-MM-dd")Date birthday){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(10000000L);
        userDTO.setName("ss");
        userDTO.setBirthday(birthday);
        return userDTO;
    }
}
