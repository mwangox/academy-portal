package tz.co.admin.portal.controller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tz.co.admin.portal.model.Class;
import tz.co.admin.portal.model.Subject;
import tz.co.admin.portal.model.Teacher;
import tz.co.admin.portal.utils.HibernateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "teacher-class-controller", value = "/teacher-assignment")
public class TeacherClassMappingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = null;
        try {
            Session session = HibernateUtils.createSession();
            Teacher teacher = session.getReference(Teacher.class, Long.parseLong(req.getParameter("assignedTeacher")));
            Subject classSubject = session.getReference(Subject.class, Long.parseLong(req.getParameter("classSubject")));
            Class assignedClass = session.getReference(Class.class, Long.parseLong(req.getParameter("assignedClass")));

            teacher.getClasses().add(assignedClass);
            teacher.getSubjects().add(classSubject);
            transaction = session.beginTransaction();
            session.update(teacher);
            transaction.commit();
            session.close();
            RequestDispatcher rd = req.getRequestDispatcher("assign-result.jsp");
            rd.forward(req, resp);
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            req.setAttribute("error", e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
            rd.forward(req, resp);
            e.printStackTrace();
        }
    }
}
