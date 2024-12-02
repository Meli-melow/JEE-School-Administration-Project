<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Accueil Étudiant</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/student/styleStudentHome.css">
</head>
<body>
<div class="container">
  <h1>Accueil - Étudiant</h1>
  <p>Bienvenue sur votre espace étudiant ! Choisissez une option :</p>
  <%=request.getParameter("mail")%>
  <%request.setAttribute("email","d");%>
  <!-- Boutons pour les différentes sections -->

  <form action="<%= request.getContextPath() %>/student/studentProfile.jsp" method="post">

    <button type="submit" class="action-btn">Profil</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentSchedule.jsp" method="post">
    <button type="submit" class="action-btn">Emploi du Temps</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentGrades.jsp" method="post">
    <button type="submit" class="action-btn">Relevé de Notes</button>
  </form>

  <form action="<%= request.getContextPath() %>/student/studentPerformance.jsp" method="post">
    <button type="submit" class="action-btn">Performances</button>
  </form>
</div>
</body>
</html>
