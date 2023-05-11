package com.iharkusha.dataFormat.formatImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.validation.DataFormatExtension;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Iterator;

public class XmlDataFormat extends DataFormat {
    private String originalData;
    private String parsed;

    public XmlDataFormat(Charset encoding) {
        super(encoding);
    }

    @Override
    public void parse(String data) {
        originalData = data;
        StringBuilder xmlBuilder = new StringBuilder();
        try {
            JSONObject json = new JSONObject(data);
            convertJsonObjectToXml(json, xmlBuilder);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String xmlString = xmlBuilder.toString();
        if (xmlString.startsWith("\uFEFF")) { // check for BOM character
            xmlString = xmlString.substring(1);
        }
        parsed = xmlString;
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

    @Override
    public String render() {
        return new String(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<root>" +
                parsed
                + "</root>").getBytes(), getEncoding());
    }

    @Override
    public void accept(DataFormatExtension extension) {
        extension.action(this);
    }
}