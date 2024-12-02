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
  <title>Results</title>
  <link rel="stylesheet" href="styleStudentPerformance.css">
</head>
<body>
<div class="container">
  <h1>Results</h1>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="studentHome.jsp" method="get">
    <button type="submit" class="return-btn">Main page</button>
  </form>

  <%
    // Simulation des résultats pour un étudiant
    List<Result> grades = new ArrayList<>();
    grades.add(new Result("Sciences", "Maths", BigDecimal.valueOf(4), null, null));
    grades.add(new Result("Sciences", "Physics", BigDecimal.valueOf(3), null, null));
    grades.add(new Result("Sciences", "Chemistry", BigDecimal.valueOf(2), null, null));
    grades.add(new Result("Sciences", "Biology", BigDecimal.valueOf(5), null, null));

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
      <th>Field</th>
      <th>Results</th>
      <th>Comment</th>
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
            out.print("Excellent");
          } else if (score >= 10) {
            out.print("Good");
          } else if (score >= 5) {
            out.print("A little more effort");
          } else {
            out.print("You need to work harder");
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
