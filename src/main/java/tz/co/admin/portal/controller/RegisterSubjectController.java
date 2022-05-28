package tz.co.admin.portal.controller;

import tz.co.admin.portal.model.Subject;
import tz.co.admin.portal.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subject")
public class RegisterSubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Repository repository = new Repository();
            String subjectName = req.getParameter("subjectName");
            Subject subject = new Subject();
            subject.setSubjectName(subjectName);
            repository.saveItem(subject);
            req.setAttribute("data", subjectName);
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
