package com.iharkusha.validation;

import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class FormatValidator implements ConverterExtension {

    @Override
    public void action(JsonDataFormat jsonDataFormat) {
        String json = jsonDataFormat.render();
        try {
            new JSONObject(json);
            // If JSON object is created - no errors present
            System.out.println("JSON is valid");
        } catch (JSONException e) {
            System.out.println("JSON is not valid");
        }
    }

    @Override
    public void action(XmlDataFormat xmlDataFormat) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xmlDataFormat.render().getBytes()));
            NodeList errorList = document.getElementsByTagName("parsererror");
            if (errorList.getLength() > 0) {
                throw new IllegalArgumentException("XML not valid: " + errorList.item(0).getTextContent());
            } else {
                System.out.println("XML is valid.");
            }
        } catch (Exception e) {
            System.err.println("Error validating XML: " + e.getMessage());
        }
    }
}
