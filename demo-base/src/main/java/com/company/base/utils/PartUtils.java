package com.company.base.utils;

import javax.servlet.http.Part;

/**
 * 针对javax.servlet.http.Part接口操作。
 * 该接口是servlet3.0新增接口，用于上传文件操作
 */
public class PartUtils {
    private static final String HEAD_CONTENT = "content-disposition";
    private static final String FILE_KEY = "filename=\"";

    public static String getFileName(Part part){
        //格式:form-data; name="file"; filename="QQ图片20160809091952.jpg"
        String headContent = part.getHeader(HEAD_CONTENT);

        int startPos = headContent.indexOf(FILE_KEY) + FILE_KEY.length();
        if(startPos < 0){
            return null;
        }

        int endPos = headContent.indexOf("\"",startPos+1);

        return headContent.substring(startPos,endPos);
    }
}
