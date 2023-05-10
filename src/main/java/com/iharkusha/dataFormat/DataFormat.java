package com.iharkusha.dataFormat;

import com.iharkusha.validation.ValidatorVisitor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class DataFormat {
    public abstract void parse(String data) throws ParserConfigurationException, IOException, SAXException;
    public abstract String render() throws TransformerException;
    public abstract String getOriginalData();
    public abstract void accept(ValidatorVisitor visitor);
}
