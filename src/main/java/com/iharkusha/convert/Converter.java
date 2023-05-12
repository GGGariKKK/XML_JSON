package com.iharkusha.convert;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.format_observer.Observer;
import com.iharkusha.dataFormat.format_observer.ProgressObserver;
import com.iharkusha.validation.DataFormatExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Converter {
    protected DataFormatFactory dataFormatFactory;
    private DataFormatExtension dataFormatExtension;

    protected Converter(DataFormatFactory dataFormatFactory, DataFormatExtension extension) {
        this.dataFormatExtension = extension;
        this.dataFormatFactory = dataFormatFactory;
    }

    public final String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Observer observer =
                new ProgressObserver((int) Pattern.compile("(\".*?\":)|(<.*?>)").matcher(data).results().count());
        DataFormat dataFormat = dataFormatFactory.createDataFormat(List.of(observer));
        parseData(dataFormat, data);
        dataFormat.accept(dataFormatExtension);
        return renderData(dataFormat);
    }

    protected abstract void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    protected abstract String renderData(DataFormat dataFormat) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

