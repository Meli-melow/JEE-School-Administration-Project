package dao;

import entities.Course;
import entities.Student;
import entities.Teacher;
import exceptions.cascades.DeleteCascadeException;
import exceptions.cascades.UpdateCascadeException;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.List;

public class CourseDao {

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void daoAddCourse(Date courseSlot, String courseHour, String courseDuration, String courseSchoolYear, String courseUnit,
    String teacherMail)
    {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            try {
                Teacher teacher = new TeacherDao().daoFetchTeacherByEmail(teacherMail);
                // Attach again the teacher instance to the persistence context
                em.merge(teacher);
                Course course = new Course(teacher.getField(), courseSlot, courseHour, courseDuration, courseSchoolYear,
                courseUnit, teacher);
                teacher.getTimetable().add(course);
                course.setTeacher(teacher);
                em.persist(course);
                // Force Hibernate to synchronise changes
                em.flush();
            } catch (UpdateCascadeException dex) {
                em.getTransaction().rollback();
                throw new UpdateCascadeException("Could not propagate modifications correctly : " + dex);
            }
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
            /*if (course == null) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch course instance : " + fe.getMessage());
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return course;
    }

    //TODO : if no new slot, replace it with java.sql.Date.valueOf("1900-01-01") to avoid IllegalArgumentException
    public void daoPatchCourse(int courseId, String newCourseField, Date newCourseSlot, String newCourseHour,
    String newCourseDuration, String newCourseSchoolYear, String newCourseUnit, String newTeacherMail) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            // Update information related to the new teacher
            try {
                if (newTeacherMail != null && newTeacherMail != "") {
                    Teacher newTeacher = new TeacherDao().daoFetchTeacherByEmail(newTeacherMail);
                    // Make sure hibernate manages changes brought to both new and previous teacher instances
                    em.merge(newTeacher);
                    em.merge(course.getTeacher());
                    //TODO: Update teachers timetables with cascading
                    course.getTeacher().getTimetable().remove(course);
                    newTeacher.getTimetable().add(course);
                    course.setTeacher(newTeacher);
                    /*if (!newTeacher.getTimetable().isEmpty())
                        newTeacher.getTimetable().add(course);
                    
                    else {
                        for (Course teacherClass : newTeacher.getTimetable()){
                            if (teacherClass.equals(course))
                        }
                    }*/
                    // Change course field to the field of the new teacher
                    if (!course.getField().equals(newTeacher.getField()))
                        course.setField(newTeacher.getField());
                }
            } catch (UpdateCascadeException uex) {
                em.getTransaction().rollback();
                throw new UpdateCascadeException("Could not propagate modifications correctly. Could not change course teacher : " + uex);
            }
            //TODO : if no new slot, replace it with java.sql.Date.valueOf("1900-01-01") to avoid IllegalArgumentException
            //TODO : java.sql.Date.valueOf("1900-01-01") means keeping the current slot
            if (!newCourseSlot.equals(java.sql.Date.valueOf("1900-01-01")) && newCourseSlot != course.getSlot())
                course.setSlot(newCourseSlot);

            if (newCourseHour != null && newCourseHour != "" && !newCourseHour.equals(course.getHour()))
                course.setHour(newCourseHour);

            if (newCourseDuration != null && newCourseDuration != "" && !newCourseDuration.equals(course.getDuration()))
                course.setDuration(newCourseDuration);

            if (newCourseUnit != null && newCourseUnit != "" && !newCourseUnit.equals(course.getCourseUnit()))
                course.setCourseUnit(newCourseUnit);

            if (newCourseSchoolYear != null && newCourseSchoolYear != "" && !newCourseSchoolYear.equals(course.getSchoolYear())) {
                // TODO : Update students with cascading
                if (!course.getStudents().isEmpty()) {
                    // Remove students from the former prom
                    try {
                        for (Student formerStudent : course.getStudents()) {
                            //TODO : use unsign up method of student dao
                            // Make sure hibernate manages changes brought to students in the former prom
                            em.merge(formerStudent);
                            course.getStudents().remove(formerStudent);
                        }
                    } catch (UpdateCascadeException uex) {
                        em.getTransaction().rollback();
                        throw new UpdateCascadeException("Could not propagate modifications correctly. Could not unsign up wrong students to course : " + uex);
                    }
                    /*String hql1 = "SELECT s FROM Student s WHERE s.schoolYear = :school_year";
                    TypedQuery<Student> query1 = em.createQuery(hql1, Student.class);
                    query1.setParameter("school_year", course.getSchoolYear());
                    List<Student> wrongProm = query1.getResultList();
                    for (Student wrongStudent : wrongProm)
                        course.getStudents().remove(wrongStudent);

                    String hql2 = "SELECT s FROM Student s WHERE s.schoolYear = :school_year";
                    TypedQuery<Student> query2 = em.createQuery(hql2, Student.class);
                    query1.setParameter("school_year", newCourseSchoolYear);
                    List<Student> newProm = query2.getResultList();
                    for (Student newStudent : newProm)
                        newStudent.addCourse(course);*/
                }
                course.setSchoolYear(newCourseSchoolYear);
            }
        em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    //TODO: Patch version
    // The goal was to optimise the fetching of entities fields which are updated but this sort of process
    // should be done to servlets
    /*public void daoPatchCourse(int courseId, HashMap<String, Object> requestFields) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            try {
                Course course = em.find(Course.class, courseId);
                // Iterate data entries and update them dynamically with Java Reflection
                requestFields.forEach((dataName, newValue) -> {
                    try {
                        Field entityField = Course.class.getDeclaredField(dataName);
                        entityField.setAccessible(true);
                        entityField.set(course, newValue);
                        entityField.setAccessible(false);
                    } catch (NoSuchFieldException | IllegalArgumentException e) {
                        throw new RuntimeException("Error while updating a course instance, unvalid entity field found : " + dataName
                        + e);
                    }
                });
            } catch (UpdateCascadeException dex) {
                em.getTransaction().rollback();
                throw new UpdateCascadeException("Could not propagate modifications correctly : " + dex);
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }*/

    public void daoDeleteCourseById(int courseId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Course course = em.find(Course.class, courseId);
            try {
                Teacher teacher = course.getTeacher();
                // Make sure hibernate retrieves the course from its teacher's timetable
                em.merge(teacher);
                teacher.getTimetable().remove(course);
            } catch (UpdateCascadeException dex) {
                em.getTransaction().rollback();
                throw new DeleteCascadeException("Could not propagate deletion correctly. Could erase course from teacher timetable : " + dex);
            }
            em.remove(course);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }
}
