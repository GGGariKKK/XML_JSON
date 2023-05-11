package com.iharkusha.convert.impl;

import com.iharkusha.convert.Converter;
import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.validation.DataFormatExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class JsonConverter extends Converter {

    public JsonConverter(DataFormatFactory dataFormatFactory, DataFormatExtension dataFormatExtension) {
        super(dataFormatFactory, dataFormatExtension);
    }

    @Override
    protected void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException {
        dataFormat.parse(data);
    }

    @Override
    protected String renderData(DataFormat dataFormat) throws TransformerException {
        return dataFormat.render();
    }
}

