package services;

import dao.AdminDao;
import dao.CourseDao;
import dao.StudentDao;
import dao.TeacherDao;
import entities.Admin;
import entities.Course;
import entities.Student;
import entities.Teacher;

import java.util.Date;
import java.util.List;

public class AdminService {

    public AdminService() {}

    public String createAdmin(Admin newAdmin) {
        return new AdminDao().daoAddAdmin(newAdmin);
    }

    public Admin adminLogin(String adminMail, String pswd) { return new AdminDao().daoAdminLogin(adminMail, pswd); }

    public Admin getAdminById(int adminId) {
        return new AdminDao().daoFetchAdminById(adminId);
    }

    public Admin getAdminByMail(String adminMail) {
        return new AdminDao().daoFetchAdminByEmail(adminMail);
    }

    public String patchAdminProfile(int adminId, String newFirstname, String newLastname, String newMail) {
        return new AdminDao().daoPatchAdminProfile(adminId, newFirstname, newLastname, newMail);
    }

    public String changeAdminPswd(int adminId, String newPswd) {
        return new AdminDao().daoChangeAdminPswd(adminId, newPswd);
    }

    public String deleteAdmin(int adminId) {
        return new AdminDao().daoDeleteAdminById(adminId);
    }

    public List<Admin> getAllAdmins() { return new AdminDao().daoGetAllAdmins(); }

    public List<Teacher> getAllTeachers() { return new TeacherDao().daoGetAllTeachers(); }

    public List<Teacher> getAllTeachers(String field) { return new TeacherDao().daoGetAllTeachers(field); }

    public List<Course> getAllCourses() { return new CourseDao().daoGetAllCourses(); }

    public List<Course> getAllCourses(Date day, String hour, String schoolYear, String field, String duration) {
        return new CourseDao().daoGetAllCourses(java.sql.Date.valueOf(String.valueOf(day)),
            hour, schoolYear, field, duration);
    }

    public List<Student> getAllStudents() { return new StudentDao().daoGetAllStudents(); }

    public List<Student> getAllStudents(String schoolYear) { return new StudentDao().daoGetAllStudents(schoolYear); }
}
