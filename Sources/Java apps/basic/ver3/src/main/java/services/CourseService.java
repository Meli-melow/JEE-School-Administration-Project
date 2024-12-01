package services;

import classes.SchoolYear;
import dao.CourseDao;
import entities.Course;

import java.util.Arrays;
import java.util.Date;

public class CourseService {

    public CourseService() {}

    //TODO : indication to servlet if field does not match the teacher's one
    public String createCourse(Date courseDay, String courseHour, String courseDuration, String courseSchoolYear,
    String teacherMail) {
        // Check if hour, duration and schoolYear entries are valid
        if ( Arrays.asList(new String[] {"8h30", "10h15", "12h00", "13h00", "14h45", "16h30", "18h15"}).contains(courseHour)
            && ( (courseDuration.equals("1h30") || courseDuration.equals("3h00")) )
            && Arrays.asList(new String[] {"ING1 GI GR1", "ING1 GI GR2", "ING1 GI GR3", "ING1 GI GR4", "ING2 GSI GR1", "ING2 GSI GR1",
                "ING2 GSI GR1", "ING3 Artificial Intelligence", "ING3 Business Intelligence", "ING3 Cloud Computing",
                "ING3 Cybersecurity", "ING3 Embedded Systems"}).contains(courseSchoolYear) )

            return new CourseDao().daoAddCourse(java.sql.Date.valueOf(String.valueOf(courseDay)), courseHour, courseDuration,
                    courseSchoolYear, teacherMail);

        return "Course creation failed. Hour or duration or school year are invalid\n";
    }

    public Course getCourse(int courseId) {
        return new CourseDao().daoFetchCourseById(courseId);
    }

    //TODO: Patch version
    public String patchCourse(int courseId, Date newCourseDay, String newCourseHour,
                            String newCourseDuration, String newCourseSchoolYear, String newTeacherMail) {
        // Check if hour, duration and schoolYear entries are valid
        // Empty fields are valid, no changes have to be brought
        if ( Arrays.asList(new String[] {"", "8h30", "10h15", "12h00", "13h00", "14h45", "16h30", "18h15"}).contains(newCourseHour)
                && ( (newCourseDuration.equals("") || newCourseDuration.equals("1h30") || newCourseDuration.equals("3h00")) )
                && Arrays.asList(new String[] {"", "ING1 GI GR1", "ING1 GI GR2", "ING1 GI GR3", "ING1 GI GR4", "ING2 GSI GR1", "ING2 GSI GR1",
                "ING2 GSI GR1", "ING3 Artificial Intelligence", "ING3 Business Intelligence", "ING3 Cloud Computing",
                "ING3 Cybersecurity", "ING3 Embedded Systems"}).contains(newCourseSchoolYear) )

            return new CourseDao().daoPatchCourse(courseId, java.sql.Date.valueOf(String.valueOf(newCourseDay)), newCourseHour,
                    newCourseDuration, newCourseSchoolYear, newTeacherMail);

        return "Course update failed. Hour or duration or school year are invalid\n";
    }

    /*public void updateCourse(int courseId, String newCourseField, Date newCourseDay, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {
        new CourseDao().daoPatchCourse(newCourseId, newCourseField, java.sql.Date.valueOf(String.valueOf(newCourseDay)), newCourseHour,
        newCourseDuration,  newCourseSchoolYear, newCourseUnit, newTeacherMail);
    }*/

    /*public void patchCourse(int courseId, String newCourseField, Date newCourseDay, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {}*/

    //TODO : CASCADING
    public String deleteCourse(int courseId) {
        return new CourseDao().daoDeleteCourseById(courseId);
    }

}
