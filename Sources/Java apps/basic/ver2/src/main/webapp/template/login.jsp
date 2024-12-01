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
    <link rel="stylesheet" type="text/css" href="form.css">
  </head>

  <body>
    <div id="container">
        <div id="content">
            <p>Student login</p>
            <form>
                <p>Email: <input type="text" id="mail" name="mail" required></p>
                <p>Password: <input type="password" id="password" name="password" required></p>
                <p><input type="submit" value="Login" id="button"></p>
            </form>
        </div>
    </div>
  </body>
</html>
