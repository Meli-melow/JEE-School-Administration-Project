<%@ page import="classes.SchoolYear" %><%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Student</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/template/form.css">
</head>
<body>
    <div id="container">

            <h1>New Student form</h1>
            <form action="${pageContext.request.contextPath}/CreateStudentServlet" method="get">
                <p>Firstname: <input type="text" id="firstname" name="firstname" required></p>
                <p>Lastname: <input type="text" id="lastname" name="lastname" required></p>
                <p>Birthday: <input type="date" id="birth" name="birth"></p>
                <p>School Year: <select name="school_year" required>
                    <%-- On prend tous les valeurs possible de l'énum SchoolYear pour laisser seulement le choix des valeurs acceptées pour la création d'un cours--%>
                    <%! SchoolYear[] years = SchoolYear.values();%>
                    <%for(SchoolYear y : years){
                        out.print("<option value='"+y.getSchoolYearValue()+"'>"+y.getSchoolYearValue()+"</option>");
                    }%>
                </select></p>
                <p>Password: <input type="password" id="password" name="password" required></p>
                <p><input type="submit" value="Create new user" id="button"></p>
            </form>
            <form action="<%= request.getContextPath() %>/admin/table/student_table.jsp" method="get">
                <button type="submit" class="return">Exit</button>
            </form>
        </div>

</body>
</html>
