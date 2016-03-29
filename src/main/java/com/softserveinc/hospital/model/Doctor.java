package com.softserveinc.hospital.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private int experience;
    private ArrayList<String> specs;
    private boolean isVailable;

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @XmlElement
    public ArrayList<String> getSpecs() {
        return specs;
    }

    public void setSpecs(ArrayList<String> specs) {
        this.specs = specs;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement

    public boolean isVailable() {
        return isVailable;
    }

    public void setVailable(boolean isVailable) {
        this.isVailable = isVailable;
    }

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, int experience, ArrayList<String> specs, boolean isVailable, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.isVailable = isVailable;
        this.specs = specs;
        this.id = id;
    }

}