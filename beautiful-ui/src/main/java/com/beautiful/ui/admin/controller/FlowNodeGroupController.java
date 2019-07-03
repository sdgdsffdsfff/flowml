package com.beautiful.ui.admin.controller;

import cn.ms.sequence.Sequence;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beautiful.ui.core.Json;
import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.model.type.Node;
import com.beautiful.ui.core.model.type.NodeGroup;
import com.beautiful.ui.core.service.INodeGroupService;
import com.beautiful.ui.core.service.INodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin/flownode")
public class FlowNodeGroupController {


    @Autowired
    private INodeGroupService nodeGroupService;
    @Autowired
    private INodeService nodeService;

    @RequestMapping(value = "/nodeGroup/list", method = RequestMethod.GET)
    public String nodeGroupList(HttpServletRequest request, HttpServletResponse response) {


        return "admin/nodegroup/list";
    }

    @RequestMapping(value = "/nodeGroup/list", method = RequestMethod.POST)
    @ResponseBody
    public TableDataSet nodeGroupList(@RequestBody JSONArray jsonArray, HttpServletRequest request) {
        JSONObject jsonObject = TableDataSet.covertJsonArrayToJsonObject(jsonArray);
        int sEcho = Integer.valueOf(jsonObject.getString("sEcho"));
        int iDisplayStart = Integer.valueOf(jsonObject.getString("iDisplayStart"));//起始索引
        int iDisplayLength = Integer.valueOf(jsonObject.getString("iDisplayLength"));//显示页数
        String query = jsonObject.getString("sSearch");
        TableDataSet<NodeGroup> tableDataSet = nodeGroupService.findListByQueryPager(query, iDisplayStart, iDisplayLength);
        return tableDataSet;
    }

    @RequestMapping(value = "/nodeGroup/save", method = RequestMethod.GET)
    public String nodeGroupSave(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            request.setAttribute("id", id.trim());
            NodeGroup nodeGroup = nodeGroupService.findById(id.trim());
            request.setAttribute("nodeGroup", nodeGroup);
        }
        return "admin/nodegroup/save";
    }


    @RequestMapping(value = "/nodeGroup/save", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupSave(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String name = jsonObject.getString("name");
        String id = jsonObject.getString("id");
        String comment = jsonObject.getString("comment");
        if (StringUtils.isNotBlank(id)) {
            //更新
            NodeGroup nodeGroup = nodeGroupService.findById(id);
            nodeGroup.setUpdateTime(System.currentTimeMillis());
            nodeGroup.setComment(comment);
            nodeGroup.setName(name);
            nodeGroupService.saveorupdate(nodeGroup);
        } else {
            //保存
            NodeGroup nodeGroup = new NodeGroup();
            nodeGroup.setName(name);
            nodeGroup.setComment(comment);
            nodeGroup.setCreateTime(System.currentTimeMillis());
            nodeGroup.setId(String.valueOf(Sequence.INSTANCE.nextId()));
            nodeGroup.setSort(nodeGroupService.findMaxSort() + 1);
            nodeGroupService.save(nodeGroup);
        }
        return Json.toSuccessJson();
    }

    @RequestMapping(value = "/nodeGroup/delete", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupDelete(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String id = jsonObject.getString("id");
        if (StringUtils.isNotBlank(id)) {
            //check 查找是否有绑定的node 节点
            List<Node> nodes = nodeService.findNodesByGroupId(id);
            if (nodes != null && !nodes.isEmpty()) {
                return Json.toFailJson("当前节点类型下绑定了节点，请先接触绑定后再来删除该节点类型!");
            }
            nodeGroupService.hardDeleteById(id);
        } else {
            return Json.toEmptyJson();
        }
        return Json.toSuccessJson();
    }

    @RequestMapping(value = "/nodeGroup/changeSort", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupChangeSort(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String id = jsonObject.getString("id");
        String type = jsonObject.getString("type");
        if (StringUtils.isNotBlank(id)) {
            //升息
            NodeGroup current = nodeGroupService.findById(id);
            NodeGroup near;
            if ("up".equalsIgnoreCase(type)) {
                near = nodeGroupService.findByNearUp(current.getSort());
            } else {
                near = nodeGroupService.findByNearDown(current.getSort());
            }
            if (near == null) {
                if ("up".equalsIgnoreCase(type)) {
                    return Json.toFailJson("已经到最顶层了");
                } else {
                    return Json.toFailJson("已经到最底层了");
                }
            } else {
                //
                Integer csort = current.getSort();
                current.setSort(near.getSort());
                near.setSort(csort);
                nodeGroupService.saveorupdate(current);
                nodeGroupService.saveorupdate(near);
            }

        } else {
            return Json.toEmptyJson();
        }
        return Json.toSuccessJson();
    }


}
