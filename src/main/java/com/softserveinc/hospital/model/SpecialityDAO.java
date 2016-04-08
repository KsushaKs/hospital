package com.softserveinc.hospital.model;

import java.sql.*;
import java.util.ArrayList;

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
    private static final String GET_ID_BY_TITLE = "SELECT id FROM speciality WHERE title=?";
    private static final String GET_SPECIALITY = "SELECT * FROM speciality";
    public ArrayList<Specialities> getSpeciality(){
        ArrayList<Specialities> specs = new ArrayList<>();
        try {
            Statement statement = MySQLConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(GET_SPECIALITY);
            while (rs.next()) {
                Specialities speciality = new Specialities();
                speciality.setId(rs.getLong("id"));
                speciality.setTitle(rs.getString("title"));
                specs.add(speciality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specs;
    }
    public int getIdByTitle(){
        Connection connection = MySQLConnection.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_ID_BY_TITLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
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
            for(Specialities spec:doctor.getSpecialties()){
            ps = connection.prepareStatement(SET_SPECIALITY);
            ps.setString(1,spec.toString());
            ps.executeUpdate();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
