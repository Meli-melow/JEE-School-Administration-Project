<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Main page</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/student/styleStudentHome.css">
</head>
<body>
<div class="container">
  <h1>Main page</h1>
  <p>Welcome to your space ! Choose an option :</p>

  <!-- Boutons pour les différentes sections -->

  <form action="<%= request.getContextPath() %>/student/studentProfile.jsp" method="post">

    <button type="submit" class="action-btn">Profile</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentSchedule.jsp" method="post">
    <button type="submit" class="action-btn">Timetable</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentGrades.jsp" method="post">
    <button type="submit" class="action-btn">Grades</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentPerformance.jsp" method="post">
    <button type="submit" class="action-btn">Performances</button>
  </form>
</div>
</body>
</html>
