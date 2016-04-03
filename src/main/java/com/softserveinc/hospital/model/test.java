package com.softserveinc.hospital.model;



import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class test {

    public static void main(String[] args) {
        ArrayList<String > specialties = new ArrayList<>(asList("one", "two"));
        Doctor doctor = new Doctor(1L, "Ara", "Popugai", 5, specialties, true);
        doctor.setBirthDate(new LocalDate(2000, 2, 12));
        XMLDoctor jaxbDoctor = new XMLDoctor();
        jaxbDoctor.writeToFile(doctor,"Arax");
        JSONDoctor jsonDoctor = new JSONDoctor();
        jsonDoctor.writeToFile(doctor,"/home/ksu/workspace/hospital/Ara.json");
        TXTDoctor txtDoctor = new TXTDoctor();
        //System.out.println(txtDoctor.isValid("Akks Asss [2000/09-27] (888,Y):{fgg,djj}"));
        Doctor x = jsonDoctor.readFromFile("/home/ksu/workspace/hospital/Ara.json");
        int a = 1 ;
    }
}
