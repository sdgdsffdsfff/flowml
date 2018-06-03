package com.beautiful.flow.xml.model;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 20:37
 **/
public class XmlElement implements Serializable {

    private String name;//元素名

    private String text;//文本

    public XmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlElement that = (XmlElement) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, text);
    }
}
