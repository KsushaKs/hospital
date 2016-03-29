package com.softserveinc.hospital.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Specialty {

    private String title;

//    @XmlAttribute
//    private Doctor doctor;

    public Specialty() {

    }

    public Specialty(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public Specialty(ArrayList<String> titles, Doctor doctor) {
//        this.doctor = doctor;
//        this.titles = titles;
//    }
}