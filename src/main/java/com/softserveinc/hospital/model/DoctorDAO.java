package com.softserveinc.hospital.model;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Created by ksu on 04.04.16.
 */
public class DoctorDAO {

    private static final String SELECT_BY_ID = "SELECT * FROM doctors WHERE id=%d";
    private static final String INSERT_DOCTOR = "INSERT INTO doctors " +
            "(id,first_name, last_name, birthday, experience, available) " +
            "VALUES (?,?, ?, ?, ?, ?)";
    private static final String DELETE_DOCTOR = "DELETE FROM doctors WHERE id=?";
    private static final String CREATE_DOCTORS = "CREATE TABLE doctors " +
            "(id int NOT NULL UNIQUE ,first_name VARCHAR (45), last_name VARCHAR (45), " +
            "birthday DATE , experience int , available VARCHAR (1))";
    private static final String DROP_DOCTORS = "DROP TABLE doctors";
    private static final String UPDATE_DOCTOR_AVAILABLE = "UPDATE doctors SET available='Y' WHERE available='N'";

    public void createDoctors() {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(CREATE_DOCTORS);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctors() {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DROP_DOCTORS);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try {
            Statement statement = MySQLConnection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                doctors.add(getDoc(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public Doctor getByID(Integer id) {
        String query = String.format(SELECT_BY_ID, id);
        Doctor doctor = null;
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = MySQLConnection.getConnection();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            rs.next();
            doctor = getDoc(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            doctor.setSpecialties(new ArrayList<Specialities>(Arrays.asList(new Specialities(), new Specialities())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_DOCTOR);
            ps.setLong(1, doctor.getId());
            ps.setString(2, doctor.getFirstName());
            ps.setString(3, doctor.getLastName());
            ps.setDate(4, new Date(doctor.getBirthDate().toDate().getTime()));
            ps.setInt(5, doctor.getExperience());
            ps.setString(6, (doctor.getAvailable() ? "Y" : "N"));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDoctor(Doctor doctor) {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE doctors SET first_name=?,last_name=?,birthday=?,experience=?,available=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setDate(3, new Date(doctor.getBirthDate().toDate().getTime()));
            ps.setInt(4, doctor.getExperience());
            ps.setString(5, (doctor.getAvailable() ? "Y" : "N"));
            ps.setLong(6, doctor.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctorAvailable() {
        PreparedStatement statement = null;
        Connection connection = MySQLConnection.getConnection();
        try {
            statement = connection.prepareStatement(UPDATE_DOCTOR_AVAILABLE);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void deleteDoctor(int id) {
        PreparedStatement statement = null;
        Connection connection = MySQLConnection.getConnection();
        try {

            statement = connection.prepareStatement(DELETE_DOCTOR);
            statement.setLong(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

