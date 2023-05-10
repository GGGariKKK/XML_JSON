package validation;

import dataFormat.formatImpl.JsonDataFormat;
import dataFormat.formatImpl.XmlDataFormat;

public interface ValidatorVisitor {
    void visit(JsonDataFormat jsonDataFormat);
    void visit(XmlDataFormat xmlDataFormat);
}