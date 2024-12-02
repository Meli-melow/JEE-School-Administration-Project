<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Grades</title>
  <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="container">
  <h1>Add Grades</h1>

  <!-- Définition des étudiants -->
  <%
    List<String[]> students = new ArrayList<>();
    students.add(new String[]{"Alice", "Durand", "ING1"});
    students.add(new String[]{"Bob", "Martin", "ING1"});
    students.add(new String[]{"Charlie", "Dupont", "ING2"});
    students.add(new String[]{"Eve", "Leclerc", "ING3"});

    // Créer ou récupérer la liste des notes depuis la session
    List<String[]> notes = (List<String[]>) session.getAttribute("notes");
    if (notes == null) {
      notes = new ArrayList<>();
      session.setAttribute("notes", notes);
    }
  %>

  <!-- Formulaire pour sélectionner une promotion -->
  <form method="get" action="addNotes.jsp">
    <label for="promo">Choose a promotion :</label>
    <select name="promo" id="promo" onchange="this.form.submit()">
      <option value="">-- Choose a promotion --</option>
      <option value="ING1" <%= "ING1".equals(request.getParameter("promo")) ? "selected" : "" %>>ING1</option>
      <option value="ING2" <%= "ING2".equals(request.getParameter("promo")) ? "selected" : "" %>>ING2</option>
      <option value="ING3" <%= "ING3".equals(request.getParameter("promo")) ? "selected" : "" %>>ING3</option>
    </select>
  </form>

  <hr>

  <!-- Affichage des étudiants pour la promotion sélectionnée -->
  <%
    String selectedPromo = request.getParameter("promo");
    if (selectedPromo != null && !selectedPromo.isEmpty()) {
      boolean found = false;
  %>
  <form method="post" action="addNotes.jsp">
    <table>
      <thead>
      <tr>
        <th>Name</th>
        <th>Firstname</th>
        <th>Grade</th>
      </tr>
      </thead>
      <tbody>
      <% for (String[] student : students) {
        if (student[2].equals(selectedPromo)) {
          found = true;
      %>
      <tr>
        <td><%= student[1] %></td>
        <td><%= student[0] %></td>
        <td><input type="number" name="note_<%= student[0] %>" step="0.01" min="0" max="20"></td>
      </tr>
      <% } } %>
      </tbody>
    </table>
    <% if (!found) { %>
    <p>No student in this promotion.</p>
    <% } else { %>
    <button type="submit">Save Grades</button>
    <% } %>
  </form>
  <% } else { %>
  <p>Select a promotion.</p>
  <% } %>

  <!-- Stocker les notes soumises dans la session -->
  <%
    if (request.getMethod().equalsIgnoreCase("POST")) {
      for (String[] student : students) {
        String noteParam = request.getParameter("note_" + student[0]);
        if (noteParam != null && !noteParam.isEmpty()) {
          notes.add(new String[]{student[2], student[1], student[0], noteParam});
        }
      }
    }
  %>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="professorHome.jsp" method="get">
    <button type="submit" class="return">Main page</button>
  </form>
</div>
</body>
</html>
