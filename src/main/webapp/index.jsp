<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>


<table border="1">
    <tr>
        <th>id naprawy</th>
        <th>pracownik</th>
        <th>samochód</th>
        <th>nr rej</th>
        <th>data rozpoczęcia naprawy</th>
        <th>szczegóły</th>
    </tr>
    <c:forEach var="element" items="${orderList}">
        <tr>
            <td>${element.id}</td>
            <td>${element.employee.firstName} ${element.employee.lastName}</td>
            <td>${element.vehicle.make}</td>
            <td>${element.vehicle.registration}</td>
            <td>${element.serviceStart}</td>
            <td>szczegóły</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
