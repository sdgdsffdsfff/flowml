package com.beautiful.ui.core.service;

import com.beautiful.ui.core.TableDataSet;
import com.beautiful.ui.core.dao.BaseMongoDao;
import com.beautiful.ui.core.model.NodeGroup;

public interface INodeGroupService extends BaseMongoDao<NodeGroup> {


    TableDataSet<NodeGroup> findListByQueryPager(String query, int start, int limit);

    Integer findMaxSort();

    NodeGroup findByNearUp(Integer sort);

    NodeGroup findByNearDown(Integer sort);
}
