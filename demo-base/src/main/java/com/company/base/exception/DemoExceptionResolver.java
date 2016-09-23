package com.company.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class DemoExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(DemoExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }

        if (ex instanceof DemoException) {
            pw.write(ex.getMessage());
        } else {
            LOG.error("系统异常",ex);
            pw.write("系统异常，请联系管理员");
        }

        return new ModelAndView();
    }
}
