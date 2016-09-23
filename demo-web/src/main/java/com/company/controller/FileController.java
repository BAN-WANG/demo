package com.company.controller;

import com.company.base.exception.DemoException;
import com.company.base.utils.PartUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@Controller
@RequestMapping("file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private static final long FILE_MAX_SIZE= 5*1025*1024;//5M

    @RequestMapping("upload")
    @ResponseBody
    public String upload(String name ,Part file) throws IOException,DemoException{
        if(file.getSize() > FILE_MAX_SIZE){
            throw new DemoException("文件过大");
        }

        //获取文件名
        String fileName = PartUtils.getFileName(file);

        InputStream is = file.getInputStream();
        FileUtils.copyInputStreamToFile(is, new File("D:\\upload\\" + fileName));

        return name+":"+fileName;
    }

    @RequestMapping("/download")
    public void download(String fileKey,HttpServletResponse response) throws IOException,DemoException{
        String fileName = fileKey;
        File file = new File("D:\\upload\\"+fileName);
        if(!file.exists()){
            throw new DemoException("文件不存在");
        }

        //设置文件名，以及中文名解决
        response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));
        OutputStream os = response.getOutputStream();

        //copy方法中对各"流"进行close()
        FileUtils.copyFile(file,os);
    }
}
