package com.beautiful.flow.xml.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 17:13
 **/
public class JacksonUtil {


    public static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, Object> fromJsonObject(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, Map.class);

    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJsonObject(Reader reader) throws Exception {
        return objectMapper.readValue(reader, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> fromJsonArray(String jsonString) throws Exception {

        return objectMapper.readValue(jsonString, List.class);

    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> fromJsonArray(Reader reader) throws Exception {
        return objectMapper.readValue(reader, List.class);

    }

    public static <T> T fromJsonObject(String jsonString, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonString, clazz);
    }

    public static <T> T fromJsonObject(InputStream io, Class<T> clazz) throws Exception {
        return objectMapper.readValue(io, clazz);

    }

    public static <T> List<T> fromJsonArray(String jsonString, Class<List<?>> collectionClass, Class<T> elementClass) throws Exception {
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));

    }


    public static <T> String stringify(T bean) throws Exception {
        StringWriter sw = new StringWriter();
        objectMapper.writeValue(sw, bean);
        return sw.toString();
    }

    public static String jsonToXml(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> map = mapper.readValue(json, Map.class);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(map);
        return xml;
    }


}

