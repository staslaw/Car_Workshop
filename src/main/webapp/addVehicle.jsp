<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<form action="" method="post">
    Make:
    <input type="text" name="make" required><br>
    Model:
    <input type="text" name="model" required><br>
    Production date:
    <input type="date" name="productionDate" required><br>
    Registration:
    <input type="text" name="registration" required><br>
    Next inspection date:
    <input type="date" name="nextService" required><br>
    Client id:
    <input type="number" min="1" name="clientId" required><br>
    <br>
    <input type="submit" value="Save">
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
