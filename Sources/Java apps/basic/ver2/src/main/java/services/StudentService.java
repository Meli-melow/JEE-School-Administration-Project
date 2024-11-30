package services;

import dao.StudentDao;
import entities.Course;
import entities.Student;

import java.sql.Date;
import java.util.List;

public class StudentService {

    public StudentService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public void createStudent(Student newStudent) {
        new StudentDao().daoAddStudent(newStudent);
    }

    public Student getStudentById(int studentId) {
        return new StudentDao().daoFetchStudentById(studentId);
    }

    public Student getStudentByMail(String studentMail) { return new StudentDao().daoFetchStudentByEmail(studentMail); }

    //TODO: Patch version
    public void patchStudentProfile(int studentId, String newFirstname, String newLastname, String newMail, Date newBirth,
    String newProm) {
        new StudentDao().daoPatchStudentProfile(studentId, newFirstname, newLastname, newMail, newBirth, newProm);
    }

    public void changeStudentPswd(int studentId, String newPswd) {
        new StudentDao().daoChangeStudentPswd(studentId, newPswd);
    }

    public void signUpToCourse(int studentId, int courseId) {
        new StudentDao().daoStudentSignUpToCourse(studentId, courseId);
    }

    public void unsignUpToCourse(int studentId, int courseId) {
        new StudentDao().daoUnSignUpToCourse(studentId, courseId);
    }

    //TODO : CASCADING
    public void deleteStudent(int studentId) {
        new StudentDao().daoDeleteStudentById(studentId);
    }

    public List<Course> showAvailableCourses(int studentId) {
        return new StudentDao().daoShowAvailableCourses(studentId);
    }

}
