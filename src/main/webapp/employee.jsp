<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.08.18
  Time: 17:28
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-md-8">
                                <h3>Lista pracowników</h3>
                                <p style="color:red">${findInfo}</p>
                                <p class="sort-chosed-employee" <c:if test="${!empty chosedEmployee}">style="display: block;"</c:if>>
                                    <c:if test="${!empty chosedEmployee}"> [szukaj: '${chosedEmployee}']</c:if>
                                </p>
                            </div>
                            <div class="col-md-4">
                                <form action="" method="post">
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label>Wyszukiwanie pracownika po nazwisku:</label>
                                            </div>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" name="find" required>
                                            </div>
                                            <div class="col-md-4">
                                                <input type="submit" class="btn btn-default" value="Szukaj">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-hover">
                            <tr>
                                <th>id</th>
                                <th>imię</th>
                                <th>nazwisko</th>
                                <th>adres</th>
                                <th>nr tel.</th>
                                <th>note</th>
                                <th>stawka</th>
                                <th>zlecenia</th>
                                <th>edytuj</th>
                                <th>usuń</th>
                            </tr>
                            <c:forEach var="element" items="${employeeList}">
                                <tr>
                                    <td>${element.id}</td>
                                    <td>${element.firstName}</td>
                                    <td>${element.lastName}</td>
                                    <td>${element.address}</td>
                                    <td>${element.phone}</td>
                                    <td>${element.note}</td>
                                    <td>${element.hourly_rate}</td>
                                    <td><a class="btn btn-default" href="/orders/employee?id=${element.id}"><i class="icon-info-circled-alt"></i> Sprawdź zlecenia</a> </td>
                                    <td><a class="btn btn-primary" href="/EmployeeUpdate?id=${element.id}"><i class="icon-cog"></i> edytuj</a></td>
                                    <td><a class="btn btn-danger" href="/EmployeeDelete?id=${element.id}"><i class="icon-cancel-circled"></i> usuń</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


<jsp:include page="WEB-INF/fragments/footer.jsp"/>