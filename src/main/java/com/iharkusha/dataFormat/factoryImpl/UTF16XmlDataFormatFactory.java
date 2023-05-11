package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;

import java.nio.charset.StandardCharsets;

public class UTF16XmlDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat(StandardCharsets.UTF_16);
    }
}
