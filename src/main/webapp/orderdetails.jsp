<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
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
        <th>id zlecenia</th>
        <td>${order.id}</td>
    </tr>
    <tr>
        <th>Data przyjęcia do naprawy</th>
        <td>${order.serviceAccept}</td>
    </tr>
    <tr>
        <th>Planowana data rozpoczęcia naprawy</th>
        <td>${order.servicePlan}</td>
    </tr>
    <tr>
        <th>Data rozpoczęcia naprawy</th>
        <td>${order.serviceStart}</td>
    </tr>
    <tr>
        <th>Właściciel/Klient</th>
        <td>${order.vehicle.client.firstName} ${order.vehicle.client.lastName}</td>
    </tr>
    <tr>
        <th>Pojazd</th>
        <td>${order.vehicle.model} ${order.vehicle.make} ${order.vehicle.registration}</td>
    </tr>
    <tr>
        <th>Pracownik</th>
        <td>${order.employee.firstName} ${order.employee.lastName}</td>
    </tr>
    <tr>
        <th>Opis problemu</th>
        <td>${order.issueDesc}</td>
    </tr>
    <tr>
        <th>Opis naprawy</th>
        <td>${order.repairDesc}</td>
    </tr>
    <tr>
        <th>Koszt naprawy</th>
        <td>${order.repairCost}</td>
    </tr>
    <tr>
        <th>Koszt wykorzystanych części:</th>
        <td>${order.partsCost}</td>
    </tr>
    <tr>
        <th>Koszt roboczogodziny:</th>
        <td>${order.hourlyRate}</td>
    </tr>
    <tr>
        <th>Liczba roboczogodzin:</th>
        <td>${order.manHours}</td>
    </tr>
    <tr>
        <th>Status</th>
        <td>${order.status.name}</td>
    </tr>
    <tr>
        <th>Koszt całkowity</th>
        <td>
            <c:if test="${order.repairCost != 0}">
                <c:set var = "repairCost" value ="${order.repairCost}"/><fmt:formatNumber value = "${repairCost}" type="number" groupingUsed = "false" minFractionDigits="2" maxFractionDigits="2" />
            </c:if>
        </td>
    </tr>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
