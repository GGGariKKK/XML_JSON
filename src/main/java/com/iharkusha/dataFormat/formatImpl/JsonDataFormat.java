package com.iharkusha.dataFormat.formatImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.json.JsonArray;
import com.iharkusha.json.JsonElement;
import com.iharkusha.json.JsonObject;
import com.iharkusha.validation.ConverterExtension;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;


public class JsonDataFormat implements DataFormat {

    private String originalData;
    private JsonElement parsedElement;

    public void parse(String data){
        this.originalData = data;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            ByteArrayInputStream input = new ByteArrayInputStream(data.getBytes());
            Document doc = builder.parse(input);
            Element root = doc.getDocumentElement();

            JsonArray jsonArray = new JsonArray();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element element) {
                    JsonObject.Builder jsonBuilder = new JsonObject.Builder();
                    NamedNodeMap attributes = element.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        jsonBuilder.add(attribute.getNodeName(), attribute.getNodeValue());
                    }
                    NodeList childNodes = element.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if (childNode instanceof Element childElement) {
                            jsonBuilder.add(childElement.getTagName(), childElement.getTextContent());
                        }
                    }
                    jsonArray.add(jsonBuilder.build());
                }
            }
            parsedElement = jsonArray;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String render() {
        return "{ \"root\":" + parsedElement.toJsonString() + "}";
    }

    @Override
    public String getOriginalData() {
        return originalData;
    }

    @Override
    public void accept(ConverterExtension converterExtension) {
        converterExtension.action(this);
    }
}
