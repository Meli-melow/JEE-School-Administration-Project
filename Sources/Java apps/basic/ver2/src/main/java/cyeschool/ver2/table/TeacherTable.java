package cyeschool.ver2.table;

import entities.Teacher;

import java.util.List;

public class TeacherTable {
    private List<Teacher> teacherList;
    public TeacherTable(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
    public String generateTable(){
        StringBuilder table = new StringBuilder();
        for (Teacher teacher : teacherList) {
            table.append("<tr><td>");
            table.append(teacher.getFirstname());
            table.append("</td><td>");
            table.append(teacher.getLastname());
            table.append("</td><td>");
            table.append(teacher.getMail());
            table.append("</td><td>");
            table.append(teacher.getField());
            table.append("</td><td>");
            table.append("<button>Check profile</button>");
            table.append("</td></tr>");
        }
        return table.toString();
    }
}
