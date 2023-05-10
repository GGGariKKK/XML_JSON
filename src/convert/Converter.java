package convert;

import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import validation.ValidatorVisitor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class Converter {
    protected DataFormatFactory dataFormatFactory;
    private ValidatorVisitor validatorVisitor;

    protected Converter(DataFormatFactory dataFormatFactory, ValidatorVisitor validator) {
        this.validatorVisitor = validator;
        this.dataFormatFactory = dataFormatFactory;
    }

    public final String convert(String data) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DataFormat dataFormat = dataFormatFactory.createDataFormat();
        parseData(dataFormat, data);
        dataFormat.accept(validatorVisitor);
        return renderData(dataFormat);
    }

    protected abstract void parseData(DataFormat dataFormat, String data) throws ParserConfigurationException, IOException, SAXException, TransformerException;

    protected abstract String renderData(DataFormat dataFormat) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

