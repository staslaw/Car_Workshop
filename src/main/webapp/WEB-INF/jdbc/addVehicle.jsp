<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="" method="post">
    Model:
    <input type="text" name="model"><br>
    Make:
    <input type="text" name="make"><br>

    Production year:
    <input type="text" name="productionDate"><br> <%-- dodać rozwijaną listę --%>
<%--    <select name="year" multiple>
<c:forEach begin="1900" end="2018">
        <option value="${count}">
                ${count}</option>
</c:forEach>
    </select>
--%>
    Registration:
    <input type="text" name="registration"><br>
    Next inspection date:
    <input type="date" name="nextService"><br>



    Client:
    <input type="text" name="client"><br>  <%-- dodać rozwijaną listę --%>
    <c:forEach var="clients" items="${clients}">

    </c:forEach>

    <br>
    <input type="submit" value="Save">
</form>

</body>
</html>
