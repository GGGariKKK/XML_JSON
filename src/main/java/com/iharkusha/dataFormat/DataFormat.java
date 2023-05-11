package com.iharkusha.dataFormat;

import com.iharkusha.validation.ConverterExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface DataFormat {
    void parse(String data) throws ParserConfigurationException, IOException, SAXException;
    String render() throws TransformerException;
    String getOriginalData();
    void accept(ConverterExtension visitor);
}
