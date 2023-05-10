package com.iharkusha.dataFormat.formatImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.validation.ValidatorVisitor;
import com.iharkusha.json.JsonArray;
import com.iharkusha.json.JsonObject;
import com.iharkusha.json.JsonString;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;


public class JsonDataFormat extends DataFormat {

    private String originalData;
    private JsonObject jsonObject;

    public void parse(String data) {
        this.originalData = data;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(data));
            Document doc = builder.parse(is);
            NodeList nodes = doc.getElementsByTagName("*");
            JsonObject.Builder builderObj = new JsonObject.Builder();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                if (element.getChildNodes().getLength() == 1) {
                    builderObj.add(element.getNodeName(), element.getTextContent());
                } else {
                    JsonObject.Builder objBuilder = new JsonObject.Builder();
                    for (int j = 0; j < element.getChildNodes().getLength(); j++) {
                        Node child = element.getChildNodes().item(j);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            objBuilder.add(child.getNodeName(), child.getTextContent());
                        }
                    }
                    builderObj.add(element.getNodeName(), objBuilder.build());
                }
            }
            jsonObject = builderObj.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String render(String data) {
        try {
            // Створення парсера для XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Перетворення XML у структуру дерева об'єктів
            ByteArrayInputStream input = new ByteArrayInputStream(data.getBytes());
            Document doc = builder.parse(input);
            Element root = doc.getDocumentElement();

            // Перетворення дерева об'єктів у JSON об'єкти
            JsonArray jsonArray = new JsonArray();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element element) {
                    JsonObject json = new JsonObject();
                    NamedNodeMap attributes = element.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        json.add(new JsonString(attribute.getNodeName(), attribute.getNodeValue()));
                    }
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode instanceof Element childElement) {
                            json.add(new JsonString(childElement.getTagName(), childElement.getTextContent()));
                        }
                    }
                    jsonArray.add(json);
                }
            }

            // Повернення JSON даних у вигляді рядка
            return jsonArray.toJsonString();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getOriginalData() {
        return originalData;
    }

    @Override
    public void accept(ValidatorVisitor visitor) {
        visitor.visit(this);
    }


}
