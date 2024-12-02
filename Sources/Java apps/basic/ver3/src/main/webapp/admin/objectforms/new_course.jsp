<%@ page import="classes.Hour" %>
<%@ page import="classes.Duration" %>
<%@ page import="classes.SchoolYear" %>
<%@ page import="entities.Teacher" %>
<%@ page import="services.AdminService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Course</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/template/form.css">
</head>
<body>
    <div id="container">
            <h1>New course</h1>
            <form action="${pageContext.request.contextPath}/CreateCourseServlet">
                <p>Date: <input type="date" id="slot" name="slot" required></p>
                <p>Start of the course:<select name="hour" required>
                        <%-- On prend tous les valeurs possible de l'énum Hour pour laisser seulement le choix des valeurs acceptées pour la création d'un cours--%>
                        <%! Hour[] hours = Hour.values();%>
                        <%for(Hour h : hours){
                            out.print("<option value='"+h.getHourValue()+"'>"+h.getHourValue()+"</option>");
                        }%>
                    </select>
                </p>
                <p>
                    Duration: <select name="duration" required>
                        <%! Duration[] durations = Duration.values();%>
                        <%for(Duration d : durations){
                            out.print("<option value='"+d.getDurationValue()+"'>"+d.getDurationValue()+"</option>");
                        }%>
                    </select>
                </p>
                <p>
                    School Year: <select name="school_year" required>
                        <%! SchoolYear[] years = SchoolYear.values();%>
                        <%for(SchoolYear y : years){
                            out.print("<option value='"+y.getSchoolYearValue()+"'>"+y.getSchoolYearValue()+"</option>");
                        }%>
                    </select>
                </p>
                <p>Teacher:<select name="teacher" required>
                    <% AdminService service = new AdminService();
                        List<Teacher> teachers = service.getAllTeachers();
                        for(Teacher t : teachers) {
                            out.print("<option value='"+t.getMail()+"'>"+t.getLastname()+"</option>");
                        }%>
                </p>
                <p><input type="submit" value="Create new course" id="button"></p>
            </form>
            <form action="<%= request.getContextPath() %>/admin/table/schedule.jsp" method="get">
                <button type="submit" class="return">Exit</button>
            </form>
        </div>
</body>
</html>
