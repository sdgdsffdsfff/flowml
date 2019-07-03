package com.beautiful.flow.oozie;

import com.beautiful.flow.oozie.model.OozieConfig;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Properties;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 11:36
 **/
public class OozieService implements IOozieService {

    private Logger logger = LoggerFactory.getLogger(OozieService.class);

    @Autowired
    private OozieClient oozieClient;

    public OozieClient getOozieClient() {
        return oozieClient;
    }

    public String submitJob(OozieConfig config) throws Exception {
        Properties conf = oozieClient.createConfiguration();
        Preconditions.checkNotNull(config.getOozieAppPath());
        conf.setProperty(OozieClient.APP_PATH, config.getOozieAppPath());
        conf.setProperty("appPath", config.getOozieAppPath());
        if (StringUtils.isNotBlank(config.getQueueName())) {
            conf.setProperty("queueName", config.getQueueName());
        }
        if (StringUtils.isNotBlank(config.getNameNode())) {
            conf.setProperty("nameNode", config.getNameNode());
        }
        if (StringUtils.isNotBlank(config.getJobTracker())) {
            conf.setProperty("jobTracker", config.getJobTracker());
        }
        if (StringUtils.isNotBlank(config.getResourceManager())) {
            conf.setProperty("resourceManager", config.getResourceManager());
        }

        String jobId = oozieClient.run(conf);
        logger.info("提交 workflow job:" + jobId);
        return jobId;
    }

    public Date getJobCreateTime(String jobId) throws Exception {
        return oozieClient.getBundleJobInfo(jobId).getCreatedTime();
    }

    public WorkflowJob getJobInfo(String jobId) throws Exception {
        return oozieClient.getJobInfo(jobId);
    }

    public void againRun(String jobId, OozieConfig config) throws Exception {
        Properties conf = oozieClient.createConfiguration();
        String app_path = oozieClient.getJobInfo(jobId).getAppPath();
        conf.setProperty(OozieClient.APP_PATH, app_path);
        conf.setProperty("appPath", app_path);
        if (StringUtils.isNotBlank(config.getQueueName())) {
            conf.setProperty("queueName", config.getQueueName());
        }
        if (StringUtils.isNotBlank(config.getNameNode())) {
            conf.setProperty("nameNode", config.getNameNode());
        }
        if (StringUtils.isNotBlank(config.getJobTracker())) {
            conf.setProperty("jobTracker", config.getJobTracker());
        }
        if (StringUtils.isNotBlank(config.getResourceManager())) {
            conf.setProperty("resourceManager", config.getResourceManager());
        }
        oozieClient.reRun(jobId, conf);
    }

    public void start(String jobId) throws Exception {
        oozieClient.start(jobId);
    }

    public void resume(String jobId) throws Exception {
        oozieClient.resume(jobId);
    }

    public void suspend(String jobId) throws Exception {
        oozieClient.suspend(jobId);
    }

    public void kill(String jobId) throws Exception {
        oozieClient.kill(jobId);
    }
}
