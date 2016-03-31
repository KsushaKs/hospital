package com.softserveinc.hospital.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class Doctor {
    private static Long countID = 0L;
    private Long id;
    private String firstName;
    private String lastName;
    private Integer experience;
    private ArrayList<String> specialties;
    private Boolean isAvailable;
    private Date birthDate;


    @XmlElement
    public String getFirstName() {
        return firstName;
    }
    @XmlElement
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
    public ArrayList<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }

    @XmlAttribute
    public Long getId() {
        id = Doctor.getCountID();
        return id;
    }

    public void setId(Long id) {
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

    public Doctor(Long id, String firstName, String lastName, Integer experience, ArrayList<String> specialties, Boolean isAvailable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.isAvailable = isAvailable;
        this.specialties = specialties;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (id != null ? !id.equals(doctor.id) : doctor.id != null) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (experience != null ? !experience.equals(doctor.experience) : doctor.experience != null) return false;
        if (specialties != null ? !specialties.equals(doctor.specialties) : doctor.specialties != null) return false;
        return isAvailable != null ? isAvailable.equals(doctor.isAvailable) : doctor.isAvailable == null;

    }

    //generated
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (specialties != null ? specialties.hashCode() : 0);
        result = 31 * result + (isAvailable != null ? isAvailable.hashCode() : 0);
        return result;
    }
    public static Long getCountID(){
        countID++;
        return countID;
    }
    @Override
    public String toString() {
        String toReturn;
        String specToString="";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < specialties.size(); i++) {
            specToString =sb.append(specialties.get(i)).append(", ").toString();
        }
        specToString = sb.delete(specToString.length()-3,specToString.length()-1).toString();
        toReturn = this.firstName + " " + this.lastName + " [" +this.birthDate +"] ("+ this.experience + "," +(this.isAvailable?'Y':'N')+"):{"+ specToString+"}";
        return toReturn;
    }
}