package com.softserveinc.hospital.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserveinc.hospital.serializer.LocalDateAdapterXML;
import com.softserveinc.hospital.serializer.LocalDateJSONDeserializer;
import com.softserveinc.hospital.serializer.LocalDateJSONSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Entity
@Table(name = "doctors")
@XmlRootElement
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer experience;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "doctors_specialities", joinColumns = @JoinColumn(name = "specialities_id"),
            inverseJoinColumns = @JoinColumn(name = "doctors_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"specialities_id","doctors_id"})})
    private List<Specialities> specialities;

    private Boolean available;

    @JsonSerialize(using = LocalDateJSONSerializer.class)
    @JsonDeserialize(using = LocalDateJSONDeserializer.class)
    @Column(name = "birth_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;


    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapterXML.class)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
    public List<Specialities> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Specialities> specialties) {
        this.specialities = specialties;
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean isAvailable) {
        this.available = isAvailable;
    }

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, Integer experience, List<Specialities> specialties, Boolean isAvailable) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.available = isAvailable;
        this.specialities = specialties;

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
        if (specialities != null ? !specialities.equals(doctor.specialities) : doctor.specialities != null) return false;
        return available != null ? available.equals(doctor.available) : doctor.available == null;

    }

    //generated
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (specialities != null ? specialities.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String toReturn;
        String specToString = "";
        StringBuilder sb = new StringBuilder();
        for (Specialities specialty : specialities) {
            specToString = sb.append(specialty).append(", ").toString();
        }
        specToString = sb.delete(specToString.length() - 2, specToString.length()).toString();
        toReturn = this.firstName + " " + this.lastName + " [" + this.birthDate + "] (" + this.experience + "," + (this.available ? 'Y' : 'N') + "):{" + specToString + "}";
        return toReturn;
    }
}