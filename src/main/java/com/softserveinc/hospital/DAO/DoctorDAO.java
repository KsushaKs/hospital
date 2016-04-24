package com.softserveinc.hospital.DAO;


import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Speciality;
import com.softserveinc.hospital.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.LocalDate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DoctorDAO {


    public void save(Doctor doctor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(doctor);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public List<Doctor> getDoctorList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Doctor> list = new ArrayList<>();
        try {
            list = (List<Doctor>) session.createQuery("from Doctor").list();
            for (Doctor doctor : list) {
                Hibernate.initialize(doctor.getSpecialities());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    @Transactional
    public Doctor getDoctor(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Doctor doctor = new Doctor();
        try {
            doctor = (Doctor) session.get(Doctor.class, id);
            Hibernate.initialize(doctor.getSpecialities());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctor;
    }

    @Transactional
    public void deleteDoctor(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            session.delete(doctor);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public static void main(String[] args) {
        Set<Speciality> specialties =
                new HashSet<>(Arrays.asList(new Speciality("one"), new Speciality("two")));
        Doctor doctor = new Doctor("Ara", "Popugai", 5, specialties, true);
        doctor.setBirthDate(new LocalDate(2000, 2, 12));
        DoctorDAO dd = new DoctorDAO();
        Set<Speciality> sp = new HashSet<>();
        sp.add(new Speciality("surgeon"));
        Doctor doctor2 = new Doctor("Joe", "Smith", 7, sp, false);
        doctor2.setBirthDate(new LocalDate(1978, 4, 9));
        //dd.save(doctor1);
        dd.save(doctor2);
        //dd.deleteDoctor(8);
        //Doctor d = dd.getDoctor(1);
        //System.out.println(dd.getDoctor(1));
    }

}
