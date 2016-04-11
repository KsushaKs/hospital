package com.softserveinc.hospital.model.servlets;

import com.softserveinc.hospital.model.BindingDAO;
import com.softserveinc.hospital.model.DoctorDAO;
import com.softserveinc.hospital.model.Specialities;
import com.softserveinc.hospital.model.SpecialityDAO;

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

        List dataList = new ArrayList();
        Specialities specialities;
        SpecialityDAO sDAO = new SpecialityDAO();
        DoctorDAO dDAO = new DoctorDAO();
        BindingDAO bDAO = new BindingDAO();
        switch (action) {
            case "Specialities":
                dataList = sDAO.getSpeciality();
                request.setAttribute("page", "Specialities");
                request.setAttribute("specialities", dataList);
                request.getRequestDispatcher("specialities.jsp").forward(request, response);
                break;
            case "Doctors":
                dataList = dDAO.getDoctors();
                request.setAttribute("page", "Doctors");
                request.setAttribute("doctors", dataList);
                request.getRequestDispatcher("doctors.jsp").forward(request, response);
            break;
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
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