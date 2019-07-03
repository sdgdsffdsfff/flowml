package com.beautiful.flow.xml.utils;

import com.beautiful.flow.xml.annotation.XmlField;
import com.beautiful.flow.xml.model.convert.Convert;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: ${description}
 * @author: zhuyuping
 * @create: 2018-05-18 15:25
 **/
public class OozieXmlUtil {


    public static Element generateActionXML(Element root, Object actionModel) throws Exception {
        Class type = actionModel.getClass();
        Map<String, String> returnMap = new HashMap<String, String>();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                if (descriptor.getReadMethod().isAnnotationPresent(XmlField.class)) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(actionModel);
                    XmlField xmlField = readMethod.getAnnotation(XmlField.class);
                    String tag = xmlField.name();
                    boolean isConvert = xmlField.convert();
                    Convert<Object> convert = xmlField.convertUsing().newInstance();
                    if (isConvert) {
                        root = convert.convert(root, tag, result);
                    }
                }

            }
        }
        return root;
    }

    public static void main(String args[]) {

        Namespace rootNs = new Namespace("", "uri:oozie:workflow:0.4"); // root namespace uri
        QName rootQName = QName.get("workflow-app", rootNs); // your root element's name
        Element workflow = DocumentHelper.createElement(rootQName);
        Document doc = DocumentHelper.createDocument(workflow);

        workflow.addAttribute("name", "test");
        Element test = workflow.addElement("test");
        test.addText("hello");
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
//				outputFormat.setSuppressDeclaration(true); //是否生产xml头
        outputFormat.setIndent(true); // 设置是否缩进
        outputFormat.setIndent("    "); // 以四个空格方式实现缩进
        outputFormat.setNewlines(true); // 设置是否换行
        try {
            // stringWriter字符串是用来保存XML文档的
            StringWriter stringWriter = new StringWriter();
            // xmlWriter是用来把XML文档写入字符串的(工具)
            XMLWriter xmlWriter = new XMLWriter(stringWriter);//, outputFormat);

            // 把创建好的XML文档写入字符串
            xmlWriter.write(doc);

            xmlWriter.close();
            System.out.println(doc.asXML());

            System.out.println(stringWriter.toString().trim());
            // 打印字符串,即是XML文档

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
