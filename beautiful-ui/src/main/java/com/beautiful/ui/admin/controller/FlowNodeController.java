package com.beautiful.ui.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.pagemodel.NodeVO;
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

@Controller
@RequestMapping("/admin/flownode")
public class FlowNodeController {


    @Autowired
    private INodeGroupService nodeGroupService;
    @Autowired
    private INodeService nodeService;

    @RequestMapping(value = "/node/list", method = RequestMethod.GET)
    public String nodeGroupList(HttpServletRequest request, HttpServletResponse response) {


        return "admin/node/list";
    }

    @RequestMapping(value = "/node/list", method = RequestMethod.POST)
    @ResponseBody
    public TableDataSet nodeGroupList(@RequestBody JSONArray jsonArray, HttpServletRequest request) {
        JSONObject jsonObject = TableDataSet.covertJsonArrayToJsonObject(jsonArray);
        int sEcho = Integer.valueOf(jsonObject.getString("sEcho"));
        int iDisplayStart = Integer.valueOf(jsonObject.getString("iDisplayStart"));//起始索引
        int iDisplayLength = Integer.valueOf(jsonObject.getString("iDisplayLength"));//显示页数
        String query = jsonObject.getString("sSearch");
        TableDataSet<NodeVO> tableDataSet = nodeService.findNodeInfoByQueryPager(query, iDisplayStart, iDisplayLength);
        return tableDataSet;
    }

    @RequestMapping(value = "/node/save", method = RequestMethod.GET)
    public String nodeGroupSave(HttpServletRequest request, HttpServletResponse response) {

        return "admin/node/save";
    }


}
