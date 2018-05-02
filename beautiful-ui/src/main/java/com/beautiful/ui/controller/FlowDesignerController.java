package com.beautiful.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: zhuyuping
 * @CreateDate: 2018/4/28 18:59
 **/
@Controller
@RequestMapping("/flowdesigner")
public class FlowDesignerController {


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, HttpServletResponse response) {


        return "flowdesigner/create";
    }


}
