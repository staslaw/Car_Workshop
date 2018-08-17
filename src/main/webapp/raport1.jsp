<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 17.08.18
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>

<form action="/Raport1" method="get">
    <input type="date" name="from" value="${from}">roboczogodziny od<br>
    <input type="date" name="to" value="${to}">roboczogodziny do<br>
    <input type="submit" value="wyświetl">
</form>

${news}<br><br>

<table border="1">
    <tr>
        <th>imię i nazwisko pracownika</th>
        <th>liczba roboczogodzin</th>
    </tr>
    <c:forEach var="element" items="${roboczogodziny}">
    <tr>
        <td>${element.key}</td>
        <td>${element.value}</td>
    </tr>
    </c:forEach>
</table>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
