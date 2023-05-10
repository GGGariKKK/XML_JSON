package dataFormat.factoryImpl;

import dataFormat.DataFormat;
import dataFormat.DataFormatFactory;
import dataFormat.formatImpl.JsonDataFormat;

public class JsonDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new JsonDataFormat();
    }
}

