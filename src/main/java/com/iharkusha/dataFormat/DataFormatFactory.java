package com.iharkusha.dataFormat;

import com.iharkusha.dataFormat.format_observer.Observer;

import java.util.Collection;

public interface DataFormatFactory {
    DataFormat createDataFormat();
    DataFormat createDataFormat(Collection<Observer> observers);
}


