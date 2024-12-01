<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="entities.Teacher"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profil - Professeur</title>
  <link rel="stylesheet" href="styleProfile.css">
</head>
<body>
<div class="container">
  <h1>Profil du Professeur</h1>

  <!-- Simuler une liste de professeurs -->
  <%
    List<Teacher> teachers = new ArrayList<>();
    teachers.add(new Teacher("test", "test", "test@example.com", "password123", "Mathématiques"));
    Teacher currentTeacher = teachers.get(0); // Supposons que ce soit le professeur actuel
  %>

  <!-- Informations du profil -->
  <div class="profile-card">
    <h2><%= currentTeacher.getFirstname() %> <%= currentTeacher.getLastname() %></h2>
    <p><strong>Email :</strong> <%= currentTeacher.getMail() %></p>
    <p><strong>Matière enseignée :</strong> <%= currentTeacher.getField() %></p>
  </div>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="professorHome.jsp" method="get">
    <button type="submit" class="return-btn">Retourner à l'accueil</button>
  </form>
</div>
</body>
</html>
