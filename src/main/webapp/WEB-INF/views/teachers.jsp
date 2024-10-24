<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список викладачів</title>
</head>
<body>

<h1>Список викладачів</h1>

<!-- Посилання для додавання нового викладача -->
<a href="${pageContext.request.contextPath}/teachers/new">Додати нового викладача</a>

<!-- Таблиця для відображення викладачів -->
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Ім'я</th>
        <th>Прізвище</th>
        <th>Логін</th>
        <th>Дії</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr>
            <td>${teacher.id}</td>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
<%--            <td>Поле логіну </td>--%>
            <td>${teacher.username}</td>
            <td>
                <!-- Посилання для редагування та видалення викладача -->
                <a href="${pageContext.request.contextPath}/teachers/${teacher.id}">Деталі</a> |
                <a href="${pageContext.request.contextPath}/teachers/edit/${teacher.id}">Редагувати</a> |
                <a href="${pageContext.request.contextPath}/teachers/delete/${teacher.id}"
                   onclick="return confirm('Ви впевнені, що хочете видалити викладача?');">Видалити</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
