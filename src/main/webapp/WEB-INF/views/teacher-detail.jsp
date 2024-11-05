<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Деталі викладача</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Деталі викладача</h1>
        <a href="<c:url value='/logout' />" class="btn btn-danger">Logout</a>
    </div>

    <div class="mb-4">
        <p><strong>Ім'я:</strong> ${teacher.firstName}</p>
        <p><strong>Прізвище:</strong> ${teacher.lastName}</p>
    </div>

    <!-- Кнопка для додавання нової оцінки -->
    <form action="/teachers/newGrade" method="get" class="mb-4">
        <input type="hidden" name="teacherId" value="${teacher.id}">
        <button type="submit" class="btn btn-success">Додати оцінку</button>
    </form>

    <!-- Фільтр оцінок -->
    <h2>Фільтр оцінок</h2>
    <form action="/teachers/${teacher.id}" method="get" class="form-row align-items-end mb-4">
        <div class="form-group col-md-4">
            <label for="subject">Предмет:</label>
            <select name="subject" id="subject" class="form-control">
                <option value="">Всі предмети</option>
                <c:forEach var="subject" items="${subjects}">
<%--                    <option value="${subject.id}">${subject.name}</option>--%>
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
            <input type="date" name="endDate" id="endDate"  value="${param.endDate}"  class="form-control">
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-primary btn-block">Фільтрувати</button>
        </div>
    </form>

    <!-- Таблиця оцінок -->
    <h2>Оцінки, виставлені викладачем</h2>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Ім'я студента</th>
            <th>Прізвище студента</th>
            <th>Предмет</th>
            <th>Оцінка</th>
            <th>Дата виставлення</th>
            <th>Коментар</th>
            <th>Дії</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="grade" items="${grades}">
            <tr>
                <td>${grade.student.firstName}</td>
                <td>${grade.student.lastName}</td>
                <td>${grade.subject.name}</td>
                <td>${grade.grade}</td>
                <td>${grade.date}</td>
                <td>${grade.comment}</td>
                <td>
                    <div class="d-flex justify-content-start">
                        <a href="/teachers/editGrade/${grade.id}" class="btn btn-sm btn-warning mr-2">Редагувати</a>
                        <form action="/teachers/deleteGrade/${grade.id}" method="post">
                            <input type="hidden" name="id" value="${grade.id}" />
                            <button type="submit" class="btn btn-sm btn-danger">Видалити</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
