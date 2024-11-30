package services_tests;

import entities.Course;
import entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CourseService;
import services.StudentService;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    StudentService sService;

    CourseService cs;

    Student s;

    @BeforeEach
    public void testStudentService() {
        sService = new StudentService();
    }

    @Test //TODO : WORKS
    public void serviceCreateStudent() {
        sService.createStudent( new Student("Clara",  "LAGARRE", "clara.lagarre@cyu.fr",
                "fld^ld", java.sql.Date.valueOf("2001-11-28"), "ING1 GI Gr3") );
        assertSame(sService.getStudentById(5).equals(
                new Student("Clara",  "LAGARRE", "clara.lagarre@cyu.fr",
                "fld^ld", java.sql.Date.valueOf("2001-11-28"), "ING 3 Cloud Computing"), 5),
                true);
    }

    @Test //TODO : WORKS
    public void serviceFetchStudent() {
        s = sService.getStudentById(5);
        assertSame(s.toString().equals("Student instance\nid : 5; firstname : Clara; lastname : LAGARRE; mail : clara.lagarre@cyu.fr" +
                        ";\npassword : fld^ld; birthday : 2001-11-28; school year : ING 3 Cloud Computing\n"), true);
    }

    @Test
    //TODO : FULLY WORKS
    // Update personal info with new data only
    // Changing school year resets timetable
    public void servicePatchStudentProfile() {
        sService.patchStudentProfile(5, "",  "", "",
                java.sql.Date.valueOf("1900-01-01"), "ING 3 Cloud Computing");
        s = sService.getStudentById(5);
        assertSame(s.equals( new Student("Clara",  "LAGARRE", "clara.lagarre@cyu.fr", "fld^ld",
        java.sql.Date.valueOf("2001-11-28"), "ING 3 Cloud Computing") ,3),
        true);
    }

    @Test
    public void serviceFetchStudentByMail() {
        // Check if exception is thrown
        // Red test with this approach because FecthException class does not have a default message error
        /*assertThrows(FetchException.class, () -> t = ts.getStudentByMail("alain.leclair@cyu.fr"),
                "Could not fetch teacher instance by mail");*/
        s = sService.getStudentByMail("clara.lagarre@cyu.fr");
        assertSame(s.equals( new Student("Clara",  "LAGARRE", "clara.lagarre@cyu.fr", "fld^ld",
                java.sql.Date.valueOf("2001-11-28"), "ING 3 Cloud Computing"),5), true);
    }

    @Test //TODO : WORKS
    public void serviceChangeStudentPswd() {
        sService.changeStudentPswd(2, "djbc");
        s = sService.getStudentById(2);
        assertEquals(s.getPswd(), "djbc");
    }

    @Test //TODO : WORKS
    public void serviceStudentSignUpToCourse() {
        cs = new CourseService();
        s = sService.getStudentById(3);
        Course c = cs.getCourse(2);
        sService.signUpToCourse(s.getId(), c.getId());
    }

    @Test //TODO : WORKS
    public void serviceStudentUnsignUpToCourse() {
        cs = new CourseService();
        s = sService.getStudentById(2);
        Course c = cs.getCourse(2);
        sService.unsignUpToCourse(s.getId(), c.getId());
    }

    @Test
    //TODO : WORKS
    // Removing a student makes them unsigned up from courses that were in their timetanle
    public void serviceDeleteStudent() {
        sService.deleteStudent(2);
        assertNull(sService.getStudentById(2));
    }

    @Test
    public void serviceShowAvailableCourses() {
        sService.showAvailableCourses(3);
    }
}
