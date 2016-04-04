package com.softserveinc.hospital.model;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.print.Doc;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Created by ksu on 04.04.16.
 */
public class DoctorDAO {

    public  ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try{
            ResultSet rs = MySQLConnection.getResultSet(query);
            while (rs.next()){
                doctors.add(getDoc(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return doctors;
    }
    public  Doctor getByID(Integer id) {
        String query = String.format("SELECT * FROM doctors WHERE doctors.id=%d", id);
        Doctor doctor = null;
        try {
            ResultSet rs = MySQLConnection.getResultSet(query);
            rs.next();
            doctor = getDoc(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    private Doctor getDoc(ResultSet rs) {
        Doctor doctor = new Doctor();
        try {
            doctor.setId((long) rs.getInt("id"));
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setBirthDate(LocalDate.parse(rs.getString("birthday"), DateTimeFormat.forPattern("yyyy-MM-dd")));
            doctor.setExperience(rs.getInt("experience"));
            doctor.setAvailable(rs.getString("available").equalsIgnoreCase("Y"));
            doctor.setSpecialties(new ArrayList<String>(Arrays.asList("one","two")));
        } catch (Exception e){
            e.printStackTrace();
        }
        return doctor;
    }
}
