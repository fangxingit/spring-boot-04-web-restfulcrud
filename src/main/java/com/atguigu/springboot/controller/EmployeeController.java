package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao ;

    @Autowired
    DepartmentDao departmentDao;
    @GetMapping(value="/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        //thymeleaf默认会拼串
        //classpath:/templates/xxx.html;
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddEmp(Model model){

        Collection depts = departmentDao.getDepartments();
        model.addAttribute("depts",depts);
        return "emp/add";
    }

    /**
     * SpringMVC自动将请求参数和入参对象的属性一一进行绑定，
     * 要求：请求参数的名字和javebean入参对象里面的属性名是一样的
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        employeeDao.save(employee);
        //redirect:表示重定向一个地址， /代表当前项目路径
        //forword:表示转发的一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        Employee emp = employeeDao.get(id);
        Collection depts = departmentDao.getDepartments();
        model.addAttribute("emp",emp);
        model.addAttribute("depts",depts);
        return "/emp/add";
    }

    @PutMapping("/emp")
    public String Edit(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";

    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
