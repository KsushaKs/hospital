package com.softserveinc.hospital.serializer;

import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Speciality;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public ArrayList<Doctor> getListFromFile(String fileName) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            while (true) {
                if ((line = reader.readLine()).isEmpty()) {
                    continue;
                }
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
        //doctor.setId(Doctor.getCountID());
        doctor.setFirstName(tokens[0]);
        doctor.setLastName(tokens[1]);
        if(isValidDate(tokens[2])){
        doctor.setBirthDate(LocalDate.parse(tokens[2]));}
        doctor.setExperience(Integer.parseInt(tokens[3]));
        doctor.setAvailable(tokens[4].equals("Y"));
        Set<Speciality> specs = new HashSet<>();
        specs.add(new Speciality(tokens[5]));
        specs.add(new Speciality(tokens[6]));
        doctor.setSpecialities(specs);
        return doctor;
    }

    private Boolean isValidDate(String date) {
        DateTime dateTime = DateTime.parse(date);
        DateTime dt = new DateTime();
        int diff = Years.yearsBetween(dateTime,dt).getYears();
        if(diff>=20){
            return true;
        }
        return false;
    }

    private Boolean isValid(String str) {
        Pattern p = Pattern.compile("^[A-Z][a-z]+\\s[A-Z][a-z]+\\s\\[\\d{4}\\W\\d{2}\\W\\d{2}]\\s\\([0-9]+,[X|Y]\\):\\{[a-z]+,[a-z]+\\}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {
        TXTDoctor d = new TXTDoctor();
        System.out.println(d.isValid("Akks Asss [2000/09-27] (888,Y):{fgg,djj}"));
    }
}
