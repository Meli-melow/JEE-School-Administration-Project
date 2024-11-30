<%--
  Created by IntelliJ IDEA.
  User: frime
  Date: 27/11/2024
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../template/form.css">
</head>
<body>
    <div id="container">
        <div id="content">
            <p>New course</p>
            <form>
                <p>Field: <input type="text" id="field" name="field" required></p>
                <p>Timeslot: <input type="datetime-local" id="slot" name="slot"></p>
                <p>
                    Duration: <select name="duration" required>
                        <option value="1:30">1h30</option>
                        <option value="3:00">3h00</option>
                    </select>
                </p>
                <p>
                    Unit: <select name="unitName" required>
                        <option value="maths">Maths</option>
                        <option value="computer_science">Computer Science</option>
                    </select>
                </p>
                <p>
                    School Year: <select name="school_year" required>
                        <option value="ING1">ING1</option>
                        <option value="ING2">ING2</option>
                    </select>
                </p>
                <p><input type="submit" value="Create new course" id="button"></p>
            </form>
        </div>
    </div>
</body>
</html>
