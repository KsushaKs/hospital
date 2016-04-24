package com.softserveinc.hospital.servlets;

import com.softserveinc.hospital.DAO.DoctorDAO;
import com.softserveinc.hospital.model.Doctor;
import com.softserveinc.hospital.model.Specialities;
import com.softserveinc.hospital.DAO.SpecialityDAO;
import com.softserveinc.hospital.DAO.BindingDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksu on 07.04.16.
 */
@WebServlet(name = "MS", value = "/ms")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        ArrayList dataList = new ArrayList();
        Specialities specialities;
        SpecialityDAO sDAO = new SpecialityDAO();
        DoctorDAO dDAO = new DoctorDAO();
        BindingDAO bDAO = new BindingDAO();
        switch (action) {
            case "Specialities":
                dataList = (ArrayList) sDAO.getSpecialitiesList();
                request.setAttribute("page", "Specialities");
                request.setAttribute("specialities", dataList);
                request.getRequestDispatcher("specialities.jsp").forward(request, response);
                break;
            case "Doctors":
                ArrayList<Doctor> list = (ArrayList<Doctor>) dDAO.getDoctorList();
                request.setAttribute("page", "Doctors");
                request.setAttribute("doctors", list);
                request.getRequestDispatcher("doctors.jsp").forward(request, response);
            break;
            case "delete":
                long id = Long.parseLong(request.getParameter("id"));
                dDAO.deleteDoctor(id);
                response.sendRedirect("ms?action=Doctors");
                break;
            default:
                int act = Integer.parseInt(action);
                dataList = bDAO.getDocBySpec((long)act);
                request.setAttribute("id_doctor",act);
                request.setAttribute("page","Binding");
                request.setAttribute("binding", dataList);
                request.getRequestDispatcher("binding.jsp").forward(request, response);
        }
    }
}