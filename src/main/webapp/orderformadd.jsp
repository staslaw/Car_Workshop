<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 16.08.18
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="WEB-INF/fragments/header.jsp"/>
<p>
<ul style="color:red">
    <c:forEach items="${formInfo}" var="info">
        <li>${info}</li>
    </c:forEach>
</ul>
</p>
<form action='' method='post'>
    Data przyjęcia do naprawy:
    <input type='date' name='serviceAccept' value='${order.serviceAccept}' required><br>
    Planowana data rozpoczęcia naprawy:
    <input type='date' name='servicePlan' value='${order.servicePlan}' ><br>
    Pojazd:
    <select name='vehicle' id="vehicle" required>
        <option value="">Wybierz samochód:</option>
        <c:forEach items="${vehicles}" var="vehicle">
            <option <c:if test="${vehicle.id == order.vehicle.id}">selected</c:if> value="${vehicle.id}">
                    ${vehicle.model} ${vehicle.make} ${vehicle.registration}
            </option>
        </c:forEach>
    </select><br>
    Właściciel pojazdu:
    <input type='text' id='vehicleOwner' readonly  value='${order.vehicle.client.firstName} ${order.vehicle.client.lastName}'><br>
    Pracownik:
    <select name='employee' id="employee" class="costs-counting">
        <c:forEach items="${employees}" var="employee">
            <option <c:if test="${employee.id == order.employee.id}">selected</c:if> value="${employee.id}" data-hourly-rate="${employee.hourly_rate}">
                    ${employee.firstName} ${employee.lastName} (stawka: ${employee.hourly_rate} zł/h)
            </option>
        </c:forEach>
    </select><br>
    Opis problemu:
    <input type='text' name='issueDesc' value='${order.issueDesc}' required><br>
    <input type='submit' value='zapisz'>
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
