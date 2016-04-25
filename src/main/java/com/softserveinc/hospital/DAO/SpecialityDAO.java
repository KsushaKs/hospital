package com.softserveinc.hospital.DAO;

import com.softserveinc.hospital.model.Specialities;
import com.softserveinc.hospital.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksu on 20.04.16.
 */
public class SpecialityDAO {
    public void setSpecialities(Specialities specialities) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(specialities);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public List<Specialities> getSpecialitiesList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Specialities> sp = new ArrayList<>();
        try{
            transaction = session.getTransaction();
            sp=session.createQuery("from Specialities as s").list();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return sp;
    }

    public void updateSpecialities(String title,long id){
        Session session =HibernateUtil.getSessionFactory().openSession();

        try{
            session.getTransaction().begin();
            Specialities s =(Specialities) session.get(Specialities.class,id);
            s.setTitle(title);
            session.merge(s);
            session.getTransaction().commit();
        }catch (HibernateException e){e.printStackTrace();}finally {
            session.close();
        }
    }

    public Specialities getSpecialities(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Specialities specialities = new Specialities();
        try {
            transaction = session.beginTransaction();
            specialities = (Specialities) session.get(Specialities.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return specialities;
    }
    public void deleteSpeciality(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction=session.getTransaction();
            Specialities specialities =(Specialities)session.get(Specialities.class,id);
            session.delete(specialities);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        SpecialityDAO sd = new SpecialityDAO();
        System.out.println(sd.getSpecialities(45l));
        sd.updateSpecialities("success",45l);
        System.out.println(sd.getSpecialities(45l));

    }
}
