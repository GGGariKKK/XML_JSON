package com.iharkusha.dataFormat.formatImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.validation.ValidatorVisitor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

public class XmlDataFormat extends DataFormat {
    private String originalData;
    @Override
    public void parse(String data) {
        this.originalData = data;
        JSONObject jsonObject = new JSONObject();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(data));
            Document document = builder.parse(inputSource);
            Element root = document.getDocumentElement();
            if (root.hasAttributes()) {
                NamedNodeMap attributes = root.getAttributes();
                int i = 0;
                while (i < attributes.getLength()) {
                    Node attribute = attributes.item(i);
                    jsonObject.put("@" + attribute.getNodeName(), attribute.getNodeValue());
                    i++;
                }
            }
            hasChildNodes(jsonObject, root.hasChildNodes(), root.getChildNodes());
        } catch (ParserConfigurationException | IOException | SAXException e) {
             e.printStackTrace();
        }
        System.out.println(jsonObject);
    }

    private void hasChildNodes(JSONObject jsonObject, boolean b, NodeList childNodes) {
        if (b) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node child = childNodes.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    if (child.hasChildNodes() && child.getFirstChild().getNodeType() == Node.TEXT_NODE && child.getLastChild().getNodeType() == Node.TEXT_NODE) {
                        jsonObject.put(child.getNodeName(), child.getTextContent());
                    } else {
                        if (!jsonObject.has(child.getNodeName())) {
                            jsonObject.put(child.getNodeName(), new JSONArray());
                        }
                        JSONArray jsonArray = jsonObject.getJSONArray(child.getNodeName());
                        JSONObject childJSONObject = new JSONObject();
                        if (child.hasAttributes()) {
                            NamedNodeMap attributes = child.getAttributes();
                            for (int j = 0; j < attributes.getLength(); j++) {
                                Node attribute = attributes.item(j);
                                childJSONObject.put("@" + attribute.getNodeName(), attribute.getNodeValue());
                            }
                        }
                        parseChild(child, childJSONObject);
                        jsonArray.put(childJSONObject);
                    }
                }
            }
        }
    }

    private void parseChild(Node node, JSONObject jsonObject) {
        hasChildNodes(jsonObject, node.hasChildNodes(), node.getChildNodes());
    }

    @Override
    public String render(String data) {
        StringBuilder xmlBuilder = new StringBuilder();
        try {
            JSONObject json = new JSONObject(data);
           xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            convertJsonObjectToXml(json, xmlBuilder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String xmlString = xmlBuilder.toString();
        if (xmlString.startsWith("\uFEFF")) { // check for BOM character
            xmlString = xmlString.substring(1);
        }
        return xmlString;
    }

    @Override
    public String getOriginalData() {
        return originalData;
    }

    @Override
    public void accept(ValidatorVisitor visitor) {
        visitor.visit(this);
    }


    private void convertJsonObjectToXml(JSONObject json, StringBuilder xmlBuilder) throws JSONException {
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            if (value instanceof JSONObject) {
                xmlBuilder.append("<").append(key).append(">");
                convertJsonObjectToXml((JSONObject) value, xmlBuilder);
                xmlBuilder.append("</").append(key).append(">");
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    xmlBuilder.append("<").append(key).append(">");
                    Object arrayValue = jsonArray.get(i);
                    if (arrayValue instanceof JSONObject) {
                        convertJsonObjectToXml((JSONObject) arrayValue, xmlBuilder);
                    } else {
                        xmlBuilder.append(arrayValue);
                    }
                    xmlBuilder.append("</").append(key).append(">");
                }
            } else {
                xmlBuilder.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
            }
        }
    }

}