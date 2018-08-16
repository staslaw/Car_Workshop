<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action='' method='post'>
    Model:
    <input type='text' name='model' value='${vehicle.model}'><br>
    Make:
    <input type='text' name='make' value='${vehicle.make}'><br>
    Production date:
    <input type='date' name='productionDate' value='${vehicle.productionDate}'><br>
    Registration:
    <input type='text' name='registration' value='${vehicle.registration}'><br>
    Next service:
    <input type='date' name='nextService' value='${vehicle.nextService}'><br>
    Client id:
    <input type='number' min="1" name='clientId' value='${vehicle.client.id}'><br><br>

    <input type='submit' value='Save'>
</form>

</body>
</html>
