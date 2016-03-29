package com.softserveinc.hospital.model;

import java.util.ArrayList;

public class Specialty {
    private ArrayList<String> titles;
    private Doctor doctor;

    public Specialty() {
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getTitles() {

        return titles;
    }

    public Specialty(ArrayList<String> titles, Doctor doctor) {
        this.doctor = doctor;
        this.titles = titles;
    }
}