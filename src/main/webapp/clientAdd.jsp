<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 16.08.18
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>

<form action='/ClientUpdate' method='post'>
    <input type='text' name='id' value='0' hidden>
    <input type='text' name='firstName' value='' required>imię<br>
    <input type='text' name='lastName' value='' required>nazwisko<br>
    <input type='email' name='email' value='' required>email<br>
    <input type='number' name='phone' value='' min='100000000' max='999999999999' step='1' required>telefon<br>
    <input type='date' name='birthday' value='' min='1900-01-01' max='2018-01-01' required>data ur.<br>
    <input type='submit' value='zapisz'>
</form>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
