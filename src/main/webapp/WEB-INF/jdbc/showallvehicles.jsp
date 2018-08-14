<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Vehicles:</h3>

<p align="right"><a href="/addVehicle">Add Vehicle</a></p>


<c:forEach var="vehicles" items="${vehicles}">
    <table>
        <tr>
            <td>${vehicles.model}</td> <td>${vehicles.make}</td> <td>${vehicles.productionDate}</td> <td>${vehicles.registration}</td> <td>${vehicles.nextService}</td> <a href="/editVehicle">Add Vehicle</a> <a href="/deleteVehicle">Delete Vehicle</a>
        </tr>
    </table>

    <br>
</c:forEach>

</body>
</html>
