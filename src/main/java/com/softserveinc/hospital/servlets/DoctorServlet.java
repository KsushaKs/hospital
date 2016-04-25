package com.softserveinc.hospital.servlets;

import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.DAO.DoctorDAO;
import com.softserveinc.hospital.model.Specialities;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ksu on 11.04.16.
 */
@WebServlet(name = "addDoctor", value = "/addDoctor")
public class DoctorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("add Doctor");
        response.sendRedirect("addDoctor.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("Submit");
        Doctor doctor = new Doctor();
        DoctorDAO dao = new DoctorDAO();
        Set<Specialities> sp = new HashSet<>();
        doctor.setSpecialities(sp);
        doctor.setFirstName(request.getParameter("firstName"));
        doctor.setLastName(request.getParameter("lastName"));
        doctor.setExperience(Integer.parseInt(request.getParameter("experience")));
        doctor.setBirthDate(LocalDate.parse(request.getParameter("dob"), DateTimeFormat.forPattern("yyyy-MM-dd")));
        doctor.setAvailable(request.getParameter("available").equalsIgnoreCase("true"));
        String[] split = request.getParameter("speciality").split(",");
        for(String s:split){
            sp.add(new Specialities(s));
        }
        dao.setDoctor(doctor);
        try {
            response.sendRedirect("ms?action=Doctors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
