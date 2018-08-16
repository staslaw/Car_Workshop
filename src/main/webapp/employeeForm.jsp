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
    <input type='text' name='id' value='${employee.id}' hidden>
    <input type='text' name='firstName' value='${employee.firstName}' required>imię<br>
    <input type='text' name='lastName' value='${employee.lastName}' required>nazwisko<br>
    <input type='text' name='address' value='${employee.address}' required>adres<br>
    <input type='tel' name='phone' placeholder="600500400" pattern="[0-9]{9,}" required>telefon<br>
    <input type='text' name='note' value='${employee.note}' required>opis<br>
    <input type='number' name='hourly_rate' value='${employee.hourly_rate}' min='10' max='100' step='1' required>stawka godzinowa<br>
    <input type='submit' value='zapisz'>
    </form>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
