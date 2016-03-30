package com.softserveinc.hospital.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Specialty {

    private ArrayList<String> titles;

//    @XmlAttribute
//    private Doctor doctor;

    public Specialty() {

    }

    public Specialty(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    //    public Specialty(ArrayList<String> titles, Doctor doctor) {
//        this.doctor = doctor;
//        this.titles = titles;
//    }
}