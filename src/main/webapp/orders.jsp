<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
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
<h2>Lista zleceń<c:if test="${!empty chosedEmployee}"> ${chosedEmployee.firstName} ${chosedEmployee.lastName}</c:if>:</h2>
<p>Dodaj nowe zlecenie <a href="/order/add">Dodaj</a></p>
<table border="1">
    <tr>
        <th>id</th>
        <th>Data przyjęcia do naprawy</th>
        <th>Klient</th>
        <th>Pojazd</th>
        <th>Pracownik</th>
        <th>Status</th>
        <th>Koszt całkowity</th>
        <th>Szczegóły Naprawy</th>
        <th>Edycja</th>
        <th>Usuwanie</th>
    </tr>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.serviceAccept}</td>
            <td>${order.vehicle.client.firstName}&nbsp${order.vehicle.client.lastName}</td>
            <td>${order.vehicle.model} ${order.vehicle.make}</td>
            <td>${order.employee.firstName}&nbsp;${order.employee.lastName}</td>
            <td>${order.status.name}</td>
            <td>
                <c:if test="${order.repairCost != 0}">
                    <c:set var = "repairCost" value ="${order.repairCost}"/><fmt:formatNumber value = "${repairCost}" type="number" groupingUsed = "false" minFractionDigits="2" maxFractionDigits="2" />
                </c:if>
            </td>
            <td><a href="/order/details?id=${order.id}">szczegóły</a></td>
            <td><a href="/order/update?id=${order.id}">edytuj</a></td>
            <td><a href="#">usuń</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
