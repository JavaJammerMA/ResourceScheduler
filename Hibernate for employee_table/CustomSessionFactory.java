
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomSessionFactory {

    private static org.hibernate.SessionFactory sessionFactory;

    static {
        sessionFactory = (SessionFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Resources.class)
                .addAnnotatedClass(Reservations.class)
                .buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            new CustomSessionFactory();
        }

        return sessionFactory;
    }

    public static void burnDownFactory() {
        sessionFactory.close();
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
