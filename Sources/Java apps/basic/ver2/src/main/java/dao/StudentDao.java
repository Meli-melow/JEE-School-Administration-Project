package dao;

import entities.Course;
import entities.Student;
import exceptions.cascades.DeleteCascadeException;
import exceptions.cascades.UpdateCascadeException;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.sql.Date;
import java.util.List;

public class StudentDao {

    public void daoAddStudent(Student student) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }

    }

    public Student daoFetchStudentById(int studentId) throws FetchException, ExceptionInInitializerError {

        Student student;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            student = em.find(Student.class, studentId);
            /*if (student.equals(null)) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch student instance : " + fe.getMessage());
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return student;
    }

    public Student daoFetchStudentByEmail(String studentMail) throws FetchException, ExceptionInInitializerError {

        Student student;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            // Typed query to fetch a student instance by mail
            String hql = "SELECT s FROM Student s WHERE s.mail = :student_mail";
            em.getTransaction().begin();
            TypedQuery<Student> query = em.createQuery(hql, Student.class);
            query.setParameter("student_mail", studentMail);
            student = query.getSingleResult();
            /*if (student == null) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch student instance : " + fe.getMessage());
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return student;
    }

    //TODO : if no new birthdate, replace it with java.sql.Date.valueOf("1900-01-01") to avoid IllegalArgumentException
    public void daoPatchStudentProfile(int studentId, String newFirstname, String newLastname, String newMail, Date newBirth,
    String newProm) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (newFirstname != null && newFirstname != "" && !newFirstname.equals(student.getFirstname()))
                student.setFirstname(newFirstname);

            if (newLastname != null && newLastname != "" && !newLastname.equals(student.getLastname()))
                student.setLastname(newLastname);

            if (newMail != null && newMail != "" && !newMail.equals(student.getMail()))
                student.setMail(newMail);

            if (!newBirth.equals(java.sql.Date.valueOf("1900-01-01")) && !newBirth.equals(student.getBirth()))
                student.setBirth(newBirth);

            if (newProm != null && newProm != "" && !newProm.equals(student.getSchoolYear())) {
                try {
                    // If changing student's school year, reset their timetable
                    for (Course previousCourses : student.getTimetable()) {
                        // Make sure Hibernate resets students timetable, so they can sign up to courses again
                        em.merge(previousCourses);
                        previousCourses.getStudents().remove(student);
                    }
                    student.setSchoolYear(newProm);
                } catch (UpdateCascadeException uex) {
                    em.getTransaction().rollback();
                    throw new UpdateCascadeException("Could not propagate modifications correctly. Could not reset student timetable : " + uex);
                }
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoChangeStudentPswd(int studentId, String newPswd) {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            // Servlet will verify password criteria
            student.setPswd(newPswd);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoStudentSignUpToCourse(int studentId, int courseId) {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            Course course = em.find(Course.class, courseId);
            student.getTimetable().add(course);
            course.getStudents().add(student);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoUnSignUpToCourse(int studentId, int courseId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            Course course = em.find(Course.class, courseId);
            try {
                student.getTimetable().remove(course);
                course.getStudents().remove(student);
            } catch (DeleteCascadeException dex) {
                em.getTransaction().rollback();
                throw new DeleteCascadeException("Could not propagate deletion correctly. Could not unsign up student to course : " + dex);
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoDeleteStudentById(int studentId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            try {
                List<Course> timetable = student.getTimetable();
                for (Course course : timetable) {
                    // Make sure Hibernate retrieves courses from student timetable
                    em.merge(course);
                    course.getStudents().remove(student);
                }
            } catch (DeleteCascadeException dex) {
                em.getTransaction().rollback();
                throw new DeleteCascadeException("Could not propagate deletion correctly. Could not unsign up student from courses : " + dex);
            }
            em.remove(student);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    //TODO : test with 2 student in same academic level lower than ING3
    public List<Course> daoShowAvailableCourses(int studentId) {

        List<Course> availableCourse;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            // Fetch courses matching student's academic level
            // ING3 Students have unique course units
            if (student.getSchoolYear().startsWith("ING 3", 0)) {
                String hql = "SELECT c FROM Course c WHERE c.schoolYear = :school_year";
                TypedQuery<Course> query = em.createQuery(hql, Course.class);
                query.setParameter("school_year", student.getSchoolYear());
                availableCourse = query.getResultList();
            } else {
                // Lower academic levels are divided into groups which all have the same course units
                String levelPattern = student.getSchoolYear().substring(0, student.getSchoolYear().length() - 4) + "%";
                String hql = "SELECT c FROM Course c WHERE c.schoolYear LIKE :pattern";
                TypedQuery<Course> query = em.createQuery(hql, Course.class);
                query.setParameter("pattern", levelPattern);
                availableCourse = query.getResultList();
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return availableCourse;
    }

    //TODO
    // USE ENUMERATIONS
    // Get results and assignements
    // Filtering available courses
    //  ENCRYPT PSWDS

}
