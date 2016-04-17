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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("^[A-Z][a-z]{0,15}$");
        boolean b=true;
        b=p.matcher(request.getParameter("firstName")).matches();
        doctor.setFirstName(p.matcher(request.getParameter("firstName"))
                .matches()?request.getParameter("firstName"):"1");
        doctor.setLastName(p.matcher(request.getParameter("lastName"))
                .matches()?request.getParameter("lastName"):"2");
        doctor.setExperience(Integer.parseInt(Pattern.compile("^\\d+$")
                .matcher(request.getParameter("experience")).matches()?request.getParameter("experience"):"-1"));
        doctor.setBirthDate(LocalDate.parse(request.getParameter("dob"), DateTimeFormat.forPattern("yyyy-MM-dd")));
        doctor.setAvailable(request.getParameter("available").equalsIgnoreCase("true"));
        String[] split = request.getParameter("speciality").split("\\W");
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
