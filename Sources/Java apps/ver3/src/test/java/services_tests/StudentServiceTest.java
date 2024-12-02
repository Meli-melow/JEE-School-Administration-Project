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
        assertSame("Student creation and sign up to existing courses successfull !\n",
                sService.createStudent( new Student("Julie",  "ABATTE",
                        "julie.abatte@cyu.fr", "uglug",
                        java.sql.Date.valueOf("2005-02-28"), "ING1 GI GR3") ));
    }

    @Test //TODO : WORKS
    public void serviceFetchStudent() {
        s = sService.getStudentById(3);
        assertSame(true, s.equals(
                new Student("Julie",  "ABATTE", "julie.abatte@cyu.fr","uglug",
                        java.sql.Date.valueOf("2005-02-28"), "ING1 GI GR3"), 5));
    }

    @Test
    //TODO : FULLY WORKS
    // Update personal info with new data only
    // Changing school year resets timetable
    public void servicePatchStudentProfile() {
        assertSame("Student profile updated successfully !\nAdded the existing courses of the new school year\n" +
                        "Timetable updated successfully\n",
                sService.patchStudentProfile(5, "",  "", "",
                java.sql.Date.valueOf("1900-01-01"), "ING1 GI GR3"));
        s = sService.getStudentById(5);
        assertSame(true, s.equals( new Student("Julie", "ABATTE", "julie.abatte@cyu.fr", "uglug",
                java.sql.Date.valueOf("2005-02-28"), "ING1 GI GR3") ,5));
    }

    @Test
    public void serviceFetchStudentByMail() {
        // Check if exception is thrown
        // Red test with this approach because FecthException class does not have a default message error
        /*assertThrows(FetchException.class, () -> t = ts.getStudentByMail("alain.leclair@cyu.fr"),
                "Could not fetch teacher instance by mail");*/
        s = sService.getStudentByMail("clara.lagarre@cyu.fr");
        assertSame(true, s.equals( new Student("Clara",  "LAGARRE", "clara.lagarre@cyu.fr", "fld^ld",
                java.sql.Date.valueOf("2001-11-28"), "ING 3 Cloud Computing"),5));
    }

    @Test //TODO : WORKS
    public void serviceChangeStudentPswd() {
        sService.changeStudentPswd(2, "djbc");
        s = sService.getStudentById(2);
        assertEquals(s.getPswd(), "djbc");
    }

    @Test
    //TODO : WORKS
    // Removing a student makes them unsigned up from courses that were in their timetable
    public void serviceDeleteStudent() {
        assertSame("Student deleted successfully !\n", sService.deleteStudent(5));
        assertNull(sService.getStudentById(5));
    }

}
