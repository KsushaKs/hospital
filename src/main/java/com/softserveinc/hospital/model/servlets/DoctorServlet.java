package com.softserveinc.hospital.model.servlets;

import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.DoctorDAO;
import com.softserveinc.hospital.model.Specialities;
import com.softserveinc.hospital.model.SpecialityDAO;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        ArrayList<Specialities> sp = new ArrayList<>();
        doctor.setSpecialties(sp);
        doctor.setFirstName(request.getParameter("firstName"));
        doctor.setLastName(request.getParameter("lastName"));
        doctor.setExperience(Integer.parseInt(request.getParameter("experience")));
        doctor.setBirthDate(LocalDate.parse(request.getParameter("dob"), DateTimeFormat.forPattern("yyyy-MM-dd")));
        doctor.setAvailable(request.getParameter("available").equalsIgnoreCase("true"));
        sp.add(new Specialities(request.getParameter("speciality")));
        dao.setDoctor(doctor);
        try {
            response.sendRedirect("ms?action=Doctors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
