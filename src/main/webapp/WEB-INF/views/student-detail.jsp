<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Деталі студента</title>
</head>
<body>

<h1>Деталі студента</h1>

<p><strong>ID:</strong> ${student.id}</p>
<p><strong>Ім'я:</strong> ${student.firstName}</p>
<p><strong>Прізвище:</strong> ${student.lastName}</p>
<p><strong>Username:</strong> ${student.lastName}</p>

<h2>Оцінки студента</h2>
<table border="1">
  <thead>
  <tr>
    <th>Предмет</th>
    <th>Оцінка</th>
    <th>Коментар</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="grade" items="${student.grades}">
    <tr>
      <td>${grade.subject.name}</td>
      <td>${grade.grade}</td>
      <td>${grade.comment}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
