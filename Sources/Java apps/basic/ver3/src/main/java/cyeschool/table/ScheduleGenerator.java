package cyeschool.table;

import entities.Course;
import services.AdminService;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {

    public static String generateSchedule(){
        AdminService adminService = new AdminService();
        List<Course> courseList=adminService.getAllCourses();//Appelle le service pour obtenir tous les cours présents dans la base de données
        StringBuilder schedule = new StringBuilder();
        for(Course course : courseList){
            schedule.append("<tr><td>");
            schedule.append(course.getDay());
            schedule.append("</td><td>");
            schedule.append(course.getDuration());
            schedule.append("</td><td>");
            schedule.append(course.getField());
            schedule.append("</td><td>");
            schedule.append(course.getSchoolYear());
            schedule.append("</td><td>");
            schedule.append(course.getTeacher().getLastname());
            schedule.append("</td></tr>");
        }
        return schedule.toString();
    }
}
