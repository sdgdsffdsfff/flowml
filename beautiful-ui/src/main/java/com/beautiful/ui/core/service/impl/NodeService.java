package com.beautiful.ui.core.service.impl;

import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.dao.BaseMongoDaoImpl;
import com.beautiful.ui.core.model.Node;
import com.beautiful.ui.core.model.NodeGroup;
import com.beautiful.ui.core.pagemodel.NodeVO;
import com.beautiful.ui.core.service.INodeGroupService;
import com.beautiful.ui.core.service.INodeService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class NodeService extends BaseMongoDaoImpl<Node> implements INodeService {

    @Autowired
    private INodeGroupService groupService;

    @Override
    public List<Node> findNodesByGroupId(String groupId) {
        return find(Query.query(Criteria.where("groupId").is(groupId)).with(new Sort(Sort.Direction.ASC, "createTime")));
    }

    @Override
    public TableDataSet<NodeVO> findNodeInfoByQueryPager(String queryStr, int start, int limit) {
        TableDataSet<NodeVO> tableDataSet = new TableDataSet<NodeVO>();
        Pattern pattern = Pattern.compile("^.*" + queryStr + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        if (StringUtils.isNotBlank(queryStr)) {
            query = query.query(Criteria.where("name").regex(pattern));
        }
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        List<Node> nodeList = find(query.skip(start).limit(limit));
        List<NodeVO> nodeVOList = Lists.transform(nodeList, new Function<Node, NodeVO>() {
            @Override
            public NodeVO apply(Node input) {
                String groupId = input.getGroupId();
                NodeGroup group = groupService.findById(groupId);
                NodeVO nodeVO = new NodeVO();
                nodeVO.setNode(input);
                nodeVO.setNodeGroup(group);
                return nodeVO;
            }
        });
        Integer count = count(query);
        tableDataSet.setAaData(nodeVOList);
        tableDataSet.setiTotalRecords(count);
        tableDataSet.setiTotalDisplayRecords(count);
        tableDataSet.setCpage((start - 1 + limit) / limit + 1);
        return tableDataSet;
    }
}
