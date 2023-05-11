package com.iharkusha.validation;

import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;

public interface DataFormatExtension {
    void action(JsonDataFormat jsonDataFormat);
    void action(XmlDataFormat xmlDataFormat);
}