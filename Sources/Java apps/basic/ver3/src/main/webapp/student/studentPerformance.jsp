<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="entities.Result" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Rapport de Performance</title>
  <link rel="stylesheet" href="styleStudentPerformance.css">
</head>
<body>
<div class="container">
  <h1>Rapport de Performance</h1>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="studentHome.jsp" method="get">
    <button type="submit" class="return-btn">Retourner à l'accueil</button>
  </form>

  <%
    // Simulation des résultats pour un étudiant
    List<Result> grades = new ArrayList<>();
    grades.add(new Result("Sciences", "Mathématiques", BigDecimal.valueOf(4), null, null));
    grades.add(new Result("Sciences", "Physique", BigDecimal.valueOf(3), null, null));
    grades.add(new Result("Sciences", "Chimie", BigDecimal.valueOf(2), null, null));
    grades.add(new Result("Sciences", "Biologie", BigDecimal.valueOf(5), null, null));

    // Simulation des notes obtenues
    List<Double> scores = new ArrayList<>();
    scores.add(15.5); // Mathématiques
    scores.add(13.0); // Physique
    scores.add(6.5);  // Chimie
    scores.add(4.0);  // Biologie
  %>

  <!-- Affichage du rapport -->
  <table border="1">
    <thead>
    <tr>
      <th>Unité d'Enseignement</th>
      <th>Note</th>
      <th>Commentaire</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < grades.size(); i++) { %>
    <tr>
      <td><%= grades.get(i).getCourseUnit() %></td>
      <td><%= scores.get(i) %></td>
      <td>
        <%
          double score = scores.get(i);
          if (score > 15) {
            out.print("Très Bien");
          } else if (score >= 10) {
            out.print("Bien");
          } else if (score >= 5) {
            out.print("Peu mieux faire");
          } else {
            out.print("Il faut plus travailler");
          }
        %>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
</body>
</html>
