# JEE-School-Administration-Project
 A school administration web app made with Java Entreprise Edition

This project has 3 differents parts :
-The Student space
-The Teacher space
-The Admin space

For usage :
• focused on the database, please refer to the latest ver3 repertory ;
• focused on the front end, please refer to the latest commit regarding the addition of a webapp repertory.
They don't belong to the same folder project.

You need to login to access these spaces. The servlets check if the email and the password are in the right format and correspond to the database.
You need to insert into the database at least an Admin with an email in the "firstname.lastname@cyu.fr" format and a password that has at least 8 characters.

The Student and Teacher space has the layout and general navigation done but the data in the pages that are supposed to come from the database are placeholders.

The Admin space can :
-Check the list of Student in the database
-Check the list of Teacher in the database
-Check the list of Admin in the database
-Check the list of Course in the database
-Add a Student in the database
-Add a Teacher in the database
-Add an Admin in the database
-Add a Course in the database


There are functionnalities from the backend that are not implemented in the frontend :
-The ability to change the information of any user's profile in the database
-The ability for an user to change their password in the database
-The ability to change the details of a course in the database
-Deleting an user or a course
-Filtering of the list of Course according to the day, duration, starting hour, field of the course.
-Filtering of the list of Student according to the school year
-Filtering of the list of Teacher according to their field
-Automatically assign a student a course according to their promotion(school year)

