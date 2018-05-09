package com.beautiful.ui.core.service.impl;

import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.dao.BaseMongoDaoImpl;
import com.beautiful.ui.core.model.NodeGroup;
import com.beautiful.ui.core.service.INodeGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;


@Service
public class NodeGroupService extends BaseMongoDaoImpl<NodeGroup> implements INodeGroupService {


    @Override
    public TableDataSet<NodeGroup> findListByQueryPager(String queryStr, int start, int limit) {
        TableDataSet<NodeGroup> tableDataSet = new TableDataSet<NodeGroup>();
        Pattern pattern = Pattern.compile("^.*" + queryStr + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        if (StringUtils.isNotBlank(queryStr)) {
            query = query.query(Criteria.where("name").regex(pattern));
        }
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        List<NodeGroup> nodeGroupList = find(query.skip(start).limit(limit));
        Integer count = count(query);
        tableDataSet.setAaData(nodeGroupList);
        tableDataSet.setiTotalRecords(count);
        tableDataSet.setiTotalDisplayRecords(count);
        tableDataSet.setCpage((start - 1 + limit) / limit + 1);
        return tableDataSet;
    }

    @Override
    public Integer findMaxSort() {
        //Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("grade_name").count().as("总人数"));
        //getMongoTemplate().aggregate()
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.limit(1);
        NodeGroup nodeGroup = findOne(query);
        if (nodeGroup == null) return 0;
        return nodeGroup.getSort();
    }

    @Override
    public NodeGroup findByNearUp(Integer sort) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sort").lt(sort));
        query.with(new Sort(Sort.Direction.DESC, "sort"));
        query.limit(1);
        NodeGroup nodeGroup = findOne(query);
        return nodeGroup;
    }

    @Override
    public NodeGroup findByNearDown(Integer sort) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sort").gt(sort));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        query.limit(1);
        NodeGroup nodeGroup = findOne(query);
        return nodeGroup;
    }
}
