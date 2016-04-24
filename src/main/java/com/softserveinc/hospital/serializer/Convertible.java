package com.softserveinc.hospital.serializer;


import com.softserveinc.hospital.model.Doctor;

/**
 * Created by ksu on 30.03.16.
 */
public interface Convertible {
    void writeToFile(Doctor doctor, String fileName);
    Doctor readFromFile(String fileName);
}

