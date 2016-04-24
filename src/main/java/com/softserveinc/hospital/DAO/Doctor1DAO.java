package com.softserveinc.hospital.DAO;


import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ksu on 04.04.16.
 */
public class Doctor1DAO {

    private static final String SELECT_BY_ID = "SELECT * FROM doctors WHERE id=%d";
    private static final String INSERT_DOCTOR = "INSERT INTO doctors " +
            "(first_name, last_name, birthday, experience, available) " +
            "VALUES (?, ?, ?, ?, ?)";
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
                doctor.setSpecialities(spec);
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
                doctor.setSpecialities(spec);
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
                doctor.setSpecialities(spec);
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
        PreparedStatement specSt=null;
        String updateSp = "INSERT IGNORE INTO speciality(title) VALUES(?)";
        String updateTableSQL = "INSERT IGNORE INTO binding(id_speciality,id_doctor)" +
                "VALUES ((SELECT id FROM speciality WHERE title=?),@lastID)";
        try {
            dbConnection = MySQLConnection.getConnection();

            dbConnection.setAutoCommit(false);
            preparedStatementInsert = dbConnection.prepareStatement(INSERT_DOCTOR);
           // preparedStatementInsert.setLong(1, doctor.getId());
            preparedStatementInsert.setString(1,doctor.getFirstName());
            preparedStatementInsert.setString(2, doctor.getLastName());
            preparedStatementInsert.setDate(3, new Date(doctor.getBirthDate().toDate().getTime()));
            preparedStatementInsert.setInt(4, doctor.getExperience());
            preparedStatementInsert.setString(5, (doctor.getAvailable() ? "Y" : "N"));
            preparedStatementInsert.executeUpdate();
            preparedStatementInsert = dbConnection.prepareStatement("SET @lastID := LAST_INSERT_ID()");
            preparedStatementInsert.executeUpdate();

            for(Specialities specialities:doctor.getSpecialities()){
                specSt = dbConnection.prepareStatement(updateSp);
                specSt.setString(1,specialities.getTitle());
                specSt.executeUpdate();
            }
            HashSet<String> strings = new HashSet<>();
            for(Specialities s:doctor.getSpecialities()){
                strings.add(s.getTitle());
            }
            for (String spec : strings) {
                preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
                preparedStatementUpdate.setString(1, spec);
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
    private Boolean isValidDate(String date) {
        DateTime dateTime = DateTime.parse(date);
        DateTime dt = new DateTime();
        int diff = Years.yearsBetween(dateTime,dt).getYears();
        if(diff>=20){
            return true;
        }
        return false;
    }

    private Boolean isValid(String str) {
        Pattern p = Pattern.compile("^[A-Z][a-z]+\\s[A-Z][a-z]+\\s\\[\\d{4}\\W\\d{2}\\W\\d{2}]\\s\\([0-9]+,[X|Y]\\):\\{[a-z]+,[a-z]+\\}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}

