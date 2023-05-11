package com.iharkusha.validation;

import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;

public interface ConverterExtension {
    void action(JsonDataFormat jsonDataFormat);
    void action(XmlDataFormat xmlDataFormat);
}