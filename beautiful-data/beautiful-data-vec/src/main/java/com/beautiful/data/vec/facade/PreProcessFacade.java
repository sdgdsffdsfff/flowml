package com.beautiful.data.vec.facade;

import com.beautiful.api.schema.Schema;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

/**
 * @Description:对外门面
 * @Author: zhuyuping
 * @CreateDate: 2018/2/12 下午6:04
 **/
public class PreProcessFacade {

    private Schema shema;

    private TinkerGraph tinkerGraph;

    public interface BuildStep {
        Character build();
    }

    public interface LoadStep {
        OperationStep load(Schema schema);

    }

    public interface OperationStep {
        OperationStep filter(Schema schema);

    }


    /**
     *   1.非schema结构化数据转为结构化
     *   2.schema数据 更换schema数据
     *   3.schema数据类型更改
     *   3.列名更改
     *   4.新增列
     *   5.新增id 列 Long uuid
     *   6.callUDF 自定义注册
     *   7.createSchema
     *   8.deleteSchema
     *   9.createTable
     *   10.renameTable
     *   11.deleteTable
     *   12.删除列
     *   13.stats
     *
     *
     *
     *
     *
     */

    public static class Builder {


    }


}
