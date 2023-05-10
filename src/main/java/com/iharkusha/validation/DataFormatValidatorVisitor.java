package com.iharkusha.validation;

import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DataFormatValidatorVisitor  implements ValidatorVisitor {

    @Override
    public void visit(JsonDataFormat jsonDataFormat) {
        String json = jsonDataFormat.getOriginalData();
        try {
            JSONObject jsonObj = new JSONObject(json);
            // If JSON object is created - no errors present
            System.out.println("JSON is valid");
        } catch (JSONException e) {
            System.out.println("JSON is not valid");
        }
    }

    @Override
    public void visit(XmlDataFormat xmlDataFormat) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlDataFormat.getOriginalData());

            NodeList errorList = document.getElementsByTagName("parsererror");
            if (errorList.getLength() > 0) {
                throw new IllegalArgumentException("XML not valid: " + errorList.item(0).getTextContent());
            } else {
                System.out.println("XML is valid.");
            }
        } catch (Exception e) {
//            System.err.println("Error validating XML: " + e.getMessage());
        }
    }
}
