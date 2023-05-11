package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;

import java.nio.charset.StandardCharsets;

public class UTF16JsonDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new JsonDataFormat(StandardCharsets.UTF_16);
    }
}
