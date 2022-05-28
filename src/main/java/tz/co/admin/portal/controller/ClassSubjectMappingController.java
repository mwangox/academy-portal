package tz.co.admin.portal.controller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tz.co.admin.portal.model.Class;
import tz.co.admin.portal.model.Student;
import tz.co.admin.portal.model.Subject;
import tz.co.admin.portal.utils.HibernateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "class-subject-controller", value = "/subject-assignment")
public class ClassSubjectMappingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = null;
        try {
            String classId = req.getParameter("assignedClass");
            Session session = HibernateUtils.createSession();
            Subject classSubject = session.getReference(Subject.class, Long.parseLong(req.getParameter("classSubject")));
            Class assignedClass = session.getReference(Class.class, Long.parseLong(classId));
            transaction = session.beginTransaction();
            assignedClass.getSubjects().add(classSubject);
            session.update(assignedClass);
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
