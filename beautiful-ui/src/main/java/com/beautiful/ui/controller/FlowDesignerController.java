package com.beautiful.ui.controller;

import com.alibaba.fastjson.JSONObject;
import com.beautiful.ui.core.Json;
import com.beautiful.ui.core.model.Node;
import com.beautiful.ui.core.model.NodeGroup;
import com.beautiful.ui.core.service.INodeGroupService;
import com.beautiful.ui.core.service.INodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: zhuyuping
 * @CreateDate: 2018/4/28 18:59
 **/
@Controller
@RequestMapping("/flowdesigner")
public class FlowDesignerController {

    @Autowired
    private INodeGroupService nodeGroupService;
    @Autowired
    private INodeService nodeService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(HttpServletRequest request, HttpServletResponse response) {


        return "flowdesigner/create";
    }

    @RequestMapping(value = "/listNodeGroup", method = RequestMethod.POST)
    @ResponseBody
    public Json listNodeGroup(HttpServletRequest request, HttpServletResponse response) {
        List<NodeGroup> nodeGroupList = nodeGroupService.findAll();
        return Json.toSuccessJson(nodeGroupList);
    }

    @RequestMapping(value = "/listNode", method = RequestMethod.POST)
    @ResponseBody
    public Json listNode(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String groupId = jsonObject.getString("groupId");
        List<Node> nodeList = nodeService.findNodesByGroupId(groupId);
        return Json.toSuccessJson(nodeList);
    }












}
