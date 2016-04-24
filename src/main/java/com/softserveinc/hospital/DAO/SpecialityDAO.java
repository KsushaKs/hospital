package com.softserveinc.hospital.DAO;

import com.softserveinc.hospital.model.Speciality;
import com.softserveinc.hospital.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SpecialityDAO {


    @Transactional
    public Speciality findByTitle(String title) {
        Speciality speciality = null;
        String query = String.format("SELECT s from Speciality as s WHERE title=%s", title);
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            speciality = (Speciality) session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return speciality;
    }


    public Speciality saveOrUpdate(Speciality speciality) {
        Speciality temp;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            temp = findByTitle(speciality.getTitle());
            if (isNull(temp)) {
                speciality = (Speciality) session.merge(speciality);
            } else {
                speciality = temp;
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return speciality;
    }

    public List<Speciality> getSpecialitiesList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Speciality> sp = new ArrayList<>();
        try {
            transaction = session.getTransaction();
            sp = session.createQuery("from Speciality as s").list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sp;
    }

    public Speciality getSpecialities(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Speciality specialities = new Speciality();
        try {
            transaction = session.beginTransaction();
            specialities = (Speciality) session.get(Speciality.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return specialities;
    }

    public void deleteSpeciality(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.getTransaction();
            Speciality specialities = (Speciality) session.get(Speciality.class, id);
            session.delete(specialities);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        SpecialityDAO sd = new SpecialityDAO();

    }
}
