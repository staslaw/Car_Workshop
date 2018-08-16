<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<form action='' method='post'>
    Model:
    <input type='text' name='model' value='${vehicle.model}' required><br>
    Make:
    <input type='text' name='make' value='${vehicle.make}' required><br>
    Production date:
    <input type='date' name='productionDate' value='${vehicle.productionDate}' required><br>
    Registration:
    <input type='text' name='registration' value='${vehicle.registration}' required><br>
    Next service:
    <input type='date' name='nextService' value='${vehicle.nextService}' required><br>
    Client id:
    <input type='number' min="1" name='clientId' value='${vehicle.client.id}' required><br><br>

    <input type='submit' value='Save'>
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
