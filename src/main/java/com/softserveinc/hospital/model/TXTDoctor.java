package com.softserveinc.hospital.model;

import org.joda.time.LocalDate;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Doctor doctor = null;
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            if (isValid(line)) {
                doctor = parse(line);
            }

        } catch (EOFException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public ArrayList<Doctor> getListFromFile(String fileName) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            while (!(line = reader.readLine()).isEmpty()) {
                if (isValid(line)) {
                    Doctor doctor = parse(line);
                    doctors.add(doctor);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private Doctor parse(String line) {
        Doctor doctor = new Doctor();
        String[] tokens = line.split("\\W*");
        doctor.setId(Doctor.getCountID());
        doctor.setFirstName(tokens[0]);
        doctor.setLastName(tokens[1]);
        doctor.setBirthDate(LocalDate.parse(tokens[2]));
        doctor.setExperience(Integer.parseInt(tokens[3]));
        doctor.setAvailable(tokens[4].equals("Y"));
        ArrayList<String> specs = new ArrayList<>();
        specs.add(tokens[5]);
        specs.add(tokens[6]);
        doctor.setSpecialties(specs);
        return doctor;
    }

    private Boolean isValid(String str) {
        Pattern p = Pattern.compile("^[A-Z][a-z]+\\s[A-Z][a-z]+\\s"
                + "\\[\\d{4}\\W(0[1-9]|1[0-2])\\W([0-2][1-9]|3[01])\\]"//todo check date
                + "\\s\\([0-9]+\\,[X|Y]\\)\\:\\{[a-z]+\\,[a-z]+\\}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
