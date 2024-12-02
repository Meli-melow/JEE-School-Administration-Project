<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Teacher" %>
<%@ page import="cyeschool.table.TeacherTable" %>
<%@ page import="cyeschool.table.AdminTable" %><%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Admins</title>
    <link rel="stylesheet" href="../../template/table.css">
</head>
<body>
<div class="container">
    <h1>List of Admins</h1>
    <div class="cadre-table-scroll">
        <table class="table-scroll">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Mail</th>
                <th>Profile</th>
            </tr>
            </thead>
            <tbody>
                <%= AdminTable.generateTable()%>
            </tbody>
        </table>
    </div>
    <form action="../objectforms/new_admin.jsp" method="get">
        <button type="submit">Add Admin</button>
    </form>
    <form action="../main_admin.jsp" method="get">
        <button type="submit" class="return">Exit</button>
    </form>

</div>
</body>
</html>
