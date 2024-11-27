package dao;

import entities.Teacher;
import exceptions.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
            /*if (teacher.equals(null)) {
                RuntimeException fe = new FetchException();
                throw new RuntimeException("Could not fetch teacher instance : " + fe.getMessage());
            }*/
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return teacher;
    }

    //TODO: Patch version
    public void daoUpdateTeacherProfile(int teacherId, Teacher updatedTeacher) {

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
    }

    public void daoDeleteTeacherById(int teacherId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, teacherId);
            em.detach(teacher);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
    }

}
