<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>

<form action='/EmployeeUpdate' method='post'>
    <input type='text' name='id' value='${param.id}' hidden>
    <input type='text' name='firstName' value='${param.firstName}' required>imię<br>
    <input type='text' name='lastName' value='${param.lastName}' required>nazwisko<br>
    <input type='text' name='address' value='${param.address}' required>adres<br>
    <input type='number' name='phone' value='${param.phone}' min='100000000' max='999999999999' step='1' required>telefon<br>
    <input type='text' name='note' value='${param.note}' required>opis<br>
    <input type='number' name='hourly_rate' value='${param.hourly_rate}' min='1' max='100' step='0.1' required>stawka godzinowa<br>
    <input type='submit' value='zapisz'>
    </form>

</body>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</html>
