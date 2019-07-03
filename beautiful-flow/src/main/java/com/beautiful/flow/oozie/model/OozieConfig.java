package com.beautiful.flow.oozie.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 11:57
 **/
public class OozieConfig implements Serializable {

    private String oozieAppPath;

    private String queueName;

    private String nameNode;

    private String jobTracker;


    private String resourceManager;

    private Map<String, String> props;

    public String getOozieAppPath() {
        return oozieAppPath;
    }

    public void setOozieAppPath(String oozieAppPath) {
        this.oozieAppPath = oozieAppPath;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getNameNode() {
        return nameNode;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }

    public String getJobTracker() {
        return jobTracker;
    }

    public void setJobTracker(String jobTracker) {
        this.jobTracker = jobTracker;
    }


    public String getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(String resourceManager) {
        this.resourceManager = resourceManager;
    }

    public Map<String, String> getProps() {
        return props;
    }

    public void setProps(Map<String, String> props) {
        this.props = props;
    }
}
