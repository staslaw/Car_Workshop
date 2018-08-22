<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 14.08.18
  Time: 16:20
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
                <form action="" method="post">
                    <fieldset>
                        <legend>
                            Wyszukiwanie klienta po nazwisku:
                        </legend>
                        <input type="text" name="find" required>
                        <input type="submit" value="Szukaj">
                    </fieldset>
                </form>
                <p style="color:red">${findInfo}</p>
                <h2>Lista klientów<c:if test="${!empty chosedClient}"> [szukaj: '${chosedClient}']</c:if></h2>
                <table class="table table-striped table-hover">
                    <tr>
                        <th>id</th>
                        <th>imię</th>
                        <th>nazwisko</th>
                        <th>email</th>
                        <th>nr tel.</th>
                        <th>data ur.</th>
                        <th>samochody</th>
                        <th>edytuj</th>
                        <th>usuń</th>
                    </tr>
                    <c:forEach var="element" items="${clientList}">
                        <tr>
                            <td>${element.id}</td>
                            <td>${element.firstName}</td>
                            <td>${element.lastName}</td>
                            <td>${element.email}</td>
                            <td>${element.phone}</td>
                            <td>${element.birthday}</td>
                            <td>samochody</td>
                            <td><a class="btn btn-primary" href="/ClientUpdate?id=${element.id}"><i class="icon-cog"></i> edytuj</a></td>
                            <td><a class="btn btn-danger" href="/ClientDelete?id=${element.id}"><i class="icon-cancel-circled"></i> usuń</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>