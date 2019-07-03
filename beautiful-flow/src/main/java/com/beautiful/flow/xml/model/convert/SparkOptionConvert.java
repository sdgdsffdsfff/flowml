package com.beautiful.flow.xml.model.convert;

import org.dom4j.Element;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 18:07
 **/
public class SparkOptionConvert implements Convert<Map<String, String>> {


    public String append(Map<String, String> input) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry :
                input.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //--executor-memory 20G
            sb.append("--").append(key).append(" ").append(value).append(" ");
        }
        return sb.toString();
    }

    public Element convert(Element root, String tag, Map<String, String> input) {
        String content = append(input);
        Element element = root.addElement("tag");
        element.setText(content);
        return root;
    }
}
