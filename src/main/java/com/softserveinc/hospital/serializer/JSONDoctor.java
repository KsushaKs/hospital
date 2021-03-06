package com.softserveinc.hospital.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.serializer.Convertible;

import java.io.File;
import java.io.IOException;

/**
 * Created by ksu on 30.03.16.
 */
public class JSONDoctor implements Convertible {
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void writeToFile(Doctor doctor, String fileName) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), doctor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Doctor readFromFile(String fileName) {
        Doctor doctor = null;
        try {
            doctor = mapper.readValue(new File(fileName), Doctor.class);
        } catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
