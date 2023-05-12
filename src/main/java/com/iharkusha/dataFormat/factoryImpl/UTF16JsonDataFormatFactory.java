package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.format_observer.Observer;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class UTF16JsonDataFormatFactory implements DataFormatFactory {
    @Override
    public DataFormat createDataFormat() {
        return new JsonDataFormat(StandardCharsets.UTF_16);
    }

    @Override
    public DataFormat createDataFormat(Collection<Observer> observers) {
        var format = new JsonDataFormat(StandardCharsets.UTF_16);
        observers.forEach(format::attachObserver);
        return format;
    }
}
