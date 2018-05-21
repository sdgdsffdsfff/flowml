package com.beautiful.ui.core.model;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 18:26
 **/
public enum DataNodeType {

    START,
    NEXT,//下一步
    CONDITION,//条件阻塞
    JOIN,//聚合节点
    REPEAT,//重复节点
    FORK,//分散
    ROUTER,//转发
    SUBSCRIBE,//发布订阅 同一份数据消费多次
    END;


}
