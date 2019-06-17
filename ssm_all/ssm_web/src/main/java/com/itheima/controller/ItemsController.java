package com.itheima.controller;

import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("itemsController")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping("/findById")
    public ModelAndView findById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Items item = itemsService.findById(id);
        modelAndView.addObject("item",item);
        modelAndView.setViewName("itemDetail");
        return modelAndView;
    }
}
