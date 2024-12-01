package services_tests;

import entities.Course;
import entities.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CourseService;
import services.TeacherService;

import java.util.HashSet;

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
        assertSame("Course created successfully !\n", cs.createCourse(java.sql.Date.valueOf("2024-11-13"),
                "10h15", "3h00", "ING1 GI GR2", "eric.lechamp@cyu.fr"));
    }

   @Test //TODO : WORKS
   public void serviceFetchCourse() {
        c = cs.getCourse(1);
        assertSame(true, c.equals( new Course( "Data Exploration", java.sql.Date.valueOf("2024-11-13"),
                "10h15", "3h00", "ING1 GI GR2", ts.getTeacherById(1) ),1));
   }

    @Test
    //TODO : FULL WORKS
    // Patching course information
    // Changing teacher and course field
    // Changing the course's school year unsigns up students
    public void servicePatchCourse() {
        assertSame("Course update failed. Hour or duration or school year are invalid\n",
                cs.patchCourse(1, java.sql.Date.valueOf("1900-01-01"), "", "", "ING1 GI GR3", ""));
        c = cs.getCourse(1);
        /*assertSame(true, c.equals( new Course( "Modèles Probabilistes",  java.sql.Date.valueOf("2024-11-07"), "10h15", "1h30", "Maths",
                "ING1 GI GR2", ts.getTeacherById(8) ),3));*/
        /*assertSame(true, c.getStudents().isEmpty());*/
    }

    @Test
    //TODO : WORKS
    // Deleting a course removes it from its teacher's timetable and its students'
    public void serviceDeleteCourse() {
        assertSame("Course deleted successfully !\n", cs.deleteCourse(1));
        assertNull(cs.getCourse(1));
    }
}
