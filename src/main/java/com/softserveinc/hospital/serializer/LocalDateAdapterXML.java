package com.softserveinc.hospital.serializer;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapterXML extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {
        return new LocalDate(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

}
