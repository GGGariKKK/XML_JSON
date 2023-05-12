package com.iharkusha.dataFormat.factoryImpl;

import com.iharkusha.dataFormat.DataFormat;
import com.iharkusha.dataFormat.DataFormatFactory;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;
import com.iharkusha.dataFormat.format_observer.Observer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class XmlDataFormatFactory implements DataFormatFactory {
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    public DataFormat createDataFormat() {
        return new XmlDataFormat(charset);
    }

    @Override
    public DataFormat createDataFormat(Collection<Observer> observers) {
        var format = new XmlDataFormat(charset);
        observers.forEach(format::attachObserver);
        return format;
    }
}