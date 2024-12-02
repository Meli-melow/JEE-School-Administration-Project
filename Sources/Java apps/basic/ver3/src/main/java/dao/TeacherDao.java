package dao;

import entities.Course;
import entities.Teacher;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TeacherDao {

    public String daoAddTeacher(Teacher teacher) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Teacher created successfully !\n";
    }

    public Teacher daoTeacherLogin(String teacherMail, String pswd) {

        Teacher logedInTeacher;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            String hql = "SELECT t FROM Teacher t WHERE t.mail = :teacher_mail and t.pswd = :pswd";
            em.getTransaction().begin();
            TypedQuery<Teacher> query = em.createQuery(hql, Teacher.class);
            query.setParameter("teacher_mail", teacherMail);
            query.setParameter("pswd", pswd);
            try {
                logedInTeacher = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Logging in, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return logedInTeacher;
    }

    public Teacher daoFetchTeacherById(int teacherId) throws FetchException, ExceptionInInitializerError {

        Teacher teacher;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            teacher = em.find(Teacher.class, teacherId);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teacher;
    }

    public Teacher daoFetchTeacherByEmail(String teacherMail) throws FetchException, ExceptionInInitializerError {

        Teacher teacher;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            // Typed query to fetch a teacher instance by mail
            String hql = "SELECT t FROM Teacher t WHERE t.mail = :teacher_mail";
            em.getTransaction().begin();
            TypedQuery<Teacher> query = em.createQuery(hql, Teacher.class);
            query.setParameter("teacher_mail", teacherMail);
            try {
                teacher = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Fetching failed, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teacher;
    }

    public String daoPatchTeacherProfile(int teacherId, String newFirstname, String newLastname, String newMail, String newField) {

        String serviceMessage;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            if (newFirstname != null && newFirstname != "" && !newFirstname.equals(teacher.getFirstname()))
                teacher.setFirstname(newFirstname);

            if (newLastname != null && newLastname != "" && !newLastname.equals(teacher.getLastname()))
                teacher.setLastname(newLastname);

            if (newMail != null && newMail != "" && !newMail.equals(teacher.getMail()))
                teacher.setMail(newMail);

            serviceMessage = "Teacher profile updated successfully !\n";
            if (newField != null && newField != "" && !newField.equals(teacher.getField())) {
                // Erase teacher's classes
                for (Course teacherClass : teacher.getTimetable()) {
                    // Make sure Hibernate manages courses of teacher timetable
                    em.merge(teacherClass);
                    // Remove the courses since they no longer have a teacher
                    em.remove(teacherClass);
                    // If the course instance is not managed
                    if (!em.contains(teacherClass))
                        return "Teacher update failed. Could not erase timetable during teacher update\n";
                }
                teacher.setField(newField);
                serviceMessage += "Teacher timetable successfully reset\n";
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return serviceMessage;
    }

    public String daoChangeTeacherPswd(int teacherId, String newPswd) {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            // Servlet will verify password criteria
            teacher.setPswd(newPswd);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Teacher password changed successfully !\n";
    }

    public String daoDeleteTeacherById(int teacherId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            /*try {*/
            // Use teacher remove cascading on course entity
            // Erases all classes that had this teacher instance
            em.remove(teacher);
            /*} catch (DeleteCascadeException dex) {
                em.getTransaction().rollback();
                return "Teacher deletion failed. Could not remove teacher's classes\n";
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Teacher deleted successfully !\n";
    }



    public List<Teacher> daoGetAllTeachers() {

        List<Teacher> teachersList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT t FROM Teacher t";
            TypedQuery<Teacher> query = em.createQuery(hql, Teacher.class);
            try {
                teachersList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teachersList;
    }

    public List<Teacher> daoGetAllTeachers(String schoolYear) {

        List<Teacher> teachersList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT t FROM Teacher t";
            // Filtering teachers by field
            if (schoolYear != null && !schoolYear.equals(""))
                hql += " WHERE t.field = :field_name";

            TypedQuery<Teacher> query = em.createQuery(hql, Teacher.class);
            query.setParameter("field_name", schoolYear);
            try {
                teachersList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teachersList;
    }
}
