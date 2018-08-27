<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 16.08.18
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>


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
                <jsp:include page="addelements.jsp"/>
            </div>
            <div class="col-md-9">
                <!-- Edit Order Form -->
                <div class="panel panel-default">
                    <div class="panel-heading main-color-bg">
                        <h3 class="panel-title">Edycja</h3>
                    </div>
                    <div class="panel-body">
                        <c:choose>

                            <c:when test = "${'/order/update'.equals(servletPath)}">
                                <jsp:include page="orderedit.jsp"/>
                            </c:when>

                            <c:when test = "${'/client/update'.equals(servletPath)}">
                                <jsp:include page="clientedit.jsp"/>
                            </c:when>

                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Modals -->

<!-- Add Client/Vehicle/Employee/Order in Footer-->

<jsp:include page="footer.jsp"/>
