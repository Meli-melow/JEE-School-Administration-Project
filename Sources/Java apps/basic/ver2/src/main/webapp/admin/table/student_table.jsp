<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entities.Course" %>
<%@ page import="entities.Teacher" %>
<%@ page import="cyeschool.ver2.table.StudentTable" %>
<%@ page import="entities.Student" %><%--
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
                <th>Birthday</th>
                <th>School Year</th>
                <th>Profile</th>
            </tr>
            </thead>
            <tbody>
                <%! List<Student> l= new ArrayList<Student>();%>
                <% for(int i=0;i<20;i++){
                    Student s= new Student("Chika","Takami","mail@mail.com","mikan",new java.sql.Date(103,8,1),"ING2");
                    l.add(s);
                }%>
                <%! StudentTable table = new StudentTable(l);%>
                <%= table.generateTable()%>
            </tbody>
        </table>
    </div>
    <p><div class="left-button"></div>
    <div class="right-button"><a href="../objectforms/new_student.jsp">Add student</a></div>
    </p>
    <p><div class="right-button"><a href="../main_admin.jsp">Exit</a></div></p>
</div>
</body>
</html>
