<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Teacher" %>
<%@ page import="cyeschool.table.TeacherTable" %><%--
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
                <th>First name</th>
                <th>Last name</th>
                <th>Mail</th>
                <th>Field</th>
                <th>Profile</th>
            </tr>
            </thead>
            <tbody>
                <%= TeacherTable.generateTable()%>
            </tbody>
        </table>
    </div>
    <p><div class="left-button"></div>
    <div class="right-button"><a href="../objectforms/new_teacher.jsp">Add teacher</a></div>
    </p>
    <p><div class="right-button"><a href="../main_admin.jsp">Exit</a></div></p>
</div>
</body>
</html>
