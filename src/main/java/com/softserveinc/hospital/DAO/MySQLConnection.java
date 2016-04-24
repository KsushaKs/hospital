package com.softserveinc.hospital.DAO;

import java.sql.*;

/**
 * Created by ksu on 04.04.16.
 */
public class MySQLConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
