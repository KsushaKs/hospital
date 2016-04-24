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
public class BindingDAO {
    private static final String CREATE_BINDING = "CREATE TABLE binding (id int NOT NULL auto_increment," +
            " id_speciality int NOT NULL, id_doctor int NOT NULL, PRIMARY KEY (id), " +
            "FOREIGN KEY (id_speciality) REFERENCES speciality(id) ON UPDATE CASCADE ON DELETE RESTRICT, " +
            "FOREIGN KEY (id_doctor) REFERENCES doctors(id) " +
            "ON UPDATE RESTRICT ON DELETE CASCADE) ";
    private static final String DROP_BINDING = "DROP TABLE binding";
    private static final String SET_SPECIALITY_ID = "INSERT INTO binding (id_speciality) VALUES(?)";
    private static final String SET_BINDING = "INSERT INTO binding(id_speciality,id_doctor)" +
            "VALUES ((SELECT id FROM speciality WHERE title=?),?)";
    private static final String GET_BINDING_BY_DOCTOR= "SELECT id_speciality FROM binding WHERE id_doctor=?";
    private static final String GET_DOCTOR_BY_ID="SELECT d.id, d.first_name,d.last_name,d.birthday,d.experience,d.available,s.title " +
            "FROM doctors as d join binding as b on d.id=b.id_doctor " +
            "join speciality as s on b.id_speciality= s.id where d.id=%d";
    public ArrayList<Doctor> getDocBySpec(Long id){
        ArrayList<Doctor> doctors = new ArrayList<>();
        ResultSet rs;
        Statement statement = null;
        try {
            statement = MySQLConnection.getConnection().createStatement();
            rs = statement.executeQuery(String.format(GET_DOCTOR_BY_ID,id));
            while (rs.next()) {
                Doctor doctor = new Doctor();
                Set<Specialities> spec = new HashSet<>();
                doctor.setSpecialities(spec);
                doctor.setId(id);
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
    public ArrayList<Long> getBinding(int id){
        ArrayList<Long> bindings = new ArrayList<>();
        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_BINDING_BY_DOCTOR);
            ps.setLong(1,id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bindings.add(rs.getLong("id_speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bindings;
    }
    public void setBinding(Doctor doctor){
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try{
            for(Specialities spec : doctor.getSpecialities()){
            ps = connection.prepareStatement(SET_BINDING);
            ps.setString(1,spec.getTitle());
            ps.setLong(2,doctor.getId());
            ps.execute();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createBinding(){
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps=null;
        try{
            ps=connection.prepareStatement(CREATE_BINDING);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBinding() {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps=null;
        try {
            ps = connection.prepareStatement(DROP_BINDING);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps!=null) {
                    ps.close();
                }
                if(connection!=null){
                    MySQLConnection.closeConnection(connection);
                }
            }catch (SQLException e){e.printStackTrace();}
        }
    }

    public void setSetCpecialityId(int id) {
        Connection connection = MySQLConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SET_SPECIALITY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


