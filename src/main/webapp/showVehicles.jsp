<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Pojazdy</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<h2>Lista pojazdów:</h2>

    <form action="/showAllVehicles" method="post">
        <fieldset>Klient: <input type="text" name="find" >
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><a href="/addVehicle">Add Vehicle</a></b>
        </fieldset>
    </form>

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
            <td>***repairs history***</td>
            <td><a href="/updateVehicle?id=${element.id}">Update</a></td>
            <td><a href="/deleteVehicle?id=${element.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
