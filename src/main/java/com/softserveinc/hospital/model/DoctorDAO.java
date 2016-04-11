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
        try {
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
        ResultSet rs;
        Statement statement = null;
        try {
            statement = MySQLConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Doctor doctor = new Doctor();
                ArrayList<Specialities> spec = new ArrayList();
                doctor.setSpecialties(spec);
                doctor.setId(rs.getLong("id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setBirthDate(LocalDate.parse(rs.getString("birthday"), DateTimeFormat.forPattern("yyyy-MM-dd")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setAvailable(rs.getString("available").equalsIgnoreCase("Y"));
                spec.add(new Specialities());
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public ArrayList<Doctor> getDoctorsBySpec() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT d.id, d.first_name,d.last_name,d.birthday,d.experience,d.available,s.title " +
                "FROM doctors as d join binding as b on d.id=b.id_doctor " +
                "join speciality as s on b.id_speciality= s.id where d.id=b.id_doctor;";
        ResultSet rs;
        Statement statement = null;
        try {
            statement = MySQLConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            long k = 0L;
            while (rs.next()) {
                Doctor doctor = new Doctor();
                ArrayList<Specialities> spec = new ArrayList();
                doctor.setSpecialties(spec);
                k = rs.getLong("id");
                doctor.setId(k);
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setBirthDate(LocalDate.parse(rs.getString("birthday"), DateTimeFormat.forPattern("yyyy-MM-dd")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setAvailable(rs.getString("available").equalsIgnoreCase("Y"));
                spec.add(new Specialities(rs.getString("title")));
                doctors.add(doctor);
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

    private static Doctor getDoc(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        try {
            while (rs.next()) {
                doctor.setId(rs.getLong("id"));
                doctor.setFirstName(rs.getString("first_name"));
                doctor.setLastName(rs.getString("last_name"));
                doctor.setBirthDate(LocalDate.parse(rs.getString("birthday"), DateTimeFormat.forPattern("yyyy-MM-dd")));
                doctor.setExperience(rs.getInt("experience"));
                doctor.setAvailable(rs.getString("available").equalsIgnoreCase("Y"));
                ArrayList<Specialities> spec = new ArrayList<>();
                spec.add(new Specialities(rs.getString("title")));
                doctor.setSpecialties(spec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = MySQLConnection.getConnection();
        ResultSet rs;
        String query = "SELECT d.id, d.first_name,d.last_name,d.birthday,d.experience,d.available,s.title " +
                "FROM doctors as d join binding as b on d.id=b.id_doctor " +
                "join speciality as s on b.id_speciality= s.id where d.id=b.id_doctor;";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            Doctor doctor = getDoc(rs);
            System.out.println(doctor);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDoctor(Doctor doctor) {
        Connection dbConnection = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;
        String insertTableSQL = INSERT_DOCTOR;
        String updateTableSQL = "INSERT INTO binding(id_speciality,id_doctor)" +
                "VALUES ((SELECT id FROM speciality WHERE title=?),?)";
        try {
            dbConnection = MySQLConnection.getConnection();

            dbConnection.setAutoCommit(false);
            preparedStatementInsert = dbConnection.prepareStatement(INSERT_DOCTOR);
            preparedStatementInsert.setLong(1, doctor.getId());
            preparedStatementInsert.setString(2, doctor.getFirstName());
            preparedStatementInsert.setString(3, doctor.getLastName());
            preparedStatementInsert.setDate(4, new Date(doctor.getBirthDate().toDate().getTime()));
            preparedStatementInsert.setInt(5, doctor.getExperience());
            preparedStatementInsert.setString(6, (doctor.getAvailable() ? "Y" : "N"));
            preparedStatementInsert.executeUpdate();


            for (Specialities spec : doctor.getSpecialties()) {
                preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
                preparedStatementUpdate.setString(1, spec.getTitle());
                preparedStatementUpdate.setLong(2, doctor.getId());
                preparedStatementUpdate.executeUpdate();
            }
            dbConnection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbConnection != null) {
                    dbConnection.close();
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

