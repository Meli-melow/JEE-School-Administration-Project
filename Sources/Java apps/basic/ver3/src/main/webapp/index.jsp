<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="professor/style.css">
</head>
<body>
<div class="container">

        <h1>Login</h1>
        <p>Please select what type of user you are:</p>
        <form action="login/student_login.jsp">
            <button type="submit">Student</button>
        </form>
        <form action="login/teacher_login.jsp">
            <button type="submit">Teacher</button>
        </form>
        <form action="login/admin_login.jsp">
            <button type="submit">Admin</button>
        </form>
        <form action="admin/main_admin.jsp">
            <button type="submit">Admin pages</button>
        </form>

</div>
</body>
</html>