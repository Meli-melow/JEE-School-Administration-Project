<%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Admin</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/template/form.css">
</head>
<body>
    <div id="container">
        <div id="content">
            <h1>New Admin form</h1>
            <form action="${pageContext.request.contextPath}/CreateAdminServlet">
                <p>Firstname: <input type="text" id="firstname" name="firstname" required></p>
                <p>Lastname: <input type="text" id="lastname" name="lastname" required></p>
                <p>Password: <input type="password" id="password" name="password" required></p>
                <p><input type="submit" value="Create new user" id="button"></p>
            </form>
            <form action="<%= request.getContextPath() %>/admin/table/admin_table.jsp" method="get">
                <button type="submit" class="return">Exit</button>
            </form>
        </div>
    </div>
</body>
</html>
