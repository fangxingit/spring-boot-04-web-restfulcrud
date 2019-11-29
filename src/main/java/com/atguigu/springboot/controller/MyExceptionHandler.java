package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


    //第一种写法，客户端返回的都是json
//    @ResponseBody
////    @ExceptionHandler(UserNotExistsException.class)
////    public Map<String,Object> handlerException(Exception e){
////        Map<String,Object> map = new HashMap<>();
////        map.put("code","user.notexists");
////        map.put("message",e.getMessage());
////        return map;
////    }

    @ExceptionHandler(UserNotExistsException.class)
    public String handlException(Exception e, HttpServletRequest request){
        Map<String ,Object> map = new HashMap<>();
        /**
         * 传入自己的状态错误码
         * Integer status=(Integer) request
         * .getAttribute(javax.servlet.error.status_code);
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexists");
        map.put("message",e.getMessage());
        request.setAttribute("ext", map);
        //转发到 error
        return "forward:/error";

    }
}
