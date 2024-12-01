<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil Professeur</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/professor/style.css">
</head>
<body>
<div class="container">
    <h1>Page d'accueil - Professeur</h1>
    <p>Bienvenue sur votre espace ! Choisissez une option :</p>


    <form action="<%= request.getContextPath() %>/professor/profile.jsp" method="get">
        <button type="submit">Profil</button>
    </form>


    <form action="<%= request.getContextPath() %>/professor/professorSchedule.jsp" method="get">
        <button type="submit">Emploi du Temps</button>
    </form>

    <!-- Bouton vers la page Saisir les Notes -->
    <form action="<%= request.getContextPath() %>/professor/addNotes.jsp" method="get">
        <button type="submit">Saisir les Notes</button>
    </form>

    <!-- Bouton vers la page Regarder/Modifier les Notes -->
    <form action="<%= request.getContextPath() %>/professor/viewNotes.jsp" method="get">
        <button type="submit">Regarder/Modifier les Notes</button>
    </form>
</div>
</body>
</html>
