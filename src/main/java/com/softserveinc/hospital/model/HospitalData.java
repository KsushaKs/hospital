package com.softserveinc.hospital.model;

import java.util.ArrayList;
import java.util.HashMap;

public class HospitalData {
    private ArrayList<Doctor> doctors;
    private ArrayList<String> titles;


    public HospitalData() {
    }




    public ArrayList<Doctor> doctorsBySpec(String speciality){
        ArrayList<Doctor> doctorsSpec = new ArrayList<>();
        for (Doctor doc : doctors){
            for (int i = 0; i < doc.getSpecialties().size(); i++) {
                if(doc.getSpecialties().get(i).equals(speciality)){
                    doctorsSpec.add(doc);
                }
            }
        }
        return doctorsSpec;
    }

    public int countSpec(String speciality) {
        int count = 0;
        for (Doctor doc : doctors) {
            for (int i = 0; i < doc.getSpecialties().size(); i++) {
                if(doc.getSpecialties().get(i).equals(speciality)){
                    count++;
                }
            }
        }
        return count;
    }

    public void removeEmptySpec(ArrayList<Doctor> doctors) {
        HashMap<String, Integer> countDoctors = createMapByDoctorSpec(doctors);
        for (int i = 0; i < titles.size(); i++) {
            String key = titles.get(i);
            if (countDoctors.get(key).equals(0)) {
                countDoctors.remove(key);
            }
        }
    }

    public HashMap<String, Integer> createMapByDoctorSpec(ArrayList<Doctor> doctors) {
        HashMap<String, Integer> countDoctors = new HashMap<String, Integer>();
        for (int i = 0; i < countDoctors.size(); i++) {
            countDoctors.put("", 0);
        }
        for (int i = 0; i < titles.size(); i++) {
            String speciality = titles.get(i);
            for (int j = 0; j < doctors.size(); j++) {
                ArrayList<String> doctorSp = doctors.get(j).getSpecialties();
                for (int k = 0; k < doctorSp.size(); k++) {
                    if (speciality.equals(doctorSp.get(k))) {
                        int value = countDoctors.get(speciality);
                        value++;
                        countDoctors.put(speciality, value);
                    }
                }
            }
        }
        return countDoctors;
    }

}