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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../template/form.css">
</head>
<body>
    <div id="container">
        <div id="content">
            <p>New Teacher form</p>
            <form action="${pageContext.request.contextPath}/CreateTeacherServlet">
                <p>Firstname: <input type="text" id="firstname" name="firstname" required></p>
                <p>Lastname: <input type="text" id="lastname" name="lastname" required></p>
                <p>Field: <input type="text" id="field" name="field" required></p>
                <p>Mail: <input type="text" id="mail" name="mail" required></p>
                <p>Password: <input type="password" id="password" name="password" required></p>
                <p><input type="submit" value="Create new user" id="button"></p>
            </form>
        </div>
    </div>
</body>
</html>
