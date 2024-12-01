package services;

import dao.StudentDao;
import entities.Course;
import entities.Student;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class StudentService {

    public StudentService() {}

    //TODO : indication to servlet if creation succeeded (servlet will dispatch user data on jsp where needed)
    public String createStudent(Student newStudent) {
        // Check if school year entry is valid
        if (Arrays.asList(new String[] {"ING1 GI GR1", "ING1 GI GR2", "ING1 GI GR3", "ING1 GI GR4", "ING2 GSI GR1", "ING2 GSI GR1",
                "ING2 GSI GR1", "ING3 Artificial Intelligence", "ING3 Business Intelligence", "ING3 Cloud Computing",
                "ING3 Cybersecurity", "ING3 Embedded Systems"}).contains(newStudent.getSchoolYear()))
            return new StudentDao().daoAddStudent(newStudent);

        return "Student creation failed. Please make sure school year is valid\n";
    }

    public Student studentLogin(String studentMail, String pswd) { return new StudentDao().daoStudentLogin(studentMail, pswd); }

    public Student getStudentById(int studentId) {
        return new StudentDao().daoFetchStudentById(studentId);
    }

    public Student getStudentByMail(String studentMail) { return new StudentDao().daoFetchStudentByEmail(studentMail); }

    //TODO: Patch version
    public String patchStudentProfile(int studentId, String newFirstname, String newLastname, String newMail, Date newBirth,
    String newProm) {
        return new StudentDao().daoPatchStudentProfile(studentId, newFirstname, newLastname, newMail, newBirth, newProm);
    }

    public String changeStudentPswd(int studentId, String newPswd) {
        return new StudentDao().daoChangeStudentPswd(studentId, newPswd);
    }

    /*public void signUpToCourse(int studentId, int courseId) {
        new StudentDao().daoStudentSignUpToCourse(studentId, courseId);
    }

    public void unsignUpToCourse(int studentId, int courseId) {
        new StudentDao().daoUnSignUpToCourse(studentId, courseId);
    }*/

    //TODO : CASCADING
    public String deleteStudent(int studentId) {
        return new StudentDao().daoDeleteStudentById(studentId);
    }

    /*public List<Course> showAvailableCourses(int studentId) {
        return new StudentDao().daoShowAvailableCourses(studentId);
    }*/
}
