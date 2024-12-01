<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="java.sql.Date"%>
<%@ page import="entities.Course"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Emploi du Temps - Professeur</title>
  <link rel="stylesheet" href="styleSchedule.css">
</head>
<body>
<div class="container">
  <h1>Emploi du Temps - Professeur</h1>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="professorHome.jsp" method="get">
    <button type="submit" class="return-btn">Retourner à l'accueil</button>
  </form>

  <!-- Navigation par semaine -->
  <%
    // Gestion des dates
    LocalDate today = LocalDate.now();
    LocalDate selectedDate = today; // Date sélectionnée (par défaut aujourd'hui)

    // Vérifier si une date a été passée en paramètre
    if (request.getParameter("selectedDate") != null) {
      selectedDate = LocalDate.parse(request.getParameter("selectedDate"));
    }

    // Déterminer la semaine (du lundi au vendredi)
    LocalDate startOfWeek = selectedDate.with(java.time.DayOfWeek.MONDAY);
    LocalDate endOfWeek = startOfWeek.plusDays(4); // Vendredi
  %>

  <!-- Section des boutons de navigation -->
  <div class="navigation-buttons">
    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= startOfWeek.minus(7, ChronoUnit.DAYS) %>">
      <button type="submit" class="nav-btn">Semaine précédente</button>
    </form>

    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= today %>">
      <button type="submit" class="nav-btn">Aujourd'hui</button>
    </form>

    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= startOfWeek.plus(7, ChronoUnit.DAYS) %>">
      <button type="submit" class="nav-btn">Semaine suivante</button>
    </form>
  </div>

  <!-- Sélecteur de date -->
  <form method="get" class="date-selector">
    <label for="selectedDate">Choisir une date :</label>
    <input type="date" id="selectedDate" name="selectedDate" value="<%= selectedDate %>">
    <button type="submit">Afficher</button>
  </form>

  <!-- Liste des cours simulée -->
  <%
    List<Course> courses = new ArrayList<>();
    // Cours pour la semaine actuelle
    courses.add(new Course("Sciences", Date.valueOf("2024-12-04"), "09:00", "02:00", "Mathématiques", "ING1", null)); // Mercredi
    courses.add(new Course("Sciences", Date.valueOf("2024-12-05"), "14:00", "02:00", "Physique", "ING2", null)); // Jeudi
    courses.add(new Course("Sciences", Date.valueOf("2024-12-06"), "10:00", "02:00", "Chimie", "ING3", null)); // Vendredi

    // Cours pour une autre semaine
    courses.add(new Course("Sciences", Date.valueOf("2024-12-11"), "09:00", "01:30", "Biologie", "ING1", null)); // Mercredi (semaine suivante)
    courses.add(new Course("Sciences", Date.valueOf("2024-12-12"), "13:00", "02:00", "Histoire", "ING2", null)); // Jeudi (semaine suivante)

    // Organisation des cours par jour pour la semaine sélectionnée
    Map<String, List<Course>> schedule = new LinkedHashMap<>();
    schedule.put("Lundi", new ArrayList<>());
    schedule.put("Mardi", new ArrayList<>());
    schedule.put("Mercredi", new ArrayList<>());
    schedule.put("Jeudi", new ArrayList<>());
    schedule.put("Vendredi", new ArrayList<>());

    for (Course course : courses) {
      LocalDate courseDate = course.getSlot().toLocalDate();
      if (!courseDate.isBefore(startOfWeek) && !courseDate.isAfter(endOfWeek)) {
        String day = "";
        switch (courseDate.getDayOfWeek()) {
          case MONDAY: day = "Lundi"; break;
          case TUESDAY: day = "Mardi"; break;
          case WEDNESDAY: day = "Mercredi"; break;
          case THURSDAY: day = "Jeudi"; break;
          case FRIDAY: day = "Vendredi"; break;
          default: continue;
        }
        schedule.get(day).add(course);
      }
    }
  %>

  <!-- Affichage de l'emploi du temps -->
  <div class="schedule-grid">
    <% for (Map.Entry<String, List<Course>> entry : schedule.entrySet()) { %>
    <div class="schedule-day">
      <h2><%= entry.getKey() %></h2>
      <% List<Course> dayCourses = entry.getValue(); %>
      <% if (dayCourses.isEmpty()) { %>
      <p>Aucun cours</p>
      <% } else { %>
      <ul>
        <% for (Course course : dayCourses) { %>
        <li>
          <strong><%= course.getCourseUnit() %></strong><br>
          <%= course.getHour() %> - Durée : <%= course.getDuration() %><br>
          Promotion : <%= course.getSchoolYear() %>
        </li>
        <% } %>
      </ul>
      <% } %>
    </div>
    <% } %>
  </div>
</div>
</body>
</html>
