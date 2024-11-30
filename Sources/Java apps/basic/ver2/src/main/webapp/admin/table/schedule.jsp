<%@ page import="cyeschool.ver2.table.ScheduleGenerator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Course" %>
<%@ page import="entities.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../template/table.css">
</head>
<body>
<div id="container">
    <div class="cadre-table-scroll">
        <table class="table-scroll">
            <thead>
            <tr>
                <th>Timeslot</th>
                <th>Duration</th>
                <th>Field</th>
                <th>Course Unit</th>
                <th>School Year</th>
                <th>Teacher</th>
            </tr>
            </thead>
            <tbody>
                <%! List<Course> l= new ArrayList<Course>();%>
                <% for(int i=0;i<20;i++){
                    Course c=new Course("Computer",new java.sql.Date(124,11,29),"12h00","3h","GI","ING3",new Teacher("","PK","","",""));
                    l.add(c);
                }%>
                <%! ScheduleGenerator schedule = new ScheduleGenerator(l);%>
                <%= schedule.generateSchedule()%>
            </tbody>
        </table>
    </div>
    <p><div class="left-button"></div>
    <div class="right-button"><a href="../objectforms/new_course.jsp">Add course</a></div>
    </p>
    <p><div class="right-button"><a href="../main_admin.jsp">Exit</a></div></p>
</div>
</body>
</html>
