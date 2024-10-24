<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редагувати Оцінку</title>
</head>
<body>

<h1>Редагувати Оцінку</h1>

<form action="/grades/update" method="post">
    <input type="hidden" name="teacher.id" value="${grade.teacher.id}">
    <input type="hidden" name="student.id" value="${grade.student.id}">
    <input type="hidden" name="subject.id" value="${grade.subject.id}">

    <label for="date">Дата:</label>
    <input type="date" name="date" id="date" value="${grade.date}" required/><br/>

    <input type="hidden" name="id" value="${grade.id}"/>
    <label for="grade">Оцінка:</label>
    <input type="number" name="grade" id="grade" value="${grade.grade}" required/><br/>

    <label for="comment">Коментар:</label>
    <textarea name="comment" id="comment" rows="4" required>${grade.comment}</textarea><br/>

    <button type="submit">Зберегти</button>
</form>

<a href="/teachers/${grade.teacher.id}">Повернутися</a>

</body>
</html>
