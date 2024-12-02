<%@ page import="cyeschool.table.ScheduleGenerator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Course" %>
<%@ page import="entities.Teacher" %>
<%@ page import="services.TeacherService" %><%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Courses</title>
    <link rel="stylesheet" href="../../template/table.css">
</head>
<body>
<div class="container">
    <h1>List of Courses</h1>
    <div class="cadre-table-scroll">
        <table class="table-scroll">
            <thead>
            <tr>
                <th>Timeslot</th>
                <th>Duration</th>
                <th>Field</th>
                <th>School Year</th>
                <th>Teacher</th>
            </tr>
            </thead>
            <tbody>
                <%= ScheduleGenerator.generateSchedule()%>
            </tbody>
        </table>
    </div>

    <form action="../objectforms/new_course.jsp" method="get">
        <button type="submit">Add Course</button>
    </form>
    <form action="../main_admin.jsp" method="get">
        <button type="submit" class="return">Exit</button>
    </form>
</div>
</body>
</html>