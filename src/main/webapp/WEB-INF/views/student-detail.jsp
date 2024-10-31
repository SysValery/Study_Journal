<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Деталі студента</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
  <h1 class="text-center mb-4">Деталі студента</h1>

  <div class="card p-3 mb-4">
    <p><strong>Ім'я:</strong> ${student.firstName}</p>
    <p><strong>Прізвище:</strong> ${student.lastName}</p>
  </div>

  <h2 class="mb-4">Оцінки студента</h2>
  <table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
      <th scope="col">
        <a href="?sortField=subject&sortDir=${sortDir == 'asc' ? 'desc' : 'asc'}" class="text-white">Предмет</a>
      </th>
      <th scope="col">
        <a href="?sortField=date&sortDir=${sortDir == 'asc' ? 'desc' : 'asc'}" class="text-white">Дата</a>
      </th>
      <th scope="col">Оцінка</th>
      <th scope="col">Коментар</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${student.grades}">
      <tr>
        <td>${grade.subject.name}</td>
        <td>${grade.date != null ? grade.date.toString() : ''}</td>
        <td>${grade.grade}</td>
        <td>${grade.comment}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
