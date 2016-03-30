package com.softserveinc.hospital.model;


import java.io.File;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class test {

    public static void main(String[] args) {
        ArrayList<String > specialties = new ArrayList<>(asList("one", "two"));
        Doctor doctor = new Doctor(1L, "Ara", "Popugai", 5, specialties, true);
        String marshaledFile = JAXBDoctor.writeToFile(doctor, doctor.getFirstName());
        Doctor unmarshaledDoctor = JAXBDoctor.readFromFile(marshaledFile);

        int a = 1;
    }
}
