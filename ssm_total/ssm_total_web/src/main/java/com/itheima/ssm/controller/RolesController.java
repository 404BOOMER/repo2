package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RolesController {
    @Autowired
    private RolesService rolesService;
    //查询所有角色
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Role> roleList = rolesService.findAll();
        mv.setViewName("role-list");
        mv.addObject("roleList",roleList);
        return mv;
    }
    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) throws  Exception{
        rolesService.save(role);
        return "redirect:findAll.do";
    }
}
