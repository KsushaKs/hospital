package com.softserveinc.hospital.model;


/**
 * Created by ksu on 30.03.16.
 */
public interface Convertible {
    void writeToFile(Doctor doctor,String fileName);
    Doctor readFromFile(String fileName);
}

