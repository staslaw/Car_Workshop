<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 21.08.18
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>

<form action="/Raport2" method="get">
    <input type="date" name="from" value="${from}">zysk od<br>
    <input type="date" name="to" value="${to}">do<br>
    <input type="submit" value="wyświetl">
</form>

${news}<br><br>

<table border="1">
    <tr>
        <th>od</th>
        <th>do</th>
        <th>zysk</th>
    </tr>
        <tr>
            <td>${from}</td>
            <td>${to}</td>
            <td>${zysk}</td>
        </tr>
</table>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
