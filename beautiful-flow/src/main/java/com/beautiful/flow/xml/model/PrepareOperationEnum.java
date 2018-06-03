package com.beautiful.flow.xml.model;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 16:50
 **/
public enum PrepareOperationEnum {

    DELETE("delete", 0),
    MKDIR("mkdir", 1);

    private String name;
    private Integer code;

    PrepareOperationEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
