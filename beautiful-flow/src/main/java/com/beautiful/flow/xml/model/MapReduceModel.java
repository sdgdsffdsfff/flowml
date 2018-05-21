package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 18:45
 **/
public class MapReduceModel extends XMLModel {
    @XmlField(name = "job-tracker")
    private String jobTracker;

    @XmlField(name = "name-node")
    private String nameNode;

    @XmlField(name = "job-xml")
    private String jobXml;

    public String getJobTracker() {
        return jobTracker;
    }

    public void setJobTracker(String jobTracker) {
        this.jobTracker = jobTracker;
    }

    public String getNameNode() {
        return nameNode;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }

    public String getJobXml() {
        return jobXml;
    }

    public void setJobXml(String jobXml) {
        this.jobXml = jobXml;
    }
}
