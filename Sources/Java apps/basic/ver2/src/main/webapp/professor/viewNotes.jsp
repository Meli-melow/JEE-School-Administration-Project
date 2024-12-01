<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Voir et Modifier les Notes</title>
  <link rel="stylesheet" href="style1.css">
</head>
<body>
<div class="container">
  <h1>Voir et Modifier les Notes</h1>

  <!-- Récupérer les notes stockées dans la session -->
  <%
    List<String[]> notes = (List<String[]>) session.getAttribute("notes");
    if (notes == null) {
      notes = new ArrayList<>();
      session.setAttribute("notes", notes);
    }
  %>

  <!-- Formulaire pour sélectionner une promotion -->
  <form method="get" action="viewNotes.jsp">
    <label for="promo">Choisir une promotion :</label>
    <select name="promo" id="promo" onchange="this.form.submit()">
      <option value="">-- Sélectionnez une promotion --</option>
      <option value="ING1" <%= "ING1".equals(request.getParameter("promo")) ? "selected" : "" %>>ING1</option>
      <option value="ING2" <%= "ING2".equals(request.getParameter("promo")) ? "selected" : "" %>>ING2</option>
      <option value="ING3" <%= "ING3".equals(request.getParameter("promo")) ? "selected" : "" %>>ING3</option>
    </select>
  </form>

  <hr>

  <!-- Affichage des notes pour la promotion sélectionnée -->
  <%
    String selectedPromo = request.getParameter("promo");
    if (selectedPromo != null && !selectedPromo.isEmpty()) {
  %>
  <form method="post" action="viewNotes.jsp">
    <table>
      <thead>
      <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Note</th>
        <th>Supprimer</th>
      </tr>
      </thead>
      <tbody>
      <% for (int i = 0; i < notes.size(); i++) {
        String[] note = notes.get(i); // note[0]: promo, note[1]: nom, note[2]: prénom, note[3]: note
        if (note[0].equals(selectedPromo)) {
      %>
      <tr>
        <td><%= note[1] %></td>
        <td><%= note[2] %></td>
        <td>
          <input type="number" name="note_<%= i %>" value="<%= note[3] %>" step="0.01" min="0" max="20">
        </td>
        <td>
          <button type="submit" name="delete_<%= i %>">Supprimer</button>
        </td>
      </tr>
      <% } } %>
      </tbody>
    </table>
    <button type="submit">Enregistrer les modifications</button>
  </form>
  <% if (notes.stream().noneMatch(note -> note[0].equals(selectedPromo))) { %>
  <p>Aucune note trouvée pour cette promotion.</p>
  <% } %>
  <% } else { %>
  <p>Veuillez sélectionner une promotion.</p>
  <% } %>

  <!-- Suppression ou modification des notes -->
  <%
    if (request.getMethod().equalsIgnoreCase("POST")) {
      for (int i = 0; i < notes.size(); i++) {
        // Vérifie si le bouton de suppression a été cliqué pour cette note spécifique
        if (request.getParameter("delete_" + i) != null) {
          notes.remove(i);
          i--; // Ajuste l'index après suppression
        } else {
          // Met à jour les notes si elles sont modifiées
          String updatedNote = request.getParameter("note_" + i);
          if (updatedNote != null && !updatedNote.isEmpty()) {
            notes.get(i)[3] = updatedNote; // Mise à jour de la note
          }
        }
      }
    }
  %>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="professorHome.jsp" method="get">
    <button type="submit" class="return">Retourner à l'accueil</button>
  </form>
</div>
</body>
</html>
