<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<h2>Vehicles:</h2>
<h3 align="center"><a href="/addVehicle">Add Vehicle</a></h3>

<table border="1">
    <tr>
        <th>id</th>
        <th>Model</th>
        <th>Make</th>
        <th>Production date</th>
        <th>Registration</th>
        <th>Next service</th>
        <th>Client name</th>
        <th>Repairs history</th>
        <%--<th>Edit</th>--%>
        <%--<th>Remove</th>--%>
    </tr>
    <c:forEach var="element" items="${vehicleList}">
        <tr>
            <td>${element.id}</td>
            <td>${element.model}</td>
            <td>${element.make}</td>
            <td>${element.productionDate}</td>
            <td>${element.registration}</td>
            <td>${element.nextService}</td>
            <td>${element.client.firstName}&nbsp;${element.client.lastName}</td>
            <td><a href="/orders/vehicle?id=${element.id}">Check History</a></td>
            <td><a href="/updateVehicle?id=${element.id}">Update</a></td>
            <td><a href="/deleteVehicle?id=${element.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
