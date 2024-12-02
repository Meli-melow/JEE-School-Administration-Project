package cyeschool;

import entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateApp {

    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("default");
        EntityManager en = enf.createEntityManager();

        try {
            en.getTransaction().begin();

            /*Teacher t1 = new Teacher();
            t1.setFirstname("Alain");
            t1.setLastname("LECLAIR");
            t1.setMail("alain.leclair@cyu.fr");
            t1.setPswd("alLE");
            t1.setField("CS");
            t1.setTimetable(null);

            Teacher t2 = new Teacher("Hadrien", "LECLAIR", "hadrien.leclayr@cyu.fr", "facksj", "CS", null);


            en.persist(t1);
            en.persist(t2);*/



            en.getTransaction().commit();
        } finally {
            if (en.getTransaction().isActive())
                en.getTransaction().rollback();

            en.close();
            enf.close();
        }
    }
}
