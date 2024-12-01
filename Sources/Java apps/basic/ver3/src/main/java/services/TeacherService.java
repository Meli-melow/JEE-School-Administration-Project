package services;

import dao.TeacherDao;
import entities.Teacher;

public class TeacherService {

    public TeacherService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public String createTeacher(Teacher newTeacher) {
        return new TeacherDao().daoAddTeacher(newTeacher);
    }

    public Teacher teacherLogin(String teacherMail, String pswd) { return new TeacherDao().daoTeacherLogin(teacherMail, pswd); }

    public Teacher getTeacherById(int teacherId) {
        return new TeacherDao().daoFetchTeacherById(teacherId);
    }

    public Teacher getTeacherByMail(String teacherMail) {
        return new TeacherDao().daoFetchTeacherByEmail(teacherMail);
    }

    public String patchTeacherProfile(int teacherId, String newFirstname, String newLastname,
    String newMail, String newField) {
        return new TeacherDao().daoPatchTeacherProfile(teacherId, newFirstname, newLastname,
                newMail, newField);
    }

    public String changeTeacherPswd(int teacherId, String newPswd) {
        return new TeacherDao().daoChangeTeacherPswd(teacherId, newPswd);
    }

    public String deleteTeacher(int teacherId) {
        return new TeacherDao().daoDeleteTeacherById(teacherId);
    }
}
