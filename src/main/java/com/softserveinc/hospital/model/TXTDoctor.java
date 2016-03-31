package com.softserveinc.hospital.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ksu on 30.03.16.
 */
public class TXTDoctor implements Convertible {
    @Override
    public void writeToFile(Doctor doctor, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write(doctor.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Doctor readFromFile(String fileName) {
        Doctor doctor = new Doctor();
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String []tokens = line.split(" ");
            doctor.setId(Doctor.getCountID());
            doctor.setFirstName(tokens[0]);
            doctor.setLastName(tokens[1]);
            doctor.setExperience(Integer.parseInt(tokens[2]));
            doctor.setAvailable(Boolean.parseBoolean(tokens[3]));
            String[]strings = tokens[4].split(",");
            ArrayList<String> specs = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                String value = strings[i];
                if(value.startsWith("[")){
                    value = value.substring(1);
                }
                if(value.endsWith("]")){
                    value = value.substring(0,value.length()-2);
                }
                specs.add(value);
            }
            doctor.setSpecialties(specs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
