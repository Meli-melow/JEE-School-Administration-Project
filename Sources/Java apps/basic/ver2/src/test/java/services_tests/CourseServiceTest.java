package services_tests;

import entities.Course;
import org.junit.jupiter.api.Test;
import services.CourseService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseServiceTest {

    CourseService cs = new CourseService();
    Course c;

   /*@Test //TODO : change constructor
    public void serviceFetchCourse() {
        c = cs.getCourse(1);
        assertSame(c.equals( new Course("Maths",  java.sql.Date.valueOf("2024-11-25"), "ally.leclair@cyu.fr",
                "alLE", "CS", null),1), true);
    }*/

    /*@Test  //TODO : change constructor
    public void serviceUpdateCourse() {
        cs.updateCourse(1, new Course("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE", "CS", null));
        c = cs.getCourse(1);
        assertEquals(c.equals( new Course("Maths",  java.sql.Date.valueOf("2024-05-25"), "ally.leclair@cyu.fr",
                "alLE", "CS", null) ,1), true);
    }*/

    /*@Test //TODO : CASCADING
    public void serviceDeleteCourse() {
        cs.deleteCourse(5);
        assertNull(cs.getCourse(5));
    }*/
}
