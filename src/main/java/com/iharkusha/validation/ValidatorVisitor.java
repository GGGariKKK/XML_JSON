package com.iharkusha.validation;

import com.iharkusha.dataFormat.formatImpl.JsonDataFormat;
import com.iharkusha.dataFormat.formatImpl.XmlDataFormat;

public interface ValidatorVisitor {
    void visit(JsonDataFormat jsonDataFormat);
    void visit(XmlDataFormat xmlDataFormat);
}