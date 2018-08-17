<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.08.18
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<form action="" method="post">
    <fieldset>
        <legend>
            Wyszukiwanie klienta po nazwisku:
        </legend>
         <input type="text" name="find" required>
        <input type="submit" value="Szukaj">
    </fieldset>
</form>
<p style="color:red">${findInfo}</p>
<h2>Lista klientów<c:if test="${!empty chosedClient}"> [szukaj: ${chosedClient}]</c:if></h2>
<table border="1">
    <tr>
        <th>id</th>
        <th>imię</th>
        <th>nazwisko</th>
        <th>email</th>
        <th>nr tel.</th>
        <th>data ur.</th>
        <th>samochody</th>
        <th>edytuj</th>
        <th>usuń</th>
    </tr>
<c:forEach var="element" items="${clientList}">
    <tr>
        <td>${element.id}</td>
        <td>${element.firstName}</td>
        <td>${element.lastName}</td>
        <td>${element.email}</td>
        <td>${element.phone}</td>
        <td>${element.birthday}</td>
        <td>samochody</td>
        <td><a href="/ClientUpdate?id=${element.id}">edytuj</a></td>
        <td><a href="/ClientDelete?id=${element.id}">usuń</a></td>
    </tr>
</c:forEach>
</table>
<a href="/clientAdd.jsp">dodaj</a>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
