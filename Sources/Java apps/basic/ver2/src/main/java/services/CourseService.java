package services;

import dao.CourseDao;
import entities.Course;

public class CourseService {

    public CourseService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void createCourse(Course newCourse) {
        new CourseDao().daoAddCourse(newCourse);
    }

    public Course getCourse(int courseId) {
        return new CourseDao().daoFetchCourseById(courseId);
    }

    //TODO: Patch version
    public void updateCourse(int courseId, Course updatedCourse) {
        new CourseDao().daoUpdateCourseProfile(courseId, updatedCourse);
    }

    //TODO : CASCADING
    public void deleteCourse(int courseId) {
        new CourseDao().daoDeleteCourseById(courseId);
    }

}
