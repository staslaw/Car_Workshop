<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <div class="well">
                    <h4 class="stats-header">Statystyka zleceń:</h4>
                    <h5>W trakcie naprawy</h5>
                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="${stats["statsInRepair"]}" aria-valuemin="0" aria-valuemax="100" style="width: ${stats["statsInRepair"]}%;">
                            ${stats["statsInRepair"]}%
                        </div>
                    </div>
                    <h5>Zakończonych</h5>
                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="${stats["statsEnded"]}" aria-valuemin="0" aria-valuemax="100" style="width: ${stats["statsEnded"]}%;">
                            ${stats["statsEnded"]}%
                        </div>
                    </div>
                    <h5>Rezygnacja</h5>
                    <div class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="${stats["statsCancel"]}" aria-valuemin="0" aria-valuemax="100" style="width: ${stats["statsCancel"]}%;">
                            ${stats["statsCancel"]}%
                        </div>
                    </div>
                </div>
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
                                <h2><i class="icon-wrench"></i> ${stats["ordersSize"]}</h2>
                                <h4>Zlecenia</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-user"></i> ${stats["clientsSize"]}</h2>
                                <h4>Klienci</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-user-secret"></i> ${stats["employeesSize"]}</h2>
                                <h4>Pracownicy</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2><i class="icon-cab"></i> ${stats["vehiclesSize"]}</h2>
                                <h4>Pojazdy</h4>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Latest Orders -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Najnowsze zlecenia</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-hover">
                            <tr>
                                <th>Data przyjęcia zlecenia</th>
                                <th>Pracownik</th>
                                <th>Koszt Całkowity</th>
                                <th>Status</th>
                                <th>Szczegóły</th>
                            </tr>
                            <c:forEach var="order" items="${ordersLast5}">
                                <tr>
                                    <td>${order.serviceAccept}</td>
                                    <td>${order.employee.firstName}&nbsp;${order.employee.lastName}</td>
                                    <td>
                                        <c:if test="${order.repairCost != 0}">
                                            <c:set var = "repairCost" value ="${order.repairCost}"/><fmt:formatNumber value = "${repairCost}" type="number" groupingUsed = "false" minFractionDigits="2" maxFractionDigits="2" />
                                        </c:if>
                                    </td>
                                    <td>${order.status.name}</td>
                                    <td><a href="/order/details?id=${order.id}">szczegóły</a></td>
                                </tr>
                            </c:forEach>
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
