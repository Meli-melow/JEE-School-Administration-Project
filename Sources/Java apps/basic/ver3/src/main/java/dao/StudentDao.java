package dao;

import entities.Course;
import entities.Student;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.sql.Date;
import java.util.List;

public class StudentDao {

    public String daoAddStudent(Student student) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            // Add the existing courses to the student timetable
            String hql = "SELECT c FROM Course c WHERE c.schoolYear = :school_year";
            TypedQuery<Course> query = em.createQuery(hql, Course.class);
            query.setParameter("school_year", student.getSchoolYear());
            try {
                List<Course> newTimetable = query.getResultList();
                if (!newTimetable.isEmpty()) {
                    student.setTimetable(newTimetable);
                    for (Course studentClass : student.getTimetable()) {
                        em.merge(studentClass);
                        studentClass.getStudents().add(student);
                        // If the course instances are not managed
                        /*if (!em.contains(studentClass))
                            return "Student creation failed. Could not sign up student to existing courses during student creation\n";*/
                    }
                }
            } catch (NoResultException nrex) {
                // There are no courses having the corresponding school year
                return "Student created successfully !\n";
            }
            em.persist(student);
            em.flush();
            em.getTransaction().commit();

        } catch (Throwable ex) {
            //TODO : catch for fields validation (ConstraintViolationException)
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Student creation and sign up to existing courses successfull !\n";
    }

    public Student daoStudentLogin(String studentMail, String pswd) {

        Student logedInStudent;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            String hql = "SELECT s FROM Student s WHERE s.mail = :student_mail and s.pswd = :pswd";
            em.getTransaction().begin();
            TypedQuery<Student> query = em.createQuery(hql, Student.class);
            query.setParameter("student_mail", studentMail);
            query.setParameter("pswd", pswd);
            try {
                logedInStudent = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Logging in failed, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return logedInStudent;
    }

    public Student daoFetchStudentById(int studentId) throws FetchException, ExceptionInInitializerError {

        Student student;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            student = em.find(Student.class, studentId);
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
            try {
                student = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Fetching failed, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return student;
    }

    //TODO : if no new birthdate, replace it with java.sql.Date.valueOf("1900-01-01") to avoid IllegalArgumentException
    public String daoPatchStudentProfile(int studentId, String newFirstname, String newLastname, String newMail, Date newBirth,
    String newProm) {

        String serviceMessage;
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

            serviceMessage = "Student profile updated successfully !\n";
            if (newProm != null && newProm != "" && !newProm.equals(student.getSchoolYear())) {
                // If changing student's school year, reset their timetable
                for (Course previousCourses : student.getTimetable()) {
                    // Make sure Hibernate resets students timetable, so they can sign up to courses again
                    em.merge(previousCourses);
                    previousCourses.getStudents().remove(student);
                    // If the courses instances are not managed
                    if (!em.contains(previousCourses))
                        return "Could not sign up student to existing courses \n";

                    serviceMessage += "Removed the previous classes from student timetable\n";
                }
                student.setSchoolYear(newProm);
                String hql = "SELECT c FROM Course c WHERE c.schoolYear = :school_year";
                TypedQuery<Course> query = em.createQuery(hql, Course.class);
                query.setParameter("school_year", student.getSchoolYear());
                try {
                    List<Course> newTimetable = query.getResultList();
                    student.setTimetable(newTimetable);
                    for (Course existingCourse: newTimetable) {
                        em.merge(existingCourse);
                        existingCourse.getStudents().add(student);
                        if (!em.contains(existingCourse))
                            return "Student update failed. Could not sign up student to existing courses during" +
                                    "student update\n";

                        serviceMessage += "Added the existing courses of the new school year\n";
                    }
                } catch (NoResultException nrex) {
                    // There are no courses having the new prom
                    return serviceMessage;
                }
                serviceMessage += "Timetable updated successfully\n";
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return serviceMessage;
    }

    public String daoChangeStudentPswd(int studentId, String newPswd) {
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
        return "Student password changed successfully !\n";
    }

    public String daoDeleteStudentById(int studentId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            List<Course> timetable = student.getTimetable();
            if (!timetable.isEmpty()) {
                for (Course course : timetable) {
                    // Make sure Hibernate retrieves courses from student timetable
                    em.merge(course);
                    course.getStudents().remove(student);
                    // If the courses are not managed
                    if (!em.contains(course))
                        return "Could not erase student timetable\n";
                }
            }
            em.remove(student);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Student deleted successfully !\n";
    }

    //TODO
    // Get results and assignements
    //  ENCRYPT PSWDS

    //TODO : now for admin actions

    public List<Student> daoGetAllStudents() {

        List<Student> studentsList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT s FROM Student s";
            TypedQuery<Student> query = em.createQuery(hql, Student.class);
            try {
                studentsList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return studentsList;
    }

    public List<Student> daoGetAllStudents(String schoolYear) {

        List<Student> studentsList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT s FROM Student s";
            // Filtering students by field
            if (schoolYear != null && !schoolYear.equals(""))
                hql += " WHERE s.schoolYear = :school_year";

            TypedQuery<Student> query = em.createQuery(hql, Student.class);
            query.setParameter("school_year", schoolYear);
            try {
                studentsList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return studentsList;
    }

}
