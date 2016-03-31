package com.softserveinc.hospital.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ksu on 30.03.16.
 */
public class JSONDoctorTwo implements Convertible {
    @Override
    public void writeToFile(Doctor doctor, String fileName) {
        JSONObject jsonO = new JSONObject();
        jsonO.put("id",doctor.getId());
        jsonO.put("firstName",doctor.getFirstName());
        jsonO.put("lastName",doctor.getLastName());

        JSONArray list = new JSONArray();
        ArrayList<String> specs = doctor.getSpecialties();
        for (int i = 0; i < specs.size(); i++) {
            list.add(specs.get(i));
        }
        jsonO.put("listSpecs",specs);
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(jsonO.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Doctor readFromFile(String fileName) {
        JSONParser parser = new JSONParser();
        Doctor doctor = null;
        try {

            Object obj = parser.parse(new FileReader(fileName));

            JSONObject jsonObject = (JSONObject) obj;

            Long id = (Long) jsonObject.get("id");
            doctor.setId(id);

            String firstName = (String) jsonObject.get("firstName");
            doctor.setFirstName(firstName);
            String lastName = (String) jsonObject.get("lastName");
            doctor.setLastName(lastName);
            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("listSpecs");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            doctor.setSpecialties(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
