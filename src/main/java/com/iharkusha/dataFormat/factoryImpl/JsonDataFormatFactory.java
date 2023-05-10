package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;

public class JsonDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new JsonDataFormat();
    }
}

