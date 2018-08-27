<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 00:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="WEB-INF/fragments/header.jsp"/>

<form action='/client/edit' method='post'>
    <input type='text' name='id' value='${client.id}' hidden>
    <input type='text' name='firstName' value='${client.firstName}' required>imię<br>
    <input type='text' name='lastName' value='${client.lastName}' required>nazwisko<br>
    <input type='email' name='email' value='${client.email}' required>email<br>
    <input type='tel' name='phone' value='${client.phone}' maxlength="15" placeholder="600500400" pattern="[0-9]{9,}" required>telefon<br>
    <input type='date' name='birthday' value='${client.birthday}' min='1900-01-01' max='2018-01-01' required>data ur.<br>
    <input type='submit' value='zapisz'>
</form>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
