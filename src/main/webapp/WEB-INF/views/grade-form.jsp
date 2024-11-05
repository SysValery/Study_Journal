<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${grade.id == null ? "Додати Оцінку" : "Редагувати Оцінку"}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/grade-form.css">
</head>
<body class="bg-light">

<div class="container my-5">
    <h1 class="text-center mb-4">${grade.id == null ? "Додати Оцінку" : "Редагувати Оцінку"}</h1>

    <!-- Форма надсилання до загального методу для збереження або оновлення -->
    <form:form action="${pageContext.request.contextPath}/teachers/saveOrUpdateGrade" modelAttribute="grade" method="post" class="border p-4 bg-white rounded shadow">

        <!-- Приховане поле для teacherId -->
        <form:hidden path="teacher.id" value="${teacherId}"/>

        <!-- Приховане поле для id (тільки для редагування) -->
        <c:if test="${grade.id != null}">
            <form:hidden path="id"/>
        </c:if>

        <!-- Поле для вибору студента -->
        <div class="form-group">
            <label for="student">Студент:</label>
            <form:select path="student.id" id="student" class="form-control">
                <form:options items="${students}" itemValue="id" itemLabel="fullName"/>
            </form:select>
            <form:errors path="student.id" cssClass="text-danger"/>
        </div>

        <!-- Поле для вибору предмету -->
        <div class="form-group">
            <label for="subject">Предмет:</label>
            <form:select path="subject.id" id="subject" class="form-control">
                <form:options items="${subjects}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="subject.id" cssClass="text-danger"/>
        </div>

        <!-- Поле для дати -->
        <div class="form-group">
            <label for="date">Дата:</label>
            <form:input path="date" id="date" type="date" class="form-control"/>
            <form:errors path="date" cssClass="text-danger"/>
        </div>

        <!-- Поле для оцінки -->
        <div class="form-group">
            <label for="grade">Оцінка:</label>
            <form:input path="grade" id="grade" type="number" class="form-control"/>
            <form:errors path="grade" cssClass="text-danger"/>
        </div>

        <!-- Поле для коментаря -->
        <div class="form-group">
            <label for="comment">Коментар:</label>
            <form:textarea path="comment" id="comment" rows="4" class="form-control"></form:textarea>
            <form:errors path="comment" cssClass="text-danger"/>
        </div>

        <!-- Кнопки для збереження або скасування -->
        <div class="d-flex justify-content-between">
            <a href="${pageContext.request.contextPath}/teachers/${teacherId}" class="btn btn-secondary">Повернутися</a>
            <button type="submit" class="btn btn-primary">${grade.id == null ? "Додати" : "Зберегти"}</button>
        </div>
    </form:form>
</div>

</body>
</html>
