package dao;

import entities.Course;
import entities.Course;
import exceptions.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;

public class CourseDao {

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void daoAddCourse(Course course) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public Course daoFetchCourseById(int courseId) throws FetchException, ExceptionInInitializerError {

        Course course;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            course = em.find(Course.class, courseId);
            /*if (course.equals(null)) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch course instance : " + fe.getMessage());
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return course;
    }

    //TODO: Patch version OR servlets send a code int value
    // to indicate the type of update
    public void daoUpdateCourseProfile(int courseId, Course updatedCourse) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            course.setField( updatedCourse.getField() );
            course.setSlot( updatedCourse.getSlot() );
            course.setDuration( updatedCourse.getDuration() );
            course.setCourseUnit( updatedCourse.getCourseUnit() );
            course.setStudents( updatedCourse.getStudents() );
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoDeleteCourseById(int courseId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            em.detach(course);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }
}
