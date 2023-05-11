package com.iharkusha.convert;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.validation.DataFormatExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class Converter {
    protected DataFormatFactory dataFormatFactory;
    private DataFormatExtension dataFormatExtension;

    protected Converter(DataFormatFactory dataFormatFactory, DataFormatExtension validator) {
        this.dataFormatExtension = validator;
        this.dataFormatFactory = dataFormatFactory;
    }

    public final String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DataFormat dataFormat = dataFormatFactory.createDataFormat();
        parseData(dataFormat, data);
        dataFormat.accept(dataFormatExtension);
        return renderData(dataFormat);
    }

    protected abstract void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    protected abstract String renderData(DataFormat dataFormat) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

