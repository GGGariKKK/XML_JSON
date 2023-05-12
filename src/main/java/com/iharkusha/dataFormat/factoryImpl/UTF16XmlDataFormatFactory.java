package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;
import com.iharkusha.dataFormat.format_observer.Observer;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class UTF16XmlDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat(StandardCharsets.UTF_16);
    }

    @Override
    public DataFormat createDataFormat(Collection<Observer> observers) {
        var format = new XmlDataFormat(StandardCharsets.UTF_16);
        observers.forEach(format::attachObserver);
        return format;
    }
}
