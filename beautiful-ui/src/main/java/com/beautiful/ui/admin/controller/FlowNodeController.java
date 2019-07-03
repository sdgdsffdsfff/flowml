package com.beautiful.ui.admin.controller;

import cn.ms.sequence.Sequence;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beautiful.ui.core.Json;
import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.model.type.Node;
import com.beautiful.ui.core.model.type.NodeGroup;
import com.beautiful.ui.core.model.type.NodeInput;
import com.beautiful.ui.core.pagemodel.NodeVO;
import com.beautiful.ui.core.service.INodeGroupService;
import com.beautiful.ui.core.service.INodeService;
import com.google.common.collect.Lists;
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
    public TableDataSet nodeList(@RequestBody JSONArray jsonArray, HttpServletRequest request) {
        JSONObject jsonObject = TableDataSet.covertJsonArrayToJsonObject(jsonArray);
        int sEcho = Integer.valueOf(jsonObject.getString("sEcho"));
        int iDisplayStart = Integer.valueOf(jsonObject.getString("iDisplayStart"));//起始索引
        int iDisplayLength = Integer.valueOf(jsonObject.getString("iDisplayLength"));//显示页数
        String query = jsonObject.getString("sSearch");
        TableDataSet<NodeVO> tableDataSet = nodeService.findNodeInfoByQueryPager(query, iDisplayStart, iDisplayLength);
        return tableDataSet;
    }

    @RequestMapping(value = "/node/group/list", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupList(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        List<NodeGroup> nodeGroupList = nodeGroupService.findAll();
        return Json.toSuccessJson(nodeGroupList);
    }

    @RequestMapping(value = "/node/save", method = RequestMethod.GET)
    public String nodeSave(HttpServletRequest request, HttpServletResponse response) {

        return "admin/node/save";
    }

    @RequestMapping(value = "/node/save", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeSave(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String name = jsonObject.getString("name");
        String comment = jsonObject.getString("comment");
        String groupId = jsonObject.getString("groupId");
        Node node = new Node();
        JSONArray jsonArray = jsonObject.getJSONArray("prop");
        node.setName(name);
        node.setComment(comment);
        node.setGroupId(groupId);
        node.setCreateTime(System.currentTimeMillis());
        node.setSort(nodeService.findMaxSort() + 1);
        List<NodeInput> nodeInputs = Lists.newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jobj = jsonArray.getJSONObject(i);
            String propName = jobj.getString("name");
            String propType = jobj.getString("type");
            String propTitle = jobj.getString("title");
            String optionValue = jobj.getString("optionValue");
            String defaultValue = jobj.getString("defaultValue");
            NodeInput nodeInput = new NodeInput();
            nodeInput.setDefaultValue(defaultValue);
            nodeInput.setOptionValue(optionValue);
            nodeInput.setName(propName);
            nodeInput.setTitle(propTitle);
            nodeInput.setType(propType);
            nodeInputs.add(nodeInput);
        }
        node.setId(String.valueOf(Sequence.INSTANCE.nextId()));
        node.setAttributes(nodeInputs);
        nodeService.save(node);
        return Json.toSuccessJson();
    }

    @RequestMapping(value = "/node/delete", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupDelete(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String id = jsonObject.getString("id");
        if (StringUtils.isNotBlank(id)) {
            //check 查找是否有绑定的connection 节点
            nodeService.hardDeleteById(id);
        } else {
            return Json.toEmptyJson();
        }
        return Json.toSuccessJson();
    }

    @RequestMapping(value = "/node/changeSort", method = RequestMethod.POST)
    @ResponseBody
    public Json nodeGroupChangeSort(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String id = jsonObject.getString("id");
        String type = jsonObject.getString("type");
        if (StringUtils.isNotBlank(id)) {
            //升息
            Node current = nodeService.findById(id);
            Node near;
            if ("up".equalsIgnoreCase(type)) {
                near = nodeService.findByNearUp(current.getSort());
            } else {
                near = nodeService.findByNearDown(current.getSort());
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
                nodeService.saveorupdate(current);
                nodeService.saveorupdate(near);
            }

        } else {
            return Json.toEmptyJson();
        }
        return Json.toSuccessJson();
    }


}
