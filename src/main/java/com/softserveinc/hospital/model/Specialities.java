package com.softserveinc.hospital.model;

/**
 * Created by ksu on 06.04.16.
 */
public class Specialities {
    private String title;
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
@Override
    public String toString(){
        return title;
    }
}
