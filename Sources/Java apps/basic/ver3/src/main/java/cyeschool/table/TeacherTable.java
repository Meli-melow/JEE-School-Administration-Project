package cyeschool.table;

import entities.Teacher;
import services.AdminService;

import java.util.List;

public class TeacherTable {
    public static String generateTable(){
        AdminService adminService = new AdminService();
        List<Teacher> teacherList=adminService.getAllTeachers();
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
