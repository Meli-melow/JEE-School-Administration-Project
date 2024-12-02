package cyeschool.table;

import entities.Course;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {
    private List<Course> courseList;

    public ScheduleGenerator(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String generateSchedule(){
        StringBuilder schedule = new StringBuilder();
        for(Course course : courseList){
            schedule.append("<tr><td>");
            schedule.append(course.getDay());
            schedule.append("</td><td>");
            schedule.append(course.getDuration());
            schedule.append("</td><td>");
            schedule.append(course.getField());
            schedule.append("</td><td>");
            schedule.append(course.getStudents());
            schedule.append("</td><td>");
            schedule.append(course.getTeacher().getLastname());
            schedule.append("</td></tr>");
        }
        return schedule.toString();
    }
    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<>();

    }
}
