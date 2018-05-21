package com.beautiful.flow.oozie;

import org.apache.oozie.client.OozieClient;
import org.springframework.beans.factory.FactoryBean;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-17 11:38
 **/
public class OozieClientFactoryBean implements FactoryBean<OozieClient> {


    private String oozieUri;

    public OozieClientFactoryBean(String oozieUri) {
        this.oozieUri = oozieUri;
    }

    public OozieClient getObject() throws Exception {
        return new OozieClient(oozieUri);
    }

    public Class<?> getObjectType() {
        return OozieClient.class;
    }

    public boolean isSingleton() {
        return true;
    }


    public String getOozieUri() {
        return oozieUri;
    }

    public void setOozieUri(String oozieUri) {
        this.oozieUri = oozieUri;
    }
}
