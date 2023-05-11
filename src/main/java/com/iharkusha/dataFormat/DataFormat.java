package com.iharkusha.dataFormat;

import com.iharkusha.validation.DataFormatExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class DataFormat {
    private final Charset encoding;

    public DataFormat(Charset encoding) {
        this.encoding = encoding;
    }

    public abstract void parse(String data) throws ParserConfigurationException, IOException, SAXException;

    public abstract String render() throws TransformerException;

    public abstract void accept(DataFormatExtension visitor);

    public Charset getEncoding() {
        return encoding;
    }
}
