package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;

public class XmlDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat();
    }
}