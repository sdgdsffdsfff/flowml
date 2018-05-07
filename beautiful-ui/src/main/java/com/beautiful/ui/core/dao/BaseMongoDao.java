package com.beautiful.ui.core.dao;

import com.beautiful.ui.core.Page;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyuping on 2016/12/28.
 * base层Mongodb http://www.jianshu.com/p/dd7b5a0e2f64
 */
public interface BaseMongoDao<T> {

    public String storeFile(File file, String filename, String contentType, Map<String, String> meta);

    public String storeFile(File file, String filename, Map<String, String> meta);

    public String storeStream(InputStream inputStream, Map<String, String> meta);

    public String storeStream(InputStream inputStream, String filename, Map<String, String> meta);

    public GridFSDBFile findOneFile(String id);

    public List<GridFSDBFile> findFiles(Map<String, String> meta);

    public void deleteFile(String id);

    public List<GridFsResource> findMatchFiles(String matcher);

    public Long randomUUID();

    public Object getObjectIdValue(String value);

    public MongoTemplate getMongoTemplate();

    /**
     * 更新或者保存
     *
     * @param entity
     * @return
     */
    public T saveorupdate(T entity);

    /**
     * 插入
     */
    public T save(T entity);

    /**
     * 根据ID查询
     */
    public T findById(String id);

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     */
    public T findById(String id, String collectionName);

    /**
     * 获得所有该类型记录
     */
    public List<T> findAll();

    /**
     * 获得所有该类型记录,并且指定了集合名(表的意思)
     */
    public List<T> findAll(String collectionName);

    /**
     * 根据条件查询
     */
    public List<T> find(Query query);

    /**
     * 根据条件查询一个
     */
    public T findOne(Query query);

    /**
     * 分页查询
     */
    public Page<T> findPage(Page<T> page, Query query);

    /**
     * 根据条件 获得总数
     */
    public int count(Query query);

    /**
     * 根据条件 更新
     */
    public WriteResult update(Query query, Update update);

    /**
     * 更新一条记录
     *
     * @param query
     * @param update
     * @return
     */
    public WriteResult updateFirst(Query query, Update update);

    /**
     * 更新符合条件并sort之后的第一个文档 并返回更新后的文档
     */
    public T updateOne(Query query, Update update);

    /**
     * 根据传入实体ID更新
     */
    public WriteResult update(T entity);

    /**
     * 根据条件 删除
     *
     * @param query
     */
    public void remove(Query query);


    public int countByQueryName(String query);

    public List<T> findListQueryName(String query);

    public List<T> findListQueryNamePage(String query, Integer start, Integer limit);

    public T findExistByName(String name);

    public boolean switchStatus(String id, Integer status);

    public void deleteByIds(List<String> ids);

    public void deleteById(String id);

    public List<T> findAllEnableList();


}
