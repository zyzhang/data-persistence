package zhang.abel.datapersistence.hibernate.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zhang.abel.datapersistence.hibernate.domain.User;
import zhang.abel.datapersistence.hibernate.utils.HibernateManager;

public class UserRepository {
    public User load(long id) {
        Session session = HibernateManager.getSession();
        return (User) session.get(User.class, id);
    }

    public void persist(User user) {
        Session session = HibernateManager.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }
}
