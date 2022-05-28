package tz.co.admin.portal.controller;

import com.google.gson.Gson;
import tz.co.admin.portal.model.Class;
import tz.co.admin.portal.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/class")
public class RegisterClassController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Repository repository = new Repository();
        try {
            String className = req.getParameter("className");
            Class studentClass = new Class();
            studentClass.setName(className);
            repository.saveItem(studentClass);
            req.setAttribute("data", className);
            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, resp);
        }catch (Exception e){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
            e.printStackTrace();
        }
    }
}
