package com.softserveinc.hospital.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Doctor {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private ArrayList<Specialty> specialties;
    private Boolean isAvailable;

    @XmlAttribute
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
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @XmlElement(name = "specialty")
    @XmlElementWrapper(name = "specialties")
    public ArrayList<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Doctor() {
    }

    public Doctor(Integer id, String firstName, String lastName, Integer experience, ArrayList<Specialty> specialties, Boolean isAvailable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.isAvailable = isAvailable;
        this.specialties = specialties;
    }

}