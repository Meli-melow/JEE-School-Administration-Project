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
<%@ page import="services.StudentService" %>
<%@ page import="entities.Student" %>
<%@ page import="services.AdminService" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Timetable</title>
  <link rel="stylesheet" href="styleStudentSchedule.css">
</head>
<body>
<div class="container">
  <h1>Timetable</h1>

  <!-- Bouton pour retourner à l'accueil -->
  <form action="studentHome.jsp" method="get">
    <button type="submit" class="return-btn">Main page</button>
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

    // Simuler une promotion de l’étudiant
//    StudentService stService = new StudentService();
//    Student student = stService.getStudentByMail();
//    String studentPromotion = student.getSchoolYear();
    String studentPromotion = "ING1 GI GR1";
    // Liste simulée des cours
    List<Course> courses = new ArrayList<>();
    courses.add(new Course("Sciences", Date.valueOf("2024-12-04"), "08h30", "3h00", "ING1 GI GR1", null)); // Mercredi
    courses.add(new Course("Sciences", Date.valueOf("2024-12-05"), "12h00", "3h00", "ING1 GI GR1", null)); // Jeudi
    courses.add(new Course("Sciences", Date.valueOf("2024-12-06"), "10:00", "3h00", "ING1 GI GR1", null)); // Vendredi
    courses.add(new Course("Sciences", Date.valueOf("2024-12-11"), "09:00", "01:30", "ING1 GI GR1", null)); // Mercredi (semaine suivante)

//    AdminService service = new AdminService();
//    List<Course> c = service.getAllCourses();
    // Organisation des cours par jour pour la semaine sélectionnée
    Map<String, List<Course>> schedule = new LinkedHashMap<>();
    schedule.put("Monday", new ArrayList<>());
    schedule.put("Tuesday", new ArrayList<>());
    schedule.put("Wednesday", new ArrayList<>());
    schedule.put("Thursday", new ArrayList<>());
    schedule.put("Friday", new ArrayList<>());

    for (Course course : courses) {
      if (!course.getSchoolYear().equals(studentPromotion)) {
        continue; // Filtrer les cours qui ne sont pas pour la promotion de l’étudiant
      }
      LocalDate courseDate = course.getDay().toLocalDate();
      if (!courseDate.isBefore(startOfWeek) && !courseDate.isAfter(endOfWeek)) {
        String day = "";
        switch (courseDate.getDayOfWeek()) {
          case MONDAY: day = "Monday"; break;
          case TUESDAY: day = "Tuesday"; break;
          case WEDNESDAY: day = "Wednesday"; break;
          case THURSDAY: day = "Thursday"; break;
          case FRIDAY: day = "Friday"; break;
          default: continue;
        }
        schedule.get(day).add(course);
      }
    }
  %>

  <!-- Section des boutons de navigation -->
  <div class="navigation-buttons">
    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= startOfWeek.minus(7, ChronoUnit.DAYS) %>">
      <button type="submit" class="nav-btn">Last week</button>
    </form>

    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= today %>">
      <button type="submit" class="nav-btn">Today</button>
    </form>

    <form method="get" class="inline-form">
      <input type="hidden" name="selectedDate" value="<%= startOfWeek.plus(7, ChronoUnit.DAYS) %>">
      <button type="submit" class="nav-btn">Next week</button>
    </form>
  </div>

  <!-- Sélecteur de date -->
  <form method="get" class="date-selector">
    <label for="selectedDate">Choose a date :</label>
    <input type="date" id="selectedDate" name="selectedDate" value="<%= selectedDate %>">
    <button type="submit">Show</button>
  </form>

  <!-- Affichage de l'emploi du temps -->
  <div class="schedule-grid">
    <% for (Map.Entry<String, List<Course>> entry : schedule.entrySet()) { %>
    <div class="schedule-day">
      <h2><%= entry.getKey() %></h2>
      <% List<Course> dayCourses = entry.getValue(); %>
      <% if (dayCourses.isEmpty()) { %>
      <p>No course</p>
      <% } else { %>
      <ul>
        <% for (Course course : dayCourses) { %>
        <li>
          <strong><%= course.getField() %></strong><br>
          <%= course.getHour() %> - Duration : <%= course.getDuration() %>
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
