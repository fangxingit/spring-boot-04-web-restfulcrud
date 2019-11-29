package com.atguigu.springboot.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

public class MyErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String,Object> map = super.getErrorAttributes(requestAttributes,includeStackTrace);
        map.put("company","com.atguigu");
        Map<String,Object> ext = (Map<String,Object>) requestAttributes.getAttribute("ext",0);
        return super.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
