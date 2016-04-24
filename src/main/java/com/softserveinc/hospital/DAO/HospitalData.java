package com.softserveinc.hospital.DAO;

import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HospitalData {
    private ArrayList<Doctor> doctors;
    private ArrayList<String> titles;


    public HospitalData() {
    }




    public ArrayList<Doctor> doctorsBySpec(String speciality){
        ArrayList<Doctor> doctorsSpec = new ArrayList<>();
        for (Doctor doc : doctors){
            {
                if(doc.getSpecialities().contains(speciality)){
                    doctorsSpec.add(doc);
                }
            }
        }
        return doctorsSpec;
    }

    private int countSpec(String speciality) {
        int count = 0;
        for (Doctor doc : doctors) {
             {
                if(doc.getSpecialities().contains(speciality)){
                    count++;
                }
            }
        }
        return count;
    }
    public void removeSpec(String speciality){
        if(countSpec(speciality)==0){
            titles.remove(titles.indexOf(speciality));
        }
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
                Set<Specialities> doctorSp = doctors.get(j).getSpecialities();
                 {
                     {
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