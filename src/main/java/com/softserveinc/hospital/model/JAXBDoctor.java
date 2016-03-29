package com.softserveinc.hospital.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBDoctor {

    public void writeToFile(Doctor doctor) {
        try {
            File file = new File("");
            JAXBContext context = JAXBContext.newInstance(Doctor.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(doctor, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Doctor.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Doctor doctor = (Doctor) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}