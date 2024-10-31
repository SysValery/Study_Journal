<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Додати Оцінку</title>
</head>
<body>

<h1>Додати Оцінку</h1>

<form action="/grades/save" method="post">
    <input type="hidden" name="teacher.id" value="${grade.teacher.id}">

    <label for="student">Студент:</label>
    <select name="student.id" id="student" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.id}">${student.firstName} ${student.lastName}</option>
        </c:forEach>
    </select><br/>

    <label for="subject">Предмет:</label>
    <select name="subject.id" id="subject" required>
        <c:forEach var="subject" items="${subjects}">
            <option value="${subject.id}">${subject.name}</option>
        </c:forEach>
    </select><br/>

    <label for="date">Дата:</label>
    <input type="date" name="date" id="date" required/><br/>

    <label for="grade">Оцінка:</label>
    <input type="number" name="grade" id="grade" required/><br/>

    <label for="comment">Коментар:</label>
    <textarea name="comment" id="comment" rows="4"></textarea><br/>

    <button type="submit">Зберегти</button>
</form>

<a href="/teachers/${teacherId}">Повернутися</a>

</body>
</html>
