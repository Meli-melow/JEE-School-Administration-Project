<%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../template/form.css">
  </head>

  <body>
    <div id="container">
            <h1>Student login</h1>
            <form action="${pageContext.request.contextPath}/StudentLoginServlet">
                <p>Email: <input type="text" id="mail" name="mail" required></p>
                <p>Password: <input type="password" id="password" name="password" required></p>
                <p><input type="submit" value="Login" id="button"></p>
            </form>
            <form action="../index.jsp" method="get">
                <button type="submit" class="return">Exit</button>
            </form>
    </div>
  </body>
</html>
