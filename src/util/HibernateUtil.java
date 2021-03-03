
// preuzeto sa predavanja
package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("/xml/hibernate.cfg.xml");
            StandardServiceRegistryBuilder srb
                    = new StandardServiceRegistryBuilder();
            srb.applySettings(config.getProperties());
            ServiceRegistry servis = srb.build();
            sessionFactory = config.buildSessionFactory(servis);
        }
        return sessionFactory;
    }
}
