package services_tests;

import entities.Teacher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import services.TeacherService;

//TODO : no effects on DB
public class TeacherServiceTest {

    TeacherService ts = new TeacherService();
    Teacher t;

    @Test //TODO: WORKS
    public void serviceFetchTeacher() {
        t = ts.getTeacher(1);
        t.toString();
        assertSame(t.equals( new Teacher("Ally",  "LECLA", "ally.leclair@cyu.fr",
        "alLE", "CS", null),1), true);
    }

    /*@Test //TODO: WORKS
    public void serviceUpdateTeacher() {
        ts.updateTeacher(1, new Teacher("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE", "CS", null));
        t = ts.getTeacher(1);
        assertSame(t.equals( new Teacher("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE",
                "CS", null) ,1), true);
    }*/

    @Test //TODO : CASCADING
    public void serviceDeleteTeacher() {
        ts.deleteTeacher(5);
        assertNull(ts.getTeacher(5));
    }

}
