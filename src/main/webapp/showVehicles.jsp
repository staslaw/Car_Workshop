<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Vehicles:</h3>
<h2 align="center"><a href="/addVehicle">Add Vehicle</a></h2>

<table border="1">
    <tr>
        <th>id</th>
        <th>Model</th>
        <th>Make</th>
        <th>Production date</th>
        <th>Registration</th>
        <th>Next service</th>
        <th>Client name</th>
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
            <td><a href="/updateVehicle?id=${element.id}">Update</a></td>
            <td><a href="/deleteVehicle?id=${element.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
