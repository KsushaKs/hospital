package com.softserveinc.hospital.model;

import java.sql.*;

/**
 * Created by ksu on 04.04.16.
 */
public class MySQLIO implements Convertible {

    @Override
    public void writeToFile(Doctor doctor, String fileName) {

    }

    @Override
    public Doctor readFromFile(String fileName) {
        return null;
    }
}
