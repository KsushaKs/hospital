package com.softserveinc.hospital.model;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by ksu on 06.04.16.
 */
@Entity
@Table(name = "specialities")
@SQLInsert(sql="INSERT IGNORE INTO specialities(title) VALUES(?)")
public class Specialities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String title;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "specialities")
    private Set<Doctor> doctors;
    public Specialities(){}
    public Specialities(String title){
        this.title=title;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
@Override
    public String toString(){
        return title;
    }
}
