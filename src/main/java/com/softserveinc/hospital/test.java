package com.softserveinc.hospital;


import com.softserveinc.hospital.DAO.BindingDAO;
import com.softserveinc.hospital.DAO.DoctorDAO;
import com.softserveinc.hospital.DAO.SpecialityDAO;
import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Speciality;
import com.softserveinc.hospital.serializer.JSONDoctor;
import com.softserveinc.hospital.serializer.TXTDoctor;
import com.softserveinc.hospital.serializer.XMLDoctor;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        Set<Speciality> specialties = new HashSet<>(Arrays.asList(new Speciality("one"), new Speciality("two")));
        Doctor doctor = new Doctor("Ara", "Popugai", 5, specialties, true);
        doctor.setBirthDate(new LocalDate(2000, 2, 12));
        XMLDoctor jaxbDoctor = new XMLDoctor();
        jaxbDoctor.writeToFile(doctor, "Arax");
        JSONDoctor jsonDoctor = new JSONDoctor();
        jsonDoctor.writeToFile(doctor, "/home/ksu/workspace/hospital/Ara.json");
        TXTDoctor tx = new TXTDoctor();
        Doctor x = jsonDoctor.readFromFile("/home/ksu/workspace/hospital/Ara.json");
        DoctorDAO doctorDAO = new DoctorDAO();
        SpecialityDAO spDAO = new SpecialityDAO();
        BindingDAO bindingDAO = new BindingDAO();
        Doctor doctor1 = new Doctor();
        Doctor smith = new Doctor("Mary", "Brawn", 10, specialties, false);
        smith.setBirthDate(new LocalDate(1988, 02, 9));
        bindingDAO.createBinding();
        doctorDAO.save(smith);
        int a = 1;
    }
}
