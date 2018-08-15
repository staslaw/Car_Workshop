<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 15.08.18
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<h2>Szczegóły zlecenia:</h2>
<table border="1">
    <tr>
        <th>id</th><td>${order.id}</td>
    </tr>
    <tr>
        <th>Data przyjęcia do naprawy</th><td>${order.serviceAccept}</td>
    </tr>
    <tr>
        <th>Planowana data rozpoczęcia naprawy</th><td>${order.servicePlan}</td>
    </tr>
    <tr>
        <th>Data rozpoczęcia naprawy</th><td>${order.serviceStart}</td>
    </tr>
    <tr>
        <th>Klient</th><td>${order.vehicle.client.firstName}&nbsp${order.vehicle.client.lastName}</td>
    </tr>
    <tr>
        <th>Pojazd</th><td>${order.vehicle.model}</td>
    </tr>
    <tr>
        <th>Pracownik</th><td>${order.employee.firstName}&nbsp;${order.employee.lastName}</td>
    </tr>
    <tr>
        <th>Opis problemu</th><td>${order.issueDesc}</td>
    </tr>
    <tr>
        <th>Opis naprawy</th><td>${order.repairDesc}</td>
    </tr>
    <tr>
        <th>Koszt naprawy</th><td>${order.repairCost}</td>
    </tr>
    <tr>
        <th>Koszt wykorzystanych części:</th><td>${order.partsCost}</td>
    </tr>
    <tr>
        <th>Koszt roboczogodziny:</th><td>${order.hourlyRate}</td>
    </tr>
    <tr>
        <th>Liczba roboczogodzin:</th><td>${order.manHours}</td>
    </tr>
    <tr>
        <th>Status</th><td>${order.status.name}</td>
    </tr>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
