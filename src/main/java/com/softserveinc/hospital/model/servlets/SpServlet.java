package com.softserveinc.hospital.model.servlets;

import com.softserveinc.hospital.model.*;

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
        if (action.equals("add")) {
            sDAO.setTitle(request.getParameter("title"));
            response.sendRedirect("ms?action=Specialities");
        } else if (action.equals("delete")) {
            sDAO.deleteEmptySpeciality();
            response.sendRedirect("ms?action=Specialities");
        }else if(action.equals("spec")){
            ArrayList<Doctor>doctors = sDAO.getDocBySpeciality(request.getParameter("title"));
            request.setAttribute("doctors",doctors);
            request.getRequestDispatcher("filterDocBySpec.jsp").forward(request, response);
        }


    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("delete");
        SpecialityDAO sDAO = new SpecialityDAO();
        sDAO.deleteEmptySpeciality();
        response.sendRedirect("ms?action=Specialities");
    }
}
