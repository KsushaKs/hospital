package com.softserveinc.hospital.model;

import java.util.ArrayList;
import java.util.HashMap;

public class HospitalData {
    //private Doctor doctor;
    private ArrayList<Doctor> doctors;

    private HashMap<ArrayList<String>, Integer> specials;

    public HospitalData() {
    }

    public void setSpecials(HashMap<ArrayList<String>, Integer> specials) {
        this.specials = specials;
    }

//    public void removeEmptySpec(ArrayList<Doctor> doctors) {
//        HashMap<String, Integer> countDoctors = createMapByDoctorSpec(doctors);
//        Specialty sp = new Specialty();
//        ArrayList<String> specTitles = sp.getTitles();
//        for (int i = 0; i < specTitles.size(); i++) {
//            String key = specTitles.get(i);
//            if (countDoctors.get(key).equals(0)) {
//                countDoctors.remove(key);
//            }
//        }
//    }
//
//    public HashMap<String, Integer> createMapByDoctorSpec(ArrayList<Doctor> doctors) {
//        Specialty sp = new Specialty();
//        ArrayList<String> specTitles = sp.getTitles();
//        HashMap<String, Integer> countDoctors = new HashMap<String, Integer>();
//        for (int i = 0; i < countDoctors.size(); i++) {
//            countDoctors.put("", 0);
//        }
//        for (int i = 0; i < specTitles.size(); i++) {
//            String speciality = specTitles.get(i);
//            for (int j = 0; j < doctors.size(); j++) {
//                ArrayList<Specialty> doctorSp = doctors.get(j).getSpecialties();
//                for (int k = 0; k < doctorSp.size(); k++) {
//                    if (speciality.equals(doctorSp.get(k))) {
//                        int value = countDoctors.get(speciality);
//                        value++;
//                        countDoctors.put(speciality, value);
//                    }
//                }
//            }
//        }
//        return countDoctors;
//    }
}