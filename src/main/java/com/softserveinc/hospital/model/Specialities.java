package com.softserveinc.hospital.model;

/**
 * Created by ksu on 06.04.16.
 */
public class Specialities {
    private String title;
    private Long id;
    public Specialities(){}
    public Specialities(String title){
        this.title=title;
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
