package com.beautiful.flow.xml.utils;

import com.beautiful.flow.xml.model.XmlElement;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;

import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-16 20:31
 **/
public class XMLUtils {

    /**
     * 直接外层的 用于多个容器生成
     *
     * @param rootName
     * @param attribute
     * @return
     */
    public static String generatorXML(String rootName, String namespace, Map<XmlElement, Map<String, String>> attribute) {
        Namespace xmlns = DocumentHelper.createNamespace("xmlns", namespace);
        Document doc = DocumentHelper.createDocument();
        // 设置编码
        doc.setXMLEncoding("utf-8");
        // 创建一个根节点
        Element root = doc.addElement(rootName);
        root.add(xmlns);
        for (Map.Entry<XmlElement, Map<String, String>> entry :
                attribute.entrySet()) {
            XmlElement ele = entry.getKey();
            Map<String, String> attrs = entry.getValue();
            Element element = root.addElement(ele.getName());
            element.setText(ele.getText());
            for (Map.Entry<String, String> stringEntry :
                    attrs.entrySet()) {
                element.attributeValue(stringEntry.getKey(), stringEntry.getValue());
            }
        }
        String strXML = doc.asXML();
        return strXML;
    }


}
