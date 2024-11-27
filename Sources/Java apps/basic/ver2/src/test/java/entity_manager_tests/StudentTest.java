package entity_manager_tests;

import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class StudentTest {

    Student s1;

    @Test
    public void canPersistStudent() {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("default");
        EntityManager en = enf.createEntityManager();

        try {
            en.getTransaction().begin();

            s1 = new Student("Clara", "LAGARRE", "clara.lagarre@cyu.fr", "fld^ld",
            java.sql.Date.valueOf("2001-05-25"), "ING 3 Cloud Computing");

            en.persist(s1);
            System.out.println("Student created\n");
            en.getTransaction().commit();
        } finally {

            en.close();
            enf.close();
        }
    }
}
