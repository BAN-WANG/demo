package com.company.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wsy on 2016/11/18.
 */
public class QuartzTask {
    private static final Logger LOG= LoggerFactory.getLogger(QuartzTask.class);

    public void run(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOG.info("系统调度时间："+sdf.format(new Date()));
    }
}
