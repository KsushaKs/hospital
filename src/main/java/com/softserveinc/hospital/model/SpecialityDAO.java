package com.softserveinc.hospital.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ksu on 05.04.16.
 */
public class SpecialityDAO {
    private static final String SET_SPECIALITY = "INSERT INTO speciality (title) VALUES (?) ";
    private static final String DELETE_SPECIALITY = "DROP TABLE speciality";

    private static final String DELETE_EMPTY_SPECIALITY = "DELETE FROM speciality WHERE id_speciality"
            + " NOT IN (SELECT DISTINCT id_speciality FROM binding)";
    private static final String CREATE_SPECIALITY = "CREATE TABLE speciality (id int NOT NULL auto_increment, " +
            "title VARCHAR(45),PRIMARY KEY (id))";
    public void createSpeciality(){
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(CREATE_SPECIALITY);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSpeciality(){
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(DELETE_SPECIALITY);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteEmptySpeciality(){
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps =null;
        try{
            ps = connection.prepareStatement(DELETE_EMPTY_SPECIALITY);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setDoctorSpeciality(Doctor doctor) {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try {
            for(String spec:doctor.getSpecialties()){
            ps = connection.prepareStatement(SET_SPECIALITY);
            ps.setString(1,spec);}
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
