package com.softserveinc.hospital.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by ksu on 30.03.16.
 */
public class JSDoctor implements Convertible{
    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void writeToFile(Doctor doctor, String fileName) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName),doctor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Doctor readFromFile(String fileName) {
        Doctor doctor = null;
        try {
            doctor = mapper.readValue(new File(fileName),Doctor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
