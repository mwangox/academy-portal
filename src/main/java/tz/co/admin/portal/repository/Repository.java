package tz.co.admin.portal.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tz.co.admin.portal.utils.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Repository {
    public void saveItem(Object item){
        Transaction transaction = null;
        try {
            Session session = HibernateUtils.createSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public  <T> List<T> loadAllData(Class<T> type) {
        Session session = HibernateUtils.createSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }

    public List<tz.co.admin.portal.model.Class> getClasses(){
        return loadAllData(tz.co.admin.portal.model.Class.class);
    }
}
