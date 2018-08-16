<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 16.08.18
  Time: 12:40
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
    <input type='text' name='id' value='0' hidden>
    <input type='text' name='firstName' value='' required>imię<br>
    <input type='text' name='lastName' value='' required>nazwisko<br>
    <input type='text' name='address' value='' required>adres<br>
    <input type='text' name='phone' value='' required>telefon<br>
    <input type='text' name='note' value='' required>opis<br>
    <input type='number' name='hourly_rate' value='' min='10' max='100' step='1' required>stawka godzinowa<br>
    <input type='submit' value='zapisz'>
</form>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
