package com.beautiful.ui.core.service.impl;

import com.beautiful.ui.core.dao.BaseMongoDaoImpl;
import com.beautiful.ui.core.model.Node;
import com.beautiful.ui.core.service.INodeService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService extends BaseMongoDaoImpl<Node> implements INodeService {


    @Override
    public List<Node> findNodesByGroupId(String groupId) {
        return find(Query.query(Criteria.where("groupId").is(groupId)).with(new Sort(Sort.Direction.ASC, "createTime")));
    }
}
