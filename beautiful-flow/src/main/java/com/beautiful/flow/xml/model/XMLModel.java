package com.beautiful.flow.xml.model;


import com.beautiful.common.model.TuplePair;
import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.ConfigurationConvert;
import com.beautiful.flow.xml.model.convert.PrepareOperationConvert;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 16:11
 **/
public abstract class XMLModel implements Serializable {

    @XmlField(name = "configuration", convertUsing = ConfigurationConvert.class)
    Map<String, String> configuration;
    @XmlField(name = "prepare", convertUsing = PrepareOperationConvert.class)
    List<TuplePair<String, Map<String, String>>> prepare;


    public Map<String, String> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Map<String, String> configuration) {
        this.configuration = configuration;
    }

    public List<TuplePair<String, Map<String, String>>> getPrepare() {
        return prepare;
    }

    public void setPrepare(List<TuplePair<String, Map<String, String>>> prepare) {
        this.prepare = prepare;
    }
}
