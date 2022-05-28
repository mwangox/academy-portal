package tz.co.admin.portal.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public  static Session createSession(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(tz.co.admin.portal.model.Student.class);
        configuration.addAnnotatedClass(tz.co.admin.portal.model.Subject.class);
        configuration.addAnnotatedClass(tz.co.admin.portal.model.Class.class);
        configuration.addAnnotatedClass(tz.co.admin.portal.model.Teacher.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory.openSession();
    }
}
