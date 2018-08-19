<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 16.08.18
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p>
    <ul style="color:red">
        <c:forEach items="${formInfo}" var="info">
            <li>${info}</li>
        </c:forEach>
    </ul>
</p>
<form action='/order/add' method='post'>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Dodaj Zlecenie:</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label>Data przyjęcia do naprawy:</label>
            <input type='date' class="form-control" name='serviceAccept' value='${order.serviceAccept}' required>
        </div>
        <div class="form-group">
            <label>Planowana data rozpoczęcia naprawy:</label>
            <input type='date' class="form-control" name='servicePlan' value='${order.servicePlan}'>
        </div>
        <div class="form-group">
            <label>Dotyczy pojazdu:</label>
            <select name='vehicle' class="form-control" id="vehicle" required>
                <option value="">Wybierz z listy:</option>
                <c:forEach items="${vehicles}" var="vehicle">
                    <option <c:if test="${vehicle.id == order.vehicle.id}">selected</c:if> value="${vehicle.id}">
                            ${vehicle.model} ${vehicle.make} ${vehicle.registration}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Właściciel pojazdu:</label>
            <input type='text' class="form-control" id='vehicleOwner' readonly  value='${order.vehicle.client.firstName} ${order.vehicle.client.lastName}'>
        </div>
        <div class="form-group">
            <label>Pracownik odpowiedzialny:</label>
            <select name='employee' class="form-control" id="employee" class="costs-counting">
                <option value="">Wybierz z listy:</option>
                <c:forEach items="${employees}" var="employee">
                    <option <c:if test="${employee.id == order.employee.id}">selected</c:if> value="${employee.id}" data-hourly-rate="${employee.hourly_rate}">
                            ${employee.firstName} ${employee.lastName} (stawka: ${employee.hourly_rate} zł/h)
                    </option>
                </c:forEach>
            </select><br>
        </div>
        <div class="form-group">
            <label>Opis problemu:</label>
            <input type='text' class="form-control" name='issueDesc' value='${order.issueDesc}' required>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>
