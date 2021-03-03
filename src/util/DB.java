package util;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import model.Racun;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DB {

    public static User registerUser(User u) {

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        try {
            int id = (int) sesija.save(u);
            u.setId(id);

            JOptionPane.showMessageDialog(null, "Korisnik je uspesno registrovan, dobio je ID: " + id);
            sesija.getTransaction().commit();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: Registracija nije uspela");
            JOptionPane.showMessageDialog(null, "Doslo je do greske, molimo vas da ponovo pokrenete aplikaciju");

            sesija.getTransaction().rollback();
            System.exit(1);
        }

        if (sesija.isOpen()) {
            sesija.close();
        }

        return u;

    }

    public static User loginUser(String username, String password) {

        User user;

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from User where username = :username and password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);

        List<User> users = query.list();

        if (users.isEmpty()) {
            user = null;
        } else {
            user = users.get(0);
        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }

        return user;
    }

    public static boolean checkUsername(String username) {

        boolean usernameExists;

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from User where username = :username");
        query.setParameter("username", username);

        List<User> users = query.list();

        if (users.isEmpty()) {
            usernameExists = false;

        } else {
            usernameExists = true;
        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }

        return usernameExists;
    }

    public static int getLastRacunId() {
        int id;

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Racun");
        List<Racun> racuni = query.list();

        if (racuni.isEmpty()) {
            id = 0;

        } else {
            Racun r = racuni.get(racuni.size() - 1);
            id = r.getId();
        }

        sesija.getTransaction().commit();
        if (sesija.isOpen()) {
            sesija.close();
        }

        return id;
    }

    public static void updateUser(User user) {

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        try {
            sesija.update(user);
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: Korisnik " + user.getUsername() + " ne postoji u bazi");

        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }
    }

    public static void updateRacun(Racun racun) {

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        try {
            sesija.update(racun);
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: Racun " + racun.getBrRacuna() + " ne postoji u bazi");

        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }
    }
    
    public static void deleteRacun(Racun racun) {
        
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        try {
            sesija.delete(racun);
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: Racun " + racun.getBrRacuna() + " ne postoji u bazi");

        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }
    }

    public static Racun getRacun(String brRacuna) {

        Racun racun;

        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Racun where brRacuna = :brRacuna");
        query.setParameter("brRacuna", brRacuna);

        List<Racun> racuni = query.list();

        if (racuni.isEmpty()) {
            racun = null;
        } else {
            racun = racuni.get(0);
        }

        sesija.getTransaction().commit();

        if (sesija.isOpen()) {
            sesija.close();
        }

        return racun;
    }
    
}
