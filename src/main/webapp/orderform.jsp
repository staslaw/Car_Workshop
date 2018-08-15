<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 15.08.18
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<form action='' method='post'>
    <input type='text' name='serviceAccept' value='${order.serviceAccept}'>Data przyjęcia do naprawy<br>
    <input type='text' name='servicePlan' value='${order.servicePlan}'>Planowana data rozpoczęcia naprawy<br>
    <input type='text' name='serviceStart' value='${order.serviceStart}'>Data rozpoczęcia naprawy<br>
    <input type='text' name='idKlienta' value='${order.vehicle.client.id}'>id Klienta<br>
    <input type='submit' value='zapisz'>
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>