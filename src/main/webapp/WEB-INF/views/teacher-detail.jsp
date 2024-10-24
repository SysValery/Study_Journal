<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Деталі викладача</title>
</head>
<body>

<h1>Деталі викладача</h1>

<p><strong>ID:</strong> ${teacher.id}</p>
<p><strong>Ім'я:</strong> ${teacher.firstName}</p>
<p><strong>Прізвище:</strong> ${teacher.lastName}</p>
<%--<p><strong>Username:</strong> ${student.lastName}</p>--%>

<!-- Кнопка для додавання нової оцінки -->
<form action="/grades/new" method="get">
    <input type="hidden" name="teacherId" value="${teacher.id}">
    <button type="submit">Додати оцінку</button>
</form>


<h2>Оцінки, виставлені викладачем</h2>
<table border="1">
    <thead>
    <tr>
        <th>Ім'я студента</th>
        <th>Прізвище студента</th>
        <th>Предмет</th>
        <th>Оцінка</th>
        <th>Коментар</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${teacher.grades}">
        <tr>
            <td>${grade.student.firstName}</td>
            <td>${grade.student.lastName}</td>
            <td>${grade.subject.name}</td>
            <td>${grade.grade}</td>
            <td>${grade.comment}</td>
            <td><a href="/grades/edit/${grade.id}">Редагувати</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
