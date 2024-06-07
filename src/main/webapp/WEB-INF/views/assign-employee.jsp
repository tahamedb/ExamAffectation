<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assign Employee to Project</title>
</head>
<body>
<h1>Assign Employee to Project</h1>
<h2>Project: ${project.name}</h2>

<form action="/projects/${project.id}/assign" method="post">
    <label for="employeeId">Employee:</label>
    <select id="employeeId" name="employeeId">
        <c:forEach items="${employees}" var="employee">
            <option value="${employee.id}">${employee.name}</option>
        </c:forEach>
    </select>
    <br>
    <label for="implication">Implication:</label>
    <input type="number" id="implication" name="implication" step="0.01" min="0" max="100" required>
    <br>
    <input type="submit" value="Assign">
</form>
</body>
</html>