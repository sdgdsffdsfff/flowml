package com.beautiful.flow.xml.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.Map.Entry;


public class XmlToJsonUtil {

    public static void main(String[] args) {
        //请注意使用正常的xml
        //xml->json
        String jsonStr = xmlToJson("D:\\NewFile.xml", null);
        System.out.println(jsonStr);

        //json->xml
//		String xmlstr = jsonToXml("{\"class\": {\"student\": [{\"age\": \"18\",\"gender\": \"男\",\"name\": \"张三\",\"qk\": [{\"q1\": \"001\",\"q2\": \"002\",\"q3\": \"003\"},{\"q1\": \"001\",\"q2\": \"002\",\"q3\": \"003\"}]},{\"age\": \"17\",\"gender\": \"男\",\"name\": \"李四\",\"qk\": {\"q1\": \"005\",\"q2\": \"006\",\"q3\": \"007\"}},{\"age\": \"19\",\"gender\": \"女\",\"name\": \"王五\",\"qk\": {\"q1\": \"008\",\"q2\": \"009\",\"q3\": \"010\"}}]}}");
//		System.out.println(xmlstr);
    }

    /**
     * xml转json字符串 注意:路径和字符串二传一另外一个传null<br>
     * 方 法 名：xmlToJson <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 上午10:48:26 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param xmlPath xml路径(和字符串二传一,两样都传优先使用路径)
     * @param xmlStr  xml字符串(和路径二传一,两样都传优先使用路径)
     * @return String
     * @throws IOException
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    public static String xmlToJson(String xmlPath, String xmlStr) {
        SAXBuilder sbder = new SAXBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        Document document;
        try {
            if (xmlPath != null) {
                //路径
                document = sbder.build(new File(xmlPath));
            } else if (xmlStr != null) {
                //xml字符
                StringReader reader = new StringReader(xmlStr);
                InputSource ins = new InputSource(reader);
                document = sbder.build(ins);
            } else {
                return "{}";
            }
            //获取根节点
            Element el = document.getRootElement();
            List<Element> eList = el.getChildren();
            Map<String, Object> rootMap = new HashMap<String, Object>();
            //得到递归组装的map
            rootMap = xmlToMap(eList, rootMap);
            map.put(el.getName(), rootMap);
            //将map转换为json 返回
            return JSON.toJSONString(map);
        } catch (Exception e) {
            return "{}";
        }
    }

    /**
     * json转xml<br>
     * 方 法 名：jsonToXml <br>
     * 创 建 人：h.j<br>
     * 创建时间：2017年5月10日 上午11:09:26 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param json
     * @return String
     */
    public static String jsonToXml(String json) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            JSONObject jObj = JSON.parseObject(json);
            jsonToXmlstr(jObj, buffer);
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * json转str<br>
     * 方 法 名：jsonToXmlstr <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 下午12:02:17 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param jObj
     * @param buffer
     * @return String
     */
    public static String jsonToXmlstr(JSONObject jObj, StringBuffer buffer) {
        Set<Entry<String, Object>> se = jObj.entrySet();
        for (Iterator<Entry<String, Object>> it = se.iterator(); it.hasNext(); ) {
            Entry<String, Object> en = it.next();
            if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
                buffer.append("<" + en.getKey() + ">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo, buffer);
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey() + ">");
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">");
                }
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                buffer.append("<" + en.getKey() + ">" + en.getValue());
                buffer.append("</" + en.getKey() + ">");
            }

        }
        return buffer.toString();
    }


    /**
     * 节点转map<br>
     * 方 法 名：xmlToMap <br>
     * 创 建 人：h.j <br>
     * 创建时间：2017年5月10日 上午10:56:49 <br>
     * 修 改 人： <br>
     * 修改日期： <br>
     *
     * @param eList
     * @param map
     * @return Map<String                                                               ,                                                               Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> xmlToMap(List<Element> eList, Map<String, Object> map) {
        for (Element e : eList) {
            Map<String, Object> eMap = new HashMap<String, Object>();
            List<Element> elementList = e.getChildren();
            if (elementList != null && elementList.size() > 0) {
                eMap = xmlToMap(elementList, eMap);
                Object obj = map.get(e.getName());
                if (obj != null) {
                    List<Object> olist = new ArrayList<Object>();
                    if (obj.getClass().getName().equals("java.util.HashMap")) {
                        olist.add(obj);
                        olist.add(eMap);

                    } else if (obj.getClass().getName().equals("java.util.ArrayList")) {
                        olist = (List<Object>) obj;
                        olist.add(eMap);
                    }
                    map.put(e.getName(), olist);
                } else {
                    map.put(e.getName(), eMap);
                }
            } else {
                map.put(e.getName(), e.getValue());
            }
        }
        return map;
    }


}
