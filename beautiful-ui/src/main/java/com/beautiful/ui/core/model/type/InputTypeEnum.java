package com.beautiful.ui.core.model.type;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 15:12
 **/
public enum InputTypeEnum {
    INPUT(0, "input", "输入框"),
    SELECT(1, "select", "单选框"),
    MULTISELECT(2, "multiselect", "多选框"),
    RADIO(3, "radio", "单选按钮"),
    CHECKBOX(4, "checkbox", "多选按钮"),
    FILE(5, "file", "文件上传"),
    DATESELECT(6, "dateSelect", "日期选择"),
    DATERANGER(7, "dateRanger", "日期范围"),
    RANGE(8, "RANGE", "返回选择框");


    private Integer code;
    private String type;
    private String description;

    InputTypeEnum(Integer code, String type, String description) {
        this.code = code;
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
