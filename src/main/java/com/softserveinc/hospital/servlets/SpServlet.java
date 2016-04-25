package com.softserveinc.hospital.servlets;

import com.softserveinc.hospital.DAO.DoctorDAO;
import com.softserveinc.hospital.DAO.Speciality1DAO;
import com.softserveinc.hospital.DAO.SpecialityDAO;
import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksu on 11.04.16.
 */
@WebServlet(name = "SpServlet", value = "/ss")
public class SpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        SpecialityDAO sDAO = new SpecialityDAO();
        DoctorDAO dDAO = new DoctorDAO();
        if (action.equals("add")) {
            sDAO.setSpecialities(new Specialities(request.getParameter("title")));
            response.sendRedirect("ms?action=Specialities");
        }else if(action.equals("add speciality")){
            request.setAttribute("title",request.getParameter("title"));
            request.setAttribute("name",request.getParameter("doctor.firstNAme"));
            request.setAttribute("id",request.getParameter("doctor.id"));
            dDAO.updateDoctor(request.getParameter("doctor.firstName"),
                    request.getParameter("title"),Long.parseLong(request.getParameter("doctor.id")));
            request.getRequestDispatcher("binding.jsp").forward(request, response);
        }
        else if (action.equals("show empty")) {
            List<Specialities> list = (ArrayList) sDAO.showEmpty();
            request.setAttribute("page", "Specialities");
            request.setAttribute("specialities", list);
            request.getRequestDispatcher("specialities.jsp").forward(request, response);
        } else if (action.equals("spec")) {
            List<Doctor> doctors = dDAO.getDoctorBySpeciality(request.getParameter("title"));
            request.setAttribute("title",request.getParameter("title"));
            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("filterDocBySpec.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            sDAO.updateSpecialities(request.getParameter("title"),Long.parseLong(request.getParameter("id")));
            response.sendRedirect("ms?action=Specialities");
        }


    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("delete empty");
        SpecialityDAO sDAO = new SpecialityDAO();
        // sDAO.deleteEmptySpeciality();
        response.sendRedirect("ms?action=Specialities");
    }
}
