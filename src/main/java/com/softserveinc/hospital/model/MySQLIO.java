package com.softserveinc.hospital.model;

import java.sql.*;

/**
 * Created by ksu on 04.04.16.
 */
public class MySQLIO implements Convertible {
    private static final String url = "jdbc:mysql://localhost:3306/Hospital";
    private static String user = "";
    private static String password = "";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public void writeToFile(Doctor doctor, String fileName) {

    }

    @Override
    public Doctor readFromFile(String fileName) {
        String query = "SELECT * FROM Hospital";
        Doctor doctor = new Doctor();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                doctor.setId(rs.getLong(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

}
