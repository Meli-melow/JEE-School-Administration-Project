package dao;

import entities.Course;
import entities.Teacher;
import exceptions.cascades.DeleteCascadeException;
import exceptions.cascades.UpdateCascadeException;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class TeacherDao {

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void daoAddTeacher(Teacher teacher) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }

    }

    public Teacher daoFetchTeacherById(int teacherId) throws FetchException, ExceptionInInitializerError {

        Teacher teacher;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            teacher = em.find(Teacher.class, teacherId);
            if (teacher == null) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch teacher instance : " + fe.getMessage());
            }
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
            teacher = query.getSingleResult();
            if (teacher == null) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch teacher instance : " + fe.getMessage());
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teacher;
    }

    //TODO: Patch version
    /*public void daoUpdateTeacherProfile(int teacherId, Teacher updatedTeacher) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            teacher.setFirstname( updatedTeacher.getFirstname() );
            teacher.setLastname( updatedTeacher.getLastname() );
            teacher.setMail( updatedTeacher.getMail() );
            teacher.setPswd( updatedTeacher.getPswd() );
            teacher.setField( updatedTeacher.getField() );
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }*/

    public void daoPatchTeacherProfile(int teacherId, String newFirstname, String newLastname, String newMail, String newField) {
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

            if (newField != null && newField != "" && !newField.equals(teacher.getField())) {
                teacher.setField(newField);
                //TODO : update the field of all tearcher's classes
                try {
                    for (Course teacherClass : teacher.getTimetable()) {
                        // Make sure Hibernate manages courses of teacher timetable
                        em.merge(teacherClass);
                        //TODO : they should also unsign up students
                        teacherClass.setField(newField);
                    }
                } catch (UpdateCascadeException uex) {
                    em.getTransaction().rollback();
                    throw new UpdateCascadeException("Could not propagate modifications correctly : " + uex);
                }
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    public void daoChangeTeacherPswd(int teacherId, String newPswd) {
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
    }

    //TODO : before patch add, check if cascading does it in course patch
    //TODO : Code course delete first, removing a class from a teacher's timetable should erase the course
    public void daoPatchTeacherTimetableRemoveCourse(int teacherId, int courseId) {}

    public void daoDeleteTeacherById(int teacherId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            try {
            // Use teacher remove cascading on course entity
            // Erases all classes that had this teacher instance
            em.remove(teacher);
            } catch (DeleteCascadeException dex) {
                em.getTransaction().rollback();
                throw new DeleteCascadeException("Could not propagate modifications correctly. Could not remove teacher classes " + dex);
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

    //TODO
    // Select proms
    // Set grades to a prom
    // ENCRYPT PSWDS
}
