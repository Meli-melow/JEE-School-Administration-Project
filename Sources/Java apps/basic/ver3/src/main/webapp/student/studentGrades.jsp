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
  <title>Relevé de Notes</title>
  <link rel="stylesheet" href="styleStudentGrades.css">
</head>
<body>
<div class="container">
  <h1>Relevé de Notes</h1>

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

    // Simulation des notes obtenues (peut être extraite d'une entité Assessment dans un contexte réel)
    List<Double> scores = new ArrayList<>();
    scores.add(15.5); // Mathématiques
    scores.add(13.0); // Physique
    scores.add(16.5); // Chimie
    scores.add(14.0); // Biologie

    // Calcul de la moyenne pondérée
    double totalScore = 0;
    double totalCoeff = 0;
    for (int i = 0; i < grades.size(); i++) {
      totalScore += scores.get(i) * grades.get(i).getUnitCoeff().doubleValue();
      totalCoeff += grades.get(i).getUnitCoeff().doubleValue();
    }
    double weightedAverage = totalCoeff != 0 ? totalScore / totalCoeff : 0;
  %>

  <!-- Affichage des notes -->
  <table border="1">
    <thead>
    <tr>
      <th>Unité d'Enseignement</th>
      <th>Note</th>
      <th>Coefficient</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < grades.size(); i++) { %>
    <tr>
      <td><%= grades.get(i).getCourseUnit() %></td>
      <td><%= scores.get(i) %></td>
      <td><%= grades.get(i).getUnitCoeff() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <!-- Affichage de la moyenne pondérée -->
  <div class="average">
    <p><strong>Moyenne Pondérée :</strong> <%= String.format("%.2f", weightedAverage) %></p>
  </div>
</div>
</body>
</html>
