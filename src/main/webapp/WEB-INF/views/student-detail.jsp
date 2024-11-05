<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Інформація про студента</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
  <h1 class="text-center mb-4">Деталі студента</h1>
  <a href="<c:url value='/logout' />" class="btn btn-danger">Logout</a>
  <div class="card p-3 mb-4">
    <p><strong>Ім'я:</strong> ${student.firstName}</p>
    <p><strong>Прізвище:</strong> ${student.lastName}</p>
  </div>

  <!-- Форма фільтрації -->
  <h2 class="mb-4">Фільтрувати оцінки</h2>
  <form action="/students/${student.id}" method="get" class="form-row align-items-end mb-4">
    <div class="form-group col-md-4">
      <label for="subject">Предмет:</label>
      <select name="subject" id="subject" class="form-control">
        <option value="">Всі предмети</option>
        <c:forEach var="subject" items="${subjects}">
          <option value="${subject.id}" <c:if test="${param.subject == subject.id}">selected</c:if>>${subject.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group col-md-3">
      <label for="startDate">Дата від:</label>
      <input type="date" name="startDate" id="startDate" value="${param.startDate}" class="form-control">
    </div>
    <div class="form-group col-md-3">
      <label for="endDate">Дата до:</label>
      <input type="date" name="endDate" id="endDate" value="${param.endDate}" class="form-control">
    </div>
    <div class="col-md-2">
      <button type="submit" class="btn btn-primary btn-block">Фільтрувати</button>
    </div>
  </form>

  <h2 class="mb-4">Оцінки студента</h2>
  <table class="table table-striped table-bordered">
    <thead class="thead-dark">
    <tr>
      <th scope="col">Предмет</th>
      <th scope="col">Дата</th>
      <th scope="col">Оцінка</th>
      <th scope="col">Коментар</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${grades}">
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
