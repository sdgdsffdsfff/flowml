package com.beautiful.ui.core;

import java.io.Serializable;

/**
 * Created by zhuyuping on 2016/11/23.
 */
public class Json implements Serializable {

    public static final Integer EMPTY = 300;
    public static final Integer FAIL = 300;
    public static final Integer EXEPTION = 500;
    public static final Integer SUCCESS = 200;
    private String msg;

    private Integer status = 200;

    private Object obj;

    public Json() {
    }

    public static Json toEmptyJson() {
        Json json = new Json();
        json.setStatus(Json.EMPTY);
        json.setMsg("参数不完整或格式错误");
        return json;
    }

    public static Json toFailJson() {
        Json json = new Json();
        json.setStatus(Json.FAIL);
        json.setMsg("参数错误");
        return json;
    }

    public static Json toFailJson(String msg) {
        Json json = new Json();
        json.setStatus(Json.FAIL);
        json.setMsg(msg);
        return json;
    }

    public static Json toExeceptionJson(Object obj, String msg) {
        Json json = new Json();
        json.setStatus(Json.EXEPTION);
        json.setMsg(msg);
        json.setObj(obj);
        return json;
    }

    public static Json toExeceptionJson(String msg) {
        Json json = new Json();
        json.setStatus(Json.EXEPTION);
        json.setMsg(msg);
        return json;
    }

    public static Json toSuccessJson() {
        Json json = new Json();
        json.setStatus(Json.SUCCESS);
        json.setMsg("操作成功");
        return json;
    }

    public static Json toSuccessJson(Object obj) {
        Json json = new Json();
        json.setStatus(Json.SUCCESS);
        json.setObj(obj);
        json.setMsg("操作成功");
        return json;
    }

    public static Json toSuccessJson(Object obj, String id) {
        Json json = new Json();
        json.setStatus(Json.SUCCESS);
        json.setObj(obj);
        json.setMsg(id);
        return json;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


}
