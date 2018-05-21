package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-21 11:45
 **/
public class ConfigurationConvert implements Convert<Map<String, String>> {


    public Element convert(Element root, String tag, Map<String, String> input) {
        Element element = root.addElement(tag);
        for (Map.Entry<String, String> entry :
                input.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Element propertyEle = element.addElement("property");
            propertyEle.addElement("name").setText(key);
            propertyEle.addElement("value").setText(value);
        }
        return root;
    }
}
