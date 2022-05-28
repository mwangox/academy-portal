package tz.co.admin.portal.controller;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tz.co.admin.portal.model.Class;
import tz.co.admin.portal.model.Student;
import tz.co.admin.portal.model.Teacher;
import tz.co.admin.portal.repository.Repository;
import tz.co.admin.portal.utils.HibernateUtils;

import javax.rmi.CORBA.StubDelegate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "student-controller", value = "/student")
public class RegisterStudentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = null;
        try {
            String studentName = req.getParameter("studentName");
            Student student = new Student();
            student.setStudentName(studentName);
            Session session = HibernateUtils.createSession();
            Class studentClass = session.getReference(Class.class, Long.parseLong(req.getParameter("studentClass")));
            transaction = session.beginTransaction();
            student.setStudentClass(studentClass);
            session.persist(student);
            transaction.commit();
            session.close();
            req.setAttribute("data", studentName);
            RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
            rd.forward(req, resp);
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
