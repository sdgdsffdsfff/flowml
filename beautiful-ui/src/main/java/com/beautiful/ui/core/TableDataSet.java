package com.beautiful.ui.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DataTables 数据返回
 *
 * @param <T>
 * @author alex.zhu
 */
public class TableDataSet<T> implements Serializable {


    private Integer sEcho = 0;

    private Integer cpage = 0;

    private Integer iTotalRecords = 0;

    private Integer iTotalDisplayRecords = 0;

    private List<T> aaData;


    public TableDataSet() {


    }

    public static JSONObject covertJsonArrayToJsonObject(JSONArray jsonArray) {
        JSONObject map = new JSONObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            map.put(jsonObj.getString("name"), jsonObj.get("value"));
        }
        return map;
    }

    public static JSONObject covertJsonStringToJson(String jsonParam) {
        JSONArray jsonArray = JSONArray.parseArray(jsonParam);
        return covertJsonArrayToJsonObject(jsonArray);
    }

    public static Map<String, Object> covertJsonToHashMap(JSONObject jsonObject, String name) {
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            map.put(jsonObj.getString("name"), jsonObj.get("value"));
        }
        return map;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

    public Integer getCpage() {
        return cpage;
    }

    public void setCpage(Integer cpage) {
        this.cpage = cpage;
    }


}
