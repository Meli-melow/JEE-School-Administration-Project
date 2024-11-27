package services;

import dao.TeacherDao;
import entities.Teacher;

public class TeacherService {

    public TeacherService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void createTeacher(Teacher newTeacher) {
        new TeacherDao().daoAddTeacher(newTeacher);
    }

    public Teacher getTeacher(int teacherId) {
        return new TeacherDao().daoFetchTeacherById(teacherId);
    }

    //TODO: Patch version
    public void updateTeacher(int teacherId, Teacher updatedTeacher) {
        new TeacherDao().daoUpdateTeacherProfile(teacherId, updatedTeacher);
    }

    //TODO : CASCADING
    public void deleteTeacher(int teacherId) {
        new TeacherDao().daoDeleteTeacherById(teacherId);
    }
}
