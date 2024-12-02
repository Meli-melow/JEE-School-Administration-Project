<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="entities.Student"%>
<%@ page import="services.StudentService" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profil Étudiant</title>
  <link rel="stylesheet" href="styleStudentProfile.css">
</head>
<body>
<div class="container">
  <h1>Profile</h1>
  <!-- Bouton pour retourner à l'accueil -->
  <form action="studentHome.jsp" method="post">
    <button type="submit" class="return-btn">Main page</button>
  </form>

  <%
    // Simulation des données d'un étudiant
    List<Student> students = new ArrayList<>();
    students.add(new Student("Alice", "Durand", "alice.durand@cytech.fr", "password123", Date.valueOf("2002-06-15"), "ING1"));
    students.add(new Student("Bob", "Martin", "bob.martin@cytech.fr", "password456", Date.valueOf("2001-09-21"), "ING2"));
    students.add(new Student("Charlie", "Dupont", "charlie.dupont@cytech.fr", "password789", Date.valueOf("2003-01-12"), "ING3"));

    // Exemple : Sélectionner le premier étudiant (simule une connexion utilisateur)
    Student currentStudent = students.get(0); // Vous pouvez changer l'indice pour tester un autre étudiant
//    StudentService service = new StudentService();
//    Student currentStudent = service.getStudentByMail();
  %>

  <!-- Affichage des informations de l'étudiant -->
  <div class="profile">
    <p><strong>Lastname :</strong> <%= currentStudent.getLastname() %></p>
    <p><strong>Firstname :</strong> <%= currentStudent.getFirstname() %></p>
    <p><strong>Email :</strong> <%= currentStudent.getMail() %></p>
    <p><strong>Birthday :</strong> <%= currentStudent.getBirth() %></p>
    <p><strong>Promotion :</strong> <%= currentStudent.getSchoolYear() %></p>
  </div>
</div>
</body>
</html>
