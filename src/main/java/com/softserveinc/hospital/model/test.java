package com.softserveinc.hospital.model;



import javax.print.Doc;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class test {

    public static void main(String[] args) {
        ArrayList<String > specialties = new ArrayList<>(asList("one", "two"));
        Doctor doctor = new Doctor(1L, "Ara", "Popugai", 5, specialties, true);

    }
}
