package services_tests;

import entities.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CourseService;
import services.StudentService;
import services.TeacherService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    CourseService cs;

    TeacherService ts = new TeacherService();
    Course c;

    @BeforeEach
    public void testCourseService() { cs = new CourseService(); }

    @AfterEach
    public void testResetCourseService() {
        cs = null;
        c = null;
    }

    @Test //TODO : WORKS
    public void serviceCreateCourse() {
        cs.createCourse("Cloud Infrastructure", java.sql.Date.valueOf("2024-12-02"), "13h", "3h15", "ING 3 Cloud Computing",
                "Cloud 1", "valerie.pichon@cyu.fr");
        assertSame(cs.getCourse(7).equals(
                        new Course("Cloud Infrastructure", java.sql.Date.valueOf("2024-12-02"), "13h", "3h15", "ING 3 Cloud Computing",
                                "Cloud 1", ts.getTeacherByMail("alain.leclair@cyu.fr")), 2),
                true);
    }

   @Test //TODO : WORKS
   public void serviceFetchCourse() {
        c = cs.getCourse(2);
        assertSame(c.equals( new Course( "Modèles Probabilistes",  java.sql.Date.valueOf("2024-11-25"), "13h15", "3h00", "Maths",
                "ING1 GI Gr3", ts.getTeacherById(6) ),2), true);
   }

    @Test
    //TODO : FULL WORKS
    // Patching course information
    // Changing teacher and course field
    // Changing the course's school year unsigns up students
    public void servicePatchCourse() {
        cs.patchCourse(2, "", java.sql.Date.valueOf("1900-01-01"), "", "3h00", "ING1 GI Gr3", "Maths", "");
        c = cs.getCourse(2);
        assertSame(c.equals( new Course( "Modèles Probabilistes",  java.sql.Date.valueOf("2024-11-25"), "13h15", "3h00", "Maths",
                "ING1 GI Gr3", ts.getTeacherById(6) ),2), true);
    }

    /*@Test  //TODO : change constructor
    public void serviceUpdateCourse() {
        cs.updateCourse(1, new Course("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE", "CS", null));
        c = cs.getCourse(1);
        assertEquals(c.equals( new Course("Maths",  java.sql.Date.valueOf("2024-05-25"), "ally.leclair@cyu.fr",
                "alLE", "CS", null) ,1), true);
    }*/

    @Test
    //TODO : WORKS
    // Deleting a course removes it from its teacher's timetable and its students'
    public void serviceDeleteCourse() {
        cs.deleteCourse(2);
        assertNull(cs.getCourse(2));
    }
}
