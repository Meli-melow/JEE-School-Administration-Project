<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main page</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/professor/style.css">
</head>
<body>
<div class="container">
    <h1>Page d'accueil - Professeur</h1>
    <p>Welcome to your space ! Choose an option :</p>


    <form action="<%= request.getContextPath() %>/professor/profile.jsp" method="get">
        <button type="submit">Profile</button>
    </form>


    <form action="<%= request.getContextPath() %>/professor/professorSchedule.jsp" method="get">
        <button type="submit">Timetable</button>
    </form>

    <!-- Bouton vers la page Saisir les Notes -->
    <form action="<%= request.getContextPath() %>/professor/addNotes.jsp" method="get">
        <button type="submit">Add a Grade</button>
    </form>

    <!-- Bouton vers la page Regarder/Modifier les Notes -->
    <form action="<%= request.getContextPath() %>/professor/viewNotes.jsp" method="get">
        <button type="submit">Show/Change Grades</button>
    </form>
</div>
</body>
</html>
