package dao;

import entities.Admin;
import exceptions.crud.FetchException;
import hibernateSetUp.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AdminDao {

    public String daoAddAdmin(Admin admin) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Admin created successfully !\n";
    }

    public Admin daoAdminLogin(String adminMail, String pswd) {

        Admin logedInAdmin;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            String hql = "SELECT a FROM Admin a WHERE a.mail = :admin_mail and a.pswd = :pswd";
            em.getTransaction().begin();
            TypedQuery<Admin> query = em.createQuery(hql, Admin.class);
            query.setParameter("admin_mail", adminMail);
            query.setParameter("pswd", pswd);
            try {
                logedInAdmin = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Logging in failed, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return logedInAdmin;
    }

    public Admin daoFetchAdminById(int adminId) throws FetchException, ExceptionInInitializerError {

        Admin admin;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            admin = em.find(Admin.class, adminId);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return admin;
    }

    public Admin daoFetchAdminByEmail(String adminMail) throws FetchException, ExceptionInInitializerError {

        Admin admin;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            // Typed query to fetch a admin instance by mail
            String hql = "SELECT a FROM Admin a WHERE a.mail = :admin_mail";
            em.getTransaction().begin();
            TypedQuery<Admin> query = em.createQuery(hql, Admin.class);
            query.setParameter("admin_mail", adminMail);
            try {
                admin = query.getSingleResult();
            } catch (NoResultException nrex) {
                // Fetching failed, return an empty instance to the user
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return admin;
    }

    public String daoPatchAdminProfile(int adminId, String newFirstname, String newLastname, String newMail) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Admin admin = em.find(Admin.class, adminId);
            if (newFirstname != null && newFirstname != "" && !newFirstname.equals(admin.getFirstname()))
                admin.setFirstname(newFirstname);

            if (newLastname != null && newLastname != "" && !newLastname.equals(admin.getLastname()))
                admin.setLastname(newLastname);

            if (newMail != null && newMail != "" && !newMail.equals(admin.getMail()))
                admin.setMail(newMail);

            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Admin profile updated successfully !\n";
    }

    public String daoChangeAdminPswd(int adminId, String newPswd) {
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Admin admin = em.find(Admin.class, adminId);
            // Servlet will verify password criteria
            admin.setPswd(newPswd);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Admin password changed successfully !\n";
    }

    public String daoDeleteAdminById(int adminId) {

        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            Admin admin = em.find(Admin.class, adminId);
            em.remove(admin);
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return "Admin deleted successfully !\n";
    }

    public List<Admin> daoGetAllAdmins() {

        List<Admin> adminsList;
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            em.getTransaction().begin();
            String hql = "SELECT a FROM Admin a";
            TypedQuery<Admin> query = em.createQuery(hql, Admin.class);
            try {
                adminsList = query.getResultList();
            } catch (NoResultException nrex) {
                // No results found, return an empty list to the admin
                return null;
            }
            em.getTransaction().commit();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("EntityManagerFactory initialisation failed : " + ex);
        }
        return adminsList;
    }
}
