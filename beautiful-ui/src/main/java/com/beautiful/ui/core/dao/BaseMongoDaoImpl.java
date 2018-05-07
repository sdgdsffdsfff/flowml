package com.beautiful.ui.core.dao;


import com.beautiful.ui.core.Page;
import com.beautiful.ui.utils.ReflectionUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {
    /**
     * spring mongodb　集成操作类
     */
    @Resource
    protected MongoTemplate mongoTemplate;

    @Resource
    protected GridFsTemplate gridFsTemplate;//文件存储

    /**
     * 根据对象获得mongodb Update语句 除id字段以外，所有被赋值的字段都会成为修改项
     */
    public static Update getUpdateObj(final Object obj) {
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        Update update = null;
        boolean isFirst = true;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    if ("id".equals(field.getName().toLowerCase())
                            || "serialversionuid".equals(field.getName()
                            .toLowerCase()) || "_id".equals(field.getName().toLowerCase()))
                        continue;
                    if (field.isAnnotationPresent(Transient.class)) continue;
                    if (isFirst) {
                        update = Update.update(field.getName(), value);
                        isFirst = false;
                    } else {
                        update = update.set(field.getName(), value);
                    }
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    /**
     * 根据对象获得mongodb Query语句
     * <p>
     * 1.时间范围查询：在时间字段前增加begin或end，为这两个字段分别赋值 例：private Date createDate; 开始时间
     * private Date beginCreateDate; 结束时间 private Date endCreateDate;
     * 分析后结果：where createDate >= beginCreateDate and createDate <
     * beginCreateDate
     * <p>
     * 2.排序 定义并赋值VO中 orderBy 字段，以英文“,”分割多个排序，以空格分隔排序方向 asc可不写 例：private String
     * orderBy; orderBy="createDate desc,sendDate asc,id" 分析结构：order by
     * createDate desc,sendDate asc,id asc
     * <p>
     * 3.固定值搜索 定义并赋值VO中的任意字段，搜索时会把以赋值的字段当作为搜索条件
     */

    public static Query getQueryObj(final Object obj) {
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        // Sort sort=new Sort(new Order(Direction.DESC,"createDate"));
        Query query = new Query();
        // 存放日期范围或者确定日期
        Map<String, Criteria> dateMap = new HashMap<String, Criteria>();
        String sortStr = null;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    if ("serialversionuid"
                            .equals(field.getName().toLowerCase())) {
                        continue;
                    }
                    if ("orderby".equals(field.getName().toLowerCase())) {
                        sortStr = String.valueOf(value);
                        continue;
                    }
                    if (field.getType().getSimpleName().equals("Date")) {
                        if (field.getName().toLowerCase().startsWith("begin")) {
                            String beginName = field.getName().substring(5);
                            if (StringUtils.isNotEmpty(beginName)) {
                                dateMap.put("begin", Criteria.where("begin")
                                        .is(value));
                            } else {
                                //								beginName = StringUtil
                                //										.toLowerCaseFirstOne(beginName);
                                Criteria criteria = dateMap.get(beginName) == null ? Criteria
                                        .where(beginName).gte(value) : dateMap
                                        .get(beginName).gte(value);
                                dateMap.put(beginName, criteria);
                            }
                            continue;
                        }
                        if (field.getName().toLowerCase().startsWith("end")) {
                            String endName = field.getName().substring(3);
                            if (StringUtils.isNotEmpty(endName)) {
                                dateMap.put("end",
                                        Criteria.where("end").is(value));
                            } else {
                                //								endName = StringUtil
                                //										.toLowerCaseFirstOne(endName);
                                Criteria criteria = dateMap.get(endName) == null ? Criteria
                                        .where(endName).lt(value) : dateMap
                                        .get(endName).lt(value);
                                dateMap.put(endName, criteria);
                            }
                            continue;
                        }
                        dateMap.put(field.getName(),
                                Criteria.where(field.getName()).is(value));
                        continue;
                    }
                    query.addCriteria(Criteria.where(field.getName()).is(value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 日期类型查询条件
        for (String key : dateMap.keySet()) {
            if (dateMap.get(key) != null) {
                query.addCriteria(dateMap.get(key));
            }
        }
        // 排序
        if (sortStr != null && !StringUtils.isNotEmpty(sortStr.trim())) {
            Sort sort = null;
            String[] strs = sortStr.split(",");
            for (String str : strs) {
                str = str.trim();
                if (StringUtils.isNotEmpty(str)) {
                    continue;
                }
                int i = str.indexOf(" ");
                if (i < 0) {
                    if (sort == null) {
                        sort = new Sort(Direction.ASC, str);
                    } else {
                        sort = sort.and(new Sort(Direction.ASC, str));
                    }
                } else {
                    String name = str.substring(0, i);
                    String dire = str.substring(i + 1).trim();
                    Sort sn = null;
                    if ("desc".equals(dire.toLowerCase())) {
                        sn = new Sort(Direction.DESC, name);
                    } else {
                        sn = new Sort(Direction.ASC, name);
                    }
                    if (sort == null) {
                        sort = sn;
                    } else {
                        sort = sort.and(sn);
                    }
                }
            }
            if (sort != null) {
                query.with(sort);
            }
        }
        return query;
    }

    public GridFsTemplate getGridFsTemplate() {
        return gridFsTemplate;
    }

    public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    public String storeFile(File file, String filename, String contentType, Map<String, String> meta) {
        try {
            DBObject metadata = new BasicDBObject();
            for (Map.Entry<String, String> entry :
                    meta.entrySet()) {
                metadata.put(entry.getKey(), entry.getValue());
            }
            return gridFsTemplate.store(new FileInputStream(file), filename, contentType, metadata).getId().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String storeFile(File file, String filename, Map<String, String> meta) {
        try {
            DBObject metadata = new BasicDBObject();
            for (Map.Entry<String, String> entry :
                    meta.entrySet()) {
                metadata.put(entry.getKey(), entry.getValue());
            }
            return gridFsTemplate.store(new FileInputStream(file), filename, null, metadata).getId().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String storeStream(InputStream inputStream, Map<String, String> meta) {
        try {
            DBObject metadata = new BasicDBObject();
            for (Map.Entry<String, String> entry :
                    meta.entrySet()) {
                metadata.put(entry.getKey(), entry.getValue());
            }
            return gridFsTemplate.store(inputStream, null, null, metadata).getId().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String storeStream(InputStream inputStream, String filename, Map<String, String> meta) {
        try {
            DBObject metadata = new BasicDBObject();
            for (Map.Entry<String, String> entry :
                    meta.entrySet()) {
                metadata.put(entry.getKey(), entry.getValue());
            }
            return gridFsTemplate.store(inputStream, filename, null, metadata).getId().toString();
        } catch (Exception e) {
            return null;
        }
    }

    public GridFSDBFile findOneFile(String id) {
        GridFSDBFile gridFsdbFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(new ObjectId(id))));
        return gridFsdbFile;
    }

    public List<GridFSDBFile> findFiles(Map<String, String> meta) {
        Query query = new Query();
        for (Map.Entry<String, String> entry :
                meta.entrySet()) {
            String key = entry.getKey();
            query = query.addCriteria(Criteria.where("metadata." + key).is(entry.getValue()));
        }
        List<GridFSDBFile> gridFsdbFiles =
                gridFsTemplate.find(query);
        return gridFsdbFiles;
    }

    public void deleteFile(String id) {
        //String id = "5702deyu6d8bba0d6f2e45e4";
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(new ObjectId(id))));
    }

    public List<GridFsResource> findMatchFiles(String matcher) {
        GridFsResource[] gridFsResource = gridFsTemplate.getResources(matcher);
        return Arrays.asList(gridFsResource);
    }

    public Long randomUUID() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public Object getObjectIdValue(String value) {
        return ObjectId.isValid(value) ? new ObjectId(value) : value;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * 注入mongodbTemplate
     *
     * @param mongoTemplate
     */
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public T saveorupdate(T entity) {
        mongoTemplate.save(entity);
        return entity;
    }

    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(this.getEntityClass());
    }

    public List<T> findAll(String collectionName) {
        return mongoTemplate.findAll(this.getEntityClass(), collectionName);
    }

    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    public Page<T> findPage(Page<T> page, Query query) {
        //如果没有条件 则所有全部
        query = query == null ? new Query(Criteria.where("_id").exists(true)) : query;
        long count = this.count(query);
        // 总数
        page.setTotalCount((int) count);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPageSize();
        query.skip((currentPage - 1) * pageSize).limit(pageSize);
        List<T> rows = this.find(query);
        page.build(rows);
        return page;
    }

    public int count(Query query) {
        return (int) mongoTemplate.count(query, this.getEntityClass());
    }

    public WriteResult update(Query query, Update update) {
        if (update == null) {
            return null;
        }
        return mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    public T updateOne(Query query, Update update) {
        if (update == null) {
            return null;
        }
        return mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    public WriteResult updateFirst(Query query, Update update) {
        if (update == null) {
            return null;
        }
        return mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    public WriteResult update(T entity) {
        Field[] fields = this.getEntityClass().getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return null;
        }
        Field idField = null;
        // 查找ID的field
        for (Field field : fields) {
            if (field.getName() != null
                    && "id".equals(field.getName().toLowerCase())) {
                idField = field;
                break;
            }
        }
        if (idField == null) {
            return null;
        }
        idField.setAccessible(true);
        String id = null;
        try {
            id = String.valueOf(idField.get(entity));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (id == null || "".equals(id.trim()))
            return null;
        // 根据ID更新
        Query query = new Query(Criteria.where("_id").is(getObjectIdValue(id)));
        System.out.println(query.toString());
        Update update = getUpdateObj(entity);
        if (update == null) {
            return null;
        }
        return mongoTemplate.updateFirst(query, update, getEntityClass());
    }

    public void remove(Query query) {
        mongoTemplate.remove(query, this.getEntityClass());
    }

    public int countByQueryName(String query) {
        if (StringUtils.isBlank(query)) {
            return count(new Query(Criteria.where("del").is(0)));
        } else {
            return count(new Query(Criteria.where("del").is(0).and("name").regex(query)));
        }
    }

    public List<T> findListQueryName(String query) {
        if (StringUtils.isBlank(query)) {
            return find(new Query(Criteria.where("del").is(0)).with(new Sort(Direction.DESC, "ctime")));
        } else {
            return find(new Query(Criteria.where("del").is(0).and("name").regex(query)).with(new Sort(Direction.DESC, "ctime")));
        }
    }

    public List<T> findListQueryNamePage(String query, Integer start, Integer limit) {
        if (StringUtils.isBlank(query)) {
            return find(new Query(Criteria.where("del").is(0)).with(new Sort(Direction.DESC, "ctime")).skip(start).limit(limit));
        } else {
            return find(new Query(Criteria.where("del").is(0).and("name").regex(query)).with(new Sort(Direction.DESC, "ctime")).skip(start).limit(limit));
        }
    }

    public T findExistByName(String name) {
        return findOne(new Query(Criteria.where("name").is(name)));
    }

    public boolean switchStatus(String id, Integer status) {
        Query query = new Query(Criteria.where("_id").is(getObjectIdValue(id)));
        Update update = new Update().set("status", status);
        updateOne(query, update);
        return true;
    }

    public void deleteByIds(List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        Update update = new Update().set("del", 1);
        update(query, update);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update update = new Update().set("del", 1);
        update(query, update);
    }

    public List<T> findAllEnableList() {
        return find(new Query(Criteria.where("del").is(0).and("status").is(1)));
    }

    /**
     * 获得泛型类
     */
    private Class<T> getEntityClass() {
        // Type genType = getClass().getGenericSuperclass();
        // if (!(genType instanceof ParameterizedType)) {
        // return (Class<T>) Object.class;
        // }
        // // 返回表示此类型实际类型参数的 Type 对象的数组。
        // Type[] params = ((ParameterizedType)
        // genType).getActualTypeArguments();
        // if (!(params[0] instanceof Class)) {
        // return (Class<T>) Object.class;
        // }
        // return (Class<T>) params[0];
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }


}
