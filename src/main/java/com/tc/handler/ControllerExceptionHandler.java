package com.tc.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: tianchao
 * @Date: 2020/1/1 15:10
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionhander(HttpServletRequest request,Exception e) throws Exception {
        log.error("Request URL : {} Exception: {}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
