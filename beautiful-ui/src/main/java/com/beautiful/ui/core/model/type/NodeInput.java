package com.beautiful.ui.core.model.type;

import java.io.Serializable;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 16:46
 **/
public class NodeInput implements Serializable {

    private String name;

    private String type;

    private String title;

    private String optionValue;

    private String defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
