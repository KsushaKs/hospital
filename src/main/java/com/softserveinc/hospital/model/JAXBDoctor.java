package com.softserveinc.hospital.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBDoctor {

    public static String writeToFile(Doctor doctor, String filename) {
        String path = null;
        try {
            File file = new File(String.format("generatedXML/%s.xml", filename));
            JAXBContext context = JAXBContext.newInstance(Doctor.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(doctor, file);
            path = file.getAbsolutePath();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static Doctor readFromFile(String filename) {
        Doctor doctor = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Doctor.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            doctor = (Doctor) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}