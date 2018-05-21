package com.beautiful.flow.xml.model.convert;

import com.google.common.collect.Lists;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 18:18
 **/
public class SparkArgFlatternConvert implements Convert<Map<String, String>> {

    public static void main(String[] args) throws Exception {
        Namespace rootNs = new Namespace("", "uri:oozie:workflow:0.4"); // root namespace uri
        QName rootQName = QName.get("workflow-app", rootNs); // your root element's name
        Element workflow = DocumentHelper.createElement(rootQName);
        Document doc = DocumentHelper.createDocument(workflow);
        List<String> results = new ArrayList<String>();
        results.add("a");
        results.add("b");
        results.add("c");
        results.add("d");
        StringBuilder sb = new StringBuilder();
        for (String result :
                results) {
            Element element = workflow.addElement("arg");
            element.setText(result);
            //sb.append(element.asXML());
            //workflow.add(element);
        }
        //System.out.println(sb);
        System.out.println("##################");
        System.out.println(doc.asXML());
    }

    public List<String> flattern(Map<String, String> input) {
        List<String> results = Lists.newArrayList();
        for (Map.Entry<String, String> entry :
                input.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            results.add(key + "=" + value);
        }
        return results;
    }

    public Element convert(Element root, String tag, Map<String, String> input) {
        List<String> results = flattern(input);
        for (String result :
                results) {
            Element element = root.addElement(tag);
            element.setText(result);
        }
        return root;
    }

}
