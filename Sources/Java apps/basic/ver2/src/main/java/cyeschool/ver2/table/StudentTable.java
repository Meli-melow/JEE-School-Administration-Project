package cyeschool.ver2.table;

import entities.Student;

import java.util.List;

public class StudentTable {
    private List<Student> studentList;
    public StudentTable(List<Student> studentList) {
        this.studentList = studentList;
    }
    public String generateTable(){
        StringBuilder table = new StringBuilder();
        for (Student student : studentList) {
            table.append("<tr><td>");
            table.append(student.getFirstname());
            table.append("</td><td>");
            table.append(student.getLastname());
            table.append("</td><td>");
            table.append(student.getMail());
            table.append("</td><td>");
            table.append(student.getBirth());
            table.append("</td><td>");
            table.append(student.getSchoolYear());
            table.append("</td><td>");
            table.append("<button>Check profile</button>");
            table.append("</td></tr>");
        }
        return table.toString();
    }
}
