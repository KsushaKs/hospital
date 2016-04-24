package com.softserveinc.hospital.DAO;

import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ksu on 05.04.16.
 */
public class Speciality1DAO {
    private static final String SET_SPECIALITY = "INSERT INTO speciality (title) VALUES (?) ";
    private static final String DELETE_SPECIALITY = "DROP TABLE speciality";
    private static final String UPDATE_SPECIALITY="UPDATE speciality SET title=? where id=?";
    private static final String DELETE_EMPTY_SPECIALITY = "DELETE FROM speciality WHERE id"
            + " NOT IN (SELECT DISTINCT id_speciality FROM binding)";
    private static final String CREATE_SPECIALITY = "CREATE TABLE speciality (id int NOT NULL auto_increment, " +
            "title VARCHAR(45),PRIMARY KEY (id))";
    private static final String GET_ID_BY_TITLE = "SELECT id FROM speciality WHERE title=?";
    private static final String GET_SPECIALITY = "SELECT * FROM speciality";
    private static final String GET_DOCS_BY_TITLE="SELECT d.id, d.first_name,d.last_name,d.birthday, " +
            "d.experience,d.available,s.id FROM doctors as d join binding as b on d.id=b.id_doctor " +
            "join speciality as s on b.id_speciality= s.id where s.title=\"%s\"";
    public ArrayList<Doctor> getDocBySpeciality(String title){
        ArrayList<Doctor> doctors = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        Set<Specialities> sp = new HashSet<>();
        try{
            statement = MySQLConnection.getConnection().createStatement();
            rs = statement.executeQuery(String.format(GET_DOCS_BY_TITLE,title));
            while (rs.next()){
                Doctor doctor = new Doctor();
                sp.add(new Specialities(title));
                doctor.setSpecialities(sp);
                doctor.setId(rs.getLong("id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setBirthDate(LocalDate.parse(rs.getString("birthday"), DateTimeFormat.forPattern("yyyy-MM-dd")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setAvailable(rs.getString("available").equalsIgnoreCase("Y"));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }
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
    public void updateTitle(String title, long id){
        Connection connection = MySQLConnection.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement(UPDATE_SPECIALITY);
            ps.setString(1,title);
            ps.setLong(2,id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setTitle(String title){
        Connection connection = MySQLConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SET_SPECIALITY);
            ps.setString(1,title);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getIdByTitle(String title){
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
            for(Specialities spec:doctor.getSpecialities()){
            ps = connection.prepareStatement(SET_SPECIALITY);
            ps.setString(1,spec.toString());
            ps.executeUpdate();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
