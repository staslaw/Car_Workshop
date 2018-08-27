<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/fragments/header.jsp"/>


<section id="breadcrumb">
    <div class="container">
        <ol class="breadcrumb">
            <li class="active">Dashboard</li>
        </ol>
    </div>
</section>

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="WEB-INF/fragments/addelements.jsp"/>
            </div>
            <div class="col-md-9">
                <!-- Website Overview -->
                <div class="panel panel-default">
                    <div class="panel-heading main-color-bg">
                        <h3 class="panel-title">Website Overview</h3>
                    </div>
                    <div class="panel-body">
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-wrench"></i> ${stats["ordersInRepairCount"]}</h2>
                                <h4>Naprawy w trakcie</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-hourglass"></i> ${stats["ordersAwaitedCount"]}</h2>
                                <h4>Oczekujące zlecenia</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-cancel-outline"></i> ${stats["ordersCanceled"]}</h2>
                                <h4>Rezygnacje klientów</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-cab"></i> ${stats["ordersEndedCount"]}</h2>
                                <h4>Gotowe do odbioru</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- All Orders -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-9">
                                <h3 class="filtred-orders">
                                    Lista zleceń
                                </h3>
                                <p class="sort-chosed-employee" <c:if test="${!empty chosedEmployee}">style="display: block;"</c:if>>
                                    pracownika:
                                    <span>
                                        <c:if test="${!empty chosedEmployee}"> ${chosedEmployee.firstName} ${chosedEmployee.lastName}</c:if>
                                    </span>
                                </p>
                                <p class="sort-chosed-vehicle" <c:if test="${!empty chosedVehicle}">style="display: block;"</c:if>>
                                    dotycząca pojazdu:
                                    <span>
                                        <c:if test="${!empty chosedVehicle}"> ${chosedVehicle.model} ${chosedVehicle.make} ${chosedVehicle.registration}</c:if>
                                    </span>
                                </p>
                            </div>
                            <div class="col-md-3">
                                <label>Pracownik:</label>
                                <select class="form-control employee-list name-only" name='employee' id="employees" data-vehicle-id="${chosedVehicle.id}">
                                    <option value="" >Wybierz pracownika</option>
                                    <%--<c:forEach items="${employees}" var="employee">--%>
                                        <%--<option value="${employee.id}">${employee.firstName} ${employee.lastName}</option>--%>
                                    <%--</c:forEach>--%>
                                </select>
                                <p id="ajax-info" style="color:red"></p>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Data przyjęcia</th>
                                <th>Klient</th>
                                <th>Pojazd</th>
                                <th>Pracownik</th>
                                <th>Status</th>
                                <th>Koszt</th>
                                <th>Szczegóły</th>
                                <th>Edycja</th>
                                <th>Usuwanie</th>
                            </tr>
                            </thead>
                            <tbody id="orders-list">
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>${order.serviceAccept}</td>
                                    <td>${order.vehicle.client.firstName}&nbsp${order.vehicle.client.lastName}</td>
                                    <td>${order.vehicle.model} ${order.vehicle.make}</td>
                                    <td>${order.employee.firstName}&nbsp;${order.employee.lastName}</td>
                                    <td>${order.status.name}</td>
                                    <td>
                                        <c:if test="${order.repairCost != 0}">
                                            <c:set var = "repairCost" value ="${order.repairCost}"/><fmt:formatNumber value = "${repairCost}" type="number" groupingUsed = "false" minFractionDigits="2" maxFractionDigits="2" />
                                        </c:if>
                                    </td>
                                    <td><a class="btn btn-default" href="/order/details?id=${order.id}"><i class="icon-info-circled-alt"></i> szczegóły</a></td>
                                    <td><a class="btn btn-primary" href="/order/update?id=${order.id}"><i class="icon-cog"></i> edytuj</a></td>
                                    <td><a class="btn btn-danger" href="#"><i class="icon-cancel-circled"></i> usuń</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Modals -->

<!-- Add Client/Vehicle/Employee/Order in Footer-->

<jsp:include page="WEB-INF/fragments/footer.jsp"/>
