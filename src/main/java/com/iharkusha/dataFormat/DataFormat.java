package com.iharkusha.dataFormat;

import com.iharkusha.dataFormat.format_observer.Observer;
import com.iharkusha.validation.DataFormatExtension;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public abstract class DataFormat {
    private final Charset encoding;
    List<Observer> observers = new ArrayList<>();

    public DataFormat(Charset encoding) {
        this.encoding = encoding;
    }

    public abstract void parse(String data) throws ParserConfigurationException, IOException, SAXException;

    public abstract String render() throws TransformerException;

    public abstract void accept(DataFormatExtension visitor);

    public abstract String getOriginalData();

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(int index) {
        observers.remove(index);
    }

    public void notifyObservers(){
        observers.forEach(Observer::updateState);
    }

    public Charset getEncoding() {
        return encoding;
    }
}
