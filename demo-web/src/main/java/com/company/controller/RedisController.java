package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("redis")
public class RedisController {
    @Resource(name = "redisTemplate")
    private ValueOperations<String,String> stringOperations;

    //@Autowired
    //private StringRedisTemplate redisTemplate;

    @RequestMapping("setKeyValue")
    @ResponseBody
    public String setKeyValue(String key ,String value){
        //ValueOperations<String,String> stringOperations = redisTemplate.opsForValue();
        stringOperations.set(key,value);
        return stringOperations.get(key);
    }
}
