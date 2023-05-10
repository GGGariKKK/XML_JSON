package dataFormat.factoryImpl;

import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import dataFormat.formatImpl.XmlDataFormat;

public class XmlDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat();
    }
}