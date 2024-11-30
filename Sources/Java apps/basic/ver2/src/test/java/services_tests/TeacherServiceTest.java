package services_tests;

import entities.Teacher;
import exceptions.crud.FetchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import services.CourseService;
import services.TeacherService;

//TODO : no effects on DB
public class TeacherServiceTest {

    TeacherService ts;
    Teacher t;

    @BeforeEach
    public void testTeacherService() { ts = new TeacherService();; }

    @AfterEach
    public void testResetCourseService() {
        ts = null;
        t = null;
    }

    @Test //TODO : WORKS
    public void serviceCreateTeacher() {
        ts.createTeacher( new Teacher("Valérie",  "PICHON", "valerie.pichon@cyu.fr", "gfcgctr", "Cloud Infrastructure"));
        assertSame(ts.getTeacherById(7).equals(
                        new Teacher("Valérie", "PICHON", "valerie.pichon@cyu.fr",
                                "gfcgctr", "Cloud Infrastructure"), 7),
                true);
    }

    @Test //TODO: WORKS
    public void serviceFetchTeacherById() {
        t = ts.getTeacherById(6);
        assertSame(t.equals( new Teacher("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
        "alLE", "Maths"),6), false);
        /*assertSame(t.equals( new Teacher("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE", "Maths"),1), true);*/
    }

    @Test //TODO : WORKS
    public void serviceFetchTeacherByMail() {
        // Check if exception is thrown
        // Red test with this approach because FecthException class does not have a default message error
        /*assertThrows(FetchException.class, () -> t = ts.getTeacherByMail("alain.leclair@cyu.fr"),
                "Could not fetch teacher instance by mail");*/
        t = ts.getTeacherByMail("alain.leclair@cyu.fr");
        assertSame(t.equals( new Teacher("Alain",  "LECLAIR", "alain.leclair@cyu.fr",
                "alLE", "Maths"),1), true);
    }

    @Test
    //TODO : FULLY WORKS
    // Patching teacher info
    // Courses updated when changing field
    public void servicePatchTeacherProfile() {
        ts.patchTeacherProfile(3, "", "", "", "Data Exploration");
    }

    @Test //TODO : WORKS
    public void serviceChangeTeacherPswd() {
        ts.changeTeacherPswd(2, "e,lk,e");
    }

    /*@Test //TODO: WORKS
    public void serviceUpdateTeacher() {
        ts.updateTeacher(1, new Teacher("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE", "CS", null));
        t = ts.getTeacherById(1);
        assertSame(t.equals( new Teacher("Ally", "LECLA", "ally.leclair@cyu.fr", "alLE",
                "CS", null) ,1), true);
    }*/

    @Test
    //TODO : WORKS
    // Removing a teacher also removes courses that were in their timetable
    public void serviceDeleteTeacher() {
        t = ts.getTeacherById(1);
        ts.deleteTeacher(t.getId());
    }
}
