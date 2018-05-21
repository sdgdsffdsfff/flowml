package com.beautiful.flow.oozie;

import com.beautiful.flow.oozie.model.OozieConfig;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;

import java.util.Date;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 11:36
 **/
public interface IOozieService {

    public OozieClient getOozieClient();

    /**
     * 提交
     *
     * @param config
     * @return
     */
    public String submitJob(OozieConfig config) throws Exception;

    public Date getJobCreateTime(String jobId) throws Exception;

    public WorkflowJob getJobInfo(String jobId) throws Exception;

    /**
     * 再次运行
     *
     * @param jobId
     */
    public void againRun(String jobId, OozieConfig config) throws Exception;

    /**
     * 启动
     *
     * @param jobId
     */
    public void start(String jobId) throws Exception;

    /**
     * 恢复
     *
     * @param jobId
     */
    public void resume(String jobId) throws Exception;

    /**
     * 暂停
     *
     * @param jobId
     */
    public void suspend(String jobId) throws Exception;

    /**
     * 停止
     *
     * @param jobId
     */
    public void kill(String jobId) throws Exception;


}
