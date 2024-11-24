import entities.TeacherEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


// 22/11/24 : voir les modules et leur utilisation (compile ou runtime) ; Erreur type NoClassDefFound l.11
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory enf = Persistence.createEntityManagerFactory("default");
        EntityManager en = enf.createEntityManager();
        EntityTransaction entr = en.getTransaction();

        try {
            entr.begin();

            TeacherEntity t1 = new TeacherEntity();
            t1.setFirstname("Alain");
            t1.setLastname("LECLAIR");
            t1.setMail("alain.leclair@cyu.fr");
            t1.setPswd("alLE");
            t1.setField("CS");
            t1.setTimetable(null);

            en.persist(t1);
            entr.commit();
        } catch (RuntimeException e) {
            if (entr.isActive())
                entr.rollback();
        } finally {
            en.close();
            enf.close();
        }
    }
}
