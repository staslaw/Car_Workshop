<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 15.08.18
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<h2>Lista zleceń:</h2>
<table border="1">
    <tr>
        <th>id</th>
        <th>Data przyjęcia do naprawy</th>
        <th>Klient</th>
        <th>Pojazd</th>
        <th>Pracownik</th>
        <th>Status</th>
        <th>Szczegóły Naprawy</th>
        <th>Edycja</th>
        <th>Usuwanie</th>
    </tr>
    <c:forEach var="element" items="${orderList}">
        <tr>
            <td>${element.id}</td>
            <td>${element.serviceAccept}</td>
            <td>${element.vehicle.client.firstName}&nbsp${element.vehicle.client.lastName}</td>
            <td>${element.vehicle.model}</td>
            <td>${element.employee.firstName}&nbsp;${element.employee.lastName}</td>
            <td>${element.status.name}</td>
            <td><a href="order/details?id=${element.id}">szczegóły</a></td>
            <td><a href="order/update?id=${element.id}">edytuj</a></td>
            <td><a href="#">usuń</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
