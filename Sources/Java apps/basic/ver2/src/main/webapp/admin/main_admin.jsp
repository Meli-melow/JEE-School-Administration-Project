<%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 29/11/2024
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/professor/style.css">
</head>
<body>
  <div class="container">
      <h1>Main page - Admin</h1>
      <p>Welcome! Choose an action</p>
      <form action="<%= request.getContextPath() %>/admin/table/schedule.jsp">
        <button type="submit">List of courses</button>
      </form>
      <form action="<%= request.getContextPath() %>/admin/table/student_table.jsp">
        <button type="submit">List of students</button>
      </form>
      <form action="<%= request.getContextPath() %>/admin/table/teacher_table.jsp">
        <button type="submit">List of teachers</button>
      </form>
      <form action="<%= request.getContextPath()%>/admin/admin_profile.jsp">
        <button type="submit">Change profile</button>
      </form>
  </div>
</body>
</html>
