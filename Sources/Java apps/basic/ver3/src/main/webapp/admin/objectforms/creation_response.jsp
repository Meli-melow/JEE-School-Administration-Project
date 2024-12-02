<%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 01/12/2024
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Server Response</title>
</head>
<body>
    <%=request.getParameter("")%>
    <a href="<%=request.getContextPath()%>/admin/table/<%=request.getParameter("")%>">Exit</a>
</body>
</html>
