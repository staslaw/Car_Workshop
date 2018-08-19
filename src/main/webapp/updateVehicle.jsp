<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="WEB-INF/fragments/header.jsp"/>
<form action='' method='post'>
    Make:
    <input type='text' name='make' value='${vehicle.make}' required><br>
    Model:
    <input type='text' name='model' value='${vehicle.model}' required><br>
    Production date:
    <input type='date' name='productionDate' value='${vehicle.productionDate}' required><br>
    Registration:
    <input type='text' name='registration' value='${vehicle.registration}' required><br>
    Next service:
    <input type='date' name='nextService' value='${vehicle.nextService}' required><br>
    Client:
    <%--<input type='number' min="1" name='clientId' value='${vehicle.client.id}' required><br>--%>
    <select name='clientId' id="client" required>
        <option value="" ></option>
        <c:forEach items="${clientList}" var="client">
            <option value="${client.id}">${client.firstName} ${client.lastName}</option>
        </c:forEach>
    </select><br>
    <input type='submit' value='Save'>
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
