package services;

import dao.CourseDao;
import entities.Course;

import java.util.Date;

public class CourseService {

    public CourseService() {}

    //TODO : indication to servlet if field does not match the teacher's one
    public void createCourse(String courseField, Date courseSlot, String courseHour, String courseDuration, String courseSchoolYear,
    String courseUnit, String teacherMail) {
        new CourseDao().daoAddCourse(java.sql.Date.valueOf(String.valueOf(courseSlot)), courseHour, courseDuration, courseSchoolYear,
                courseUnit, teacherMail);
    }

    public Course getCourse(int courseId) {
        return new CourseDao().daoFetchCourseById(courseId);
    }

    //TODO: Patch version
    public void patchCourse(int courseId, String newCourseField, Date newCourseSlot, String newCourseHour,
                            String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {
        new CourseDao().daoPatchCourse(courseId, newCourseField, java.sql.Date.valueOf(String.valueOf(newCourseSlot)), newCourseHour,
                newCourseDuration, newCourseSchoolYear, newCourseUnit, newTeacherMail);
    }

    /*public void updateCourse(int courseId, String newCourseField, Date newCourseSlot, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {
        new CourseDao().daoPatchCourse(newCourseId, newCourseField, java.sql.Date.valueOf(String.valueOf(newCourseSlot)), newCourseHour,
        newCourseDuration,  newCourseSchoolYear, newCourseUnit, newTeacherMail);
    }*/

    /*public void patchCourse(int courseId, String newCourseField, Date newCourseSlot, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {}*/

    //TODO : CASCADING
    public void deleteCourse(int courseId) {
        new CourseDao().daoDeleteCourseById(courseId);
    }

}
