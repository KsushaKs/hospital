package com.softserveinc.hospital.model;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class test {

    public static void main(String[] args) {
        ArrayList<Specialities> specialties = new ArrayList<>(Arrays.asList(new Specialities("one"), new Specialities("two")));
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
        //System.out.println(doctor);
        //bindingDAO.setBinding(x);
        //System.out.println(doctorDAO.getByID());
        Doctor doctor1 = new Doctor();
        Doctor smith = new Doctor("John", "Smith", 10, specialties, false);
        smith.setBirthDate(new LocalDate(1988, 02, 9));
        //doctorDAO.setDoctor(smith);
        //bindingDAO.setBinding(smith);
        //System.out.println(spDAO.getSpeciality());
       ArrayList<Doctor>doctors = doctorDAO.getDoctors();
           System.out.println(doctors.size());
        System.out.println(doctors.get(0).getAvailable());
        System.out.println(doctors);
       // System.out.println(bindingDAO.getBinding(4));
        //bindingDAO.deleteBinding();
        //doctorDAO.setDoctor(smith);
        //bindingDAO.setBinding(smith);
        //System.out.println(smith.getId());
        //System.out.println(doctor.getId());
        //doctorDAO.setDoctor(doctor);
        ArrayList<Specialities>sp = spDAO.getSpeciality();
        System.out.println(sp.get(1));
        int a = 1;
    }
}
