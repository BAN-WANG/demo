package com.company.controller;

import com.company.base.utils.SpringApplicationContextUtils;
import com.company.dto.UserDTO;
import com.company.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * @RestController
 */
@Controller
@RequestMapping("demo")
public class DemoController {
    private static final Logger LOG= LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("sayHello222")
    @ResponseBody
    public String sayHello(String name){
        LOG.info("前置参数name=>{}",name);

        //根据注解获取
        Map<String, Object> controllerBeanMap= SpringApplicationContextUtils.getBeansWithAnnotation(Controller.class);
        Iterator<Map.Entry<String, Object>> it = controllerBeanMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            LOG.info(entry.getKey()+"=>"+entry.getValue());
        }

        LOG.info("------------getBeansOfType-----------");
        //根据类型获取（此处IBaseService为接口）
        Map<String, IBaseService> serviceBeanMap= SpringApplicationContextUtils.getBeansOfType(IBaseService.class);
        Iterator<Map.Entry<String, IBaseService>> serviceBeanMapIt = serviceBeanMap.entrySet().iterator();
        while(serviceBeanMapIt.hasNext()){
            Map.Entry<String, IBaseService> entry = serviceBeanMapIt.next();
            LOG.info(entry.getKey()+"=>"+entry.getValue());
        }

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
