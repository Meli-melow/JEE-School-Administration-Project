<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="entities.Student"%>
<%@ page import="services.AdminService" %>
<%@ page import="entities.Admin" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile</title>
  <link rel="stylesheet" href="../student/styleStudentProfile.css">
</head>
<body>
<div class="container">
  <h1>Profile</h1>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="main_admin.jsp" method="get">
    <button type="submit" class="return-btn">Main page</button>
  </form>



  <!-- Affichage des informations de l'admin -->
  <div class="profile">
    <p><strong>Last name :</strong> Doe</p>
    <p><strong>First name :</strong> John</p>
    <p><strong>Email :</strong> john.doe@cyu.fr</p>
  </div>
</div>
</body>
</html>
