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
            // Перевіряємо, чи є JSON-рядок валідним
            JSONObject jsonObj = new JSONObject(json);
            // Якщо перевірка вдалася, то JSON є валідним
            System.out.println("JSON is valid");
        } catch (JSONException e) {
            // Якщо виникла помилка при парсингу JSON, то JSON не є валідним
            System.out.println("JSON is valid");
        }
    }

    @Override
    public void visit(XmlDataFormat xmlDataFormat) {
        try {
            // Create a new DocumentBuilder instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML document
            Document document = builder.parse(xmlDataFormat.getOriginalData());

            // Check for any errors during parsing
            NodeList errorList = document.getElementsByTagName("parsererror");
            if (errorList.getLength() > 0) {
                // There were errors during parsing
                throw new IllegalArgumentException("XML valid: " + errorList.item(0).getTextContent());
            }

            // XML is valid
            System.out.println("XML is valid.");
        } catch (Exception e) {
            //System.err.println("Error validating XML: " + e.getMessage());
        }
    }
}
