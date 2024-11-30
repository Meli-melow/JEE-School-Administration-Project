package services;

import dao.TeacherDao;
import entities.Teacher;

public class TeacherService {

    public TeacherService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void createTeacher(Teacher newTeacher) {
        new TeacherDao().daoAddTeacher(newTeacher);
    }

    public Teacher getTeacherById(int teacherId) {
        return new TeacherDao().daoFetchTeacherById(teacherId);
    }

    public Teacher getTeacherByMail(String teacherMail) {
        return new TeacherDao().daoFetchTeacherByEmail(teacherMail);
    }

    //TODO: Patch version
    /*public void updateTeacher(int teacherId, Teacher updatedTeacher) {
        new TeacherDao().daoUpdateTeacherProfile(teacherId, updatedTeacher);
    }*/

    public void patchTeacherProfile(int teacherId, String newFirstname, String newLastname, String newMail, String newField) {
        new TeacherDao().daoPatchTeacherProfile(teacherId, newFirstname, newLastname, newMail, newField);
    }

    public void changeTeacherPswd(int teacherId, String newPswd) {
        new TeacherDao().daoChangeTeacherPswd(teacherId, newPswd);
    }

    //TODO : CASCADING
    public void deleteTeacher(int teacherId) {
        new TeacherDao().daoDeleteTeacherById(teacherId);
    }
}
