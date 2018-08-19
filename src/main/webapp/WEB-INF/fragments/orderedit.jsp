<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${orderEditing.id == 0}">
    <c:set var="orderEditing" value="${orderEditingSession}"/>
</c:if>

<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 16.08.18
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action='/order/update' method='post'>
    <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Edytuj Zlecenie:</h4>
        <div class="formValid">
            <ul class="valid-messages" style="color:red">
                <c:forEach items="${formInfoEditing}" var="info">
                    <li>${info}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Id Zlecenia:</label>
                </div>
                <div class="col-md-7">
                    <input name="orderId" class="form-control" type='number' value='${orderEditing.id}' readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Dotyczy Pojazdu:</label>
                </div>
                <div class="col-md-7">
                    <select name='vehicle' class="form-control vehicle-select" required>
                        <option value="">Wybierz z listy:</option>
                        <c:forEach items="${vehicles}" var="vehicle">
                            <option <c:if test="${vehicle.id == orderEditing.vehicle.id}">selected</c:if> value="${vehicle.id}">
                                    ${vehicle.model} ${vehicle.make} ${vehicle.registration}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Właściciel pojazdu:</label></div>
                <div class="col-md-7">
                    <input type='text' class="form-control vehicle-owner" readonly
                           value='${orderEditing.vehicle.client.firstName} ${orderEditing.vehicle.client.lastName}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Data przyjęcia do naprawy:</label>
                </div>
                <div class="col-md-7">
                    <input type='date' class="form-control" name='serviceAccept' value='${orderEditing.serviceAccept}' required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Planowana data rozpoczęcia naprawy:</label></div>
                <div class="col-md-7">
                    <input type='date' class="form-control" name='servicePlan' value='${orderEditing.servicePlan}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Data rozpoczęcia naprawy:</label>
                </div>
                <div class="col-md-7">
                    <input type='date' class="form-control" name='serviceStart' value='${orderEditing.serviceStart}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Pracownik odpowiedzialny:</label></div>
                <div class="col-md-7">
                    <select name='employee' class="form-control costs-counting" id="employee">
                        <option value="">Wybierz z listy:</option>
                        <c:forEach items="${employees}" var="employee">
                            <option <c:if test="${employee.id == orderEditing.employee.id}">selected</c:if> value="${employee.id}" data-hourly-rate="${employee.hourly_rate}">
                                    ${employee.firstName} ${employee.lastName} (stawka: ${employee.hourly_rate} zł/h)
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Opis problemu:</label></div>
                <div class="col-md-7">
                    <input type='text' class="form-control" name='issueDesc' value='${orderEditing.issueDesc}' required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Opis naprawy:</label></div>
                <div class="col-md-7">
                    <input type='text' class="form-control" name='repairDesc' value='${orderEditing.repairDesc}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5 "><label>Koszt wykorzystanych części:</label></div>
                <div class="col-md-7 ">
                    <input type='number' class="form-control costs-counting" step="0.01" name='partsCost' min="0" max="99999.99"
                           value='${orderEditing.partsCost}' id="parts-cost">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Koszt roboczogodziny:</label></div>
                <div class="col-md-7">
                    <input type='number' class="form-control costs-counting" id="hourly-rate" readonly value='${orderEditing.hourlyRate}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Ilość roboczogodzin:</label></div>
                <div class="col-md-7">
                    <input type='number' class="form-control costs-counting" step="1" min="0" name="manHours"
                           value='${orderEditing.manHours}' id ="man-hours">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Status:</label></div>
                <div class="col-md-7">
                    <select class="form-control" name='status'>
                        <c:forEach items="${statuses}" var="status">
                            <option <c:if test="${orderEditing.status.id == status.id}">selected</c:if> value="${status.id}">
                                    ${status.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5"><label>Koszt całkowity:</label></div>
                <div class="col-md-7">
                    <input class="form-control" type='number' step="0.01" readonly id="total-cost" max="99999.99"
                           value='<c:if test="${orderEditing.repairCost != 0}">${repairCost}</c:if>'>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>
