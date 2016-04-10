package com.softserveinc.hospital.model;

import java.sql.*;
import java.util.ArrayList;

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
            for(Specialities spec : doctor.getSpecialties()){
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


