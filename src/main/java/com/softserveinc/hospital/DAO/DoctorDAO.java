package com.softserveinc.hospital.DAO;


import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;
import com.softserveinc.hospital.util.HibernateUtil;
import org.hibernate.*;
import org.joda.time.LocalDate;

import javax.transaction.Transactional;
import java.util.*;


public class DoctorDAO {

    public void setDoctor(Doctor doctor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Specialities sp : doctor.getSpecialities()) {
                session.saveOrUpdate(sp);
            }
            session.persist(doctor);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Doctor> getDoctorList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        SpecialityDAO sd = new SpecialityDAO();
        List<Doctor> list = new ArrayList<>();
        try {
            transaction = session.getTransaction();
            list = session.createQuery("from Doctor as d").list();
            for (Doctor doctor : list) {
                Hibernate.initialize(doctor.getSpecialities());
            }
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public Doctor getDoctor(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Doctor doctor = new Doctor();
        try {
            transaction = session.beginTransaction();
            doctor = (Doctor) session.get(Doctor.class, id);
            Hibernate.initialize(doctor.getSpecialities());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctor;
    }
    @Transactional
    public void updateDoctor(String firstName,String title,long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Doctor doctor = (Doctor) session.get(Doctor.class,id);
            doctor.setFirstName(firstName);
            doctor.getSpecialities().add(new Specialities(title));
            session.update(doctor);
        }catch (HibernateException e){e.printStackTrace();}finally {
            session.close();
        }
    }

    @Transactional
    public List<Doctor> getDoctorBySpeciality(long id) {
        List<Doctor> doctors = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            doctors=session.createQuery("from Doctor as d , Specialities as s where specialities = s.title").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return doctors;
    }

    public void deleteDoctor(long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;{
            session.createQuery("from Doctor as d ");
        }
        try {

            transaction = session.beginTransaction();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            session.delete(doctor);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void deleteTableDoctors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        Set<Specialities> specialties =
                new HashSet<>(Arrays.asList(new Specialities("one"), new Specialities("two")));
        Doctor doctor = new Doctor("Ara", "Popugai", 5, specialties, true);
        doctor.setBirthDate(new LocalDate(2000, 2, 12));
        DoctorDAO dd = new DoctorDAO();
        Set<Specialities> sp = new HashSet<>();
        sp.add(new Specialities("t"));
        Doctor doctor2 = new Doctor("John", "Smith", 7, sp, false);
        doctor2.setBirthDate(new LocalDate(1978, 4, 9));
        //dd.setDoctor(doctor);
       dd.setDoctor(doctor2);
        //dd.deleteDoctor(8);
        //Doctor d = dd.getDoctor(1);
        //System.out.println(dd.getDoctor(1));
        //List<Doctor> ds =  dd.getDoctorBySpeciality(10);
    }

}
