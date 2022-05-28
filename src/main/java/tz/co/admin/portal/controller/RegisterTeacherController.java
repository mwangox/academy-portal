package tz.co.admin.portal.controller;
import tz.co.admin.portal.model.Teacher;
import tz.co.admin.portal.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "teacher-controller", value = "/teacher")
public class RegisterTeacherController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Repository repository = new Repository();

        try {
            String teacherName = req.getParameter("teacherName");
            Teacher teacher = new Teacher();
            teacher.setName(teacherName);
            repository.saveItem(teacher);
            req.setAttribute("data", teacherName);
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
