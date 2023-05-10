package com.iharkusha.convert.impl;

import com.iharkusha.convert.Converter;
import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.validation.ValidatorVisitor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlConverter extends Converter {

    public XmlConverter(DataFormatFactory dataFormatFactory, ValidatorVisitor validatorVisitor) {
        super(dataFormatFactory, validatorVisitor);
    }

    @Override
    protected void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // Парсимо XML
        dataFormat.parse(data);
    }

    @Override
    protected String renderData(DataFormat dataFormat) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // Отримуємо сконвертовані JSON дані
        return dataFormat.render(dataFormat.getOriginalData());
    }
}

