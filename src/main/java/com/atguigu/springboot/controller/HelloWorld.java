package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class HelloWorld {

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello(@RequestParam("user") String user){

        if("aaa".equals(user)){
             throw new UserNotExistsException();
        }
        return "HelloWorld";
    }

    /**
     *  第一种方式是在Controller中配置首页的访问路径，
     *  页面多了，总配置不方便
     *
     *  推荐第二种，在配置方法类中同一配置MymvcConfig
     * @return
     */
//    @RequestMapping({"/","/index"})
//    public String Index(){
//
//        return "login.html";
//
//    }
   // @ResponseBody
    @RequestMapping("/success1")
    public String success(Map<String,Object> map){
        map.put("hello","你好！");
        return "success1";
    }

}
