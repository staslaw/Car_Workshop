<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <h2></h2>
                                <h4>Info 1</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2></h2>
                                <h4>Info 2</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2></h2>
                                <h4>Info 3</h4>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="well dash-box">
                                <h2></h2>
                                <h4>Info 4</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- All Clients -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-9">
                                <h3 class="filtred-vehicles">
                                    Lista pojazdów
                                </h3>
                                <p class="sort-chosed-client" <c:if test="${!empty chosedClient}">style="display: block;"</c:if>>
                                    klienta:
                                    <span>
                                        <c:if test="${!empty chosedClient}"> ${chosedClient.firstName} ${chosedClient.lastName}</c:if>
                                    </span>
                                </p>
                            </div>
                            <div class="col-md-3">
                                <label>Klient:</label>
                                <select class="form-control client-list" name='client' id="clients">
                                    <option value="" >Wybierz klienta</option>
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
                                    <th>id</th>
                                    <th>Model</th>
                                    <th>Make</th>
                                    <th>Production date</th>
                                    <th>Registration</th>
                                    <th>Next service</th>
                                    <th>Client name</th>
                                    <th>Repairs history</th>
                                    <th>Edit</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody id="clients-list">
                                <c:forEach var="element" items="${vehicleList}">
                                    <tr>
                                        <td>${element.id}</td>
                                        <td>${element.model}</td>
                                        <td>${element.make}</td>
                                        <td>${element.productionDate}</td>
                                        <td>${element.registration}</td>
                                        <td>${element.nextService}</td>
                                        <td>${element.client.firstName}&nbsp;${element.client.lastName}</td>
                                        <td><a class="btn btn-default" href="/orders/vehicle?id=${element.id}"><i class="icon-info-circled-alt"></i> Check History</a></td>
                                        <td><a class="btn btn-primary" href="/updateVehicle?id=${element.id}"><i class="icon-cog"></i> Update</a></td>
                                        <td><a class="btn btn-danger" href="/deleteVehicle?id=${element.id}"><i class="icon-cancel-circled"></i> Delete</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
=======
            </div>




            <%--<div class="col-md-12">--%>
                <%--<h2>Lista pojazdów:</h2>--%>

                <%--&lt;%&ndash;do poprawy wyszukiwanie po nazwisku klienta&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form action="/showAllVehicles" method="post">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<fieldset>Klient: <input type="text" name="find" >&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</fieldset>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>

            <%--</div>--%>
        </div>
    </div>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
