package com.softserveinc.hospital.model;



import javax.print.Doc;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class test {

    public static void main(String[] args) {
        ArrayList<String > specialties = new ArrayList<>(asList("one", "two"));
        Doctor doctor = new Doctor(1L, "Ara", "Popugai", 5, specialties, true);
        JAXBDoctor jaxbDoctor = new JAXBDoctor();
        Doctor doc1 = jaxbDoctor.readFromFile("/home/ksu/workspace/hospital/generatedXML/Ara.xml");
        jaxbDoctor.writeToFile(doc1,"Ara2");
        TXTDoctor txtDoctor = new TXTDoctor();
        txtDoctor.writeToFile(doc1,"Ara2.txt");
        int a = 1;
    }
}
