package com.beautiful.flow.xml.model;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.JavaArgConvert;

import java.util.List;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 14:23
 **/
public class JavaModel extends XMLModel {

    @XmlField(name = "job-tracker")
    private String jobTracker;
    @XmlField(name = "name-node")
    private String nameNode;
    @XmlField(name = "main-class")
    private String mainClass;
    @XmlField(name = "arg", convertUsing = JavaArgConvert.class)
    private List<String> args;

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

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
