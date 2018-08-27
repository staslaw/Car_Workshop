<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="req" value="${pageContext.request}" />
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${pageTitle}</title>
    <!-- Bootstrap core CSS -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fontello.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Dashboard</a></li>
                <li><a href="/orders">Zlecenia</a></li>
                <li><a href="/clients">Klienci</a></li>
                <li><a href="/Employee">Pracownicy</a></li>
                <li><a href="/showAllVehicles">Pojazdy</a></li>
                <li><a href="/Raport1">Raport1</a></li>
                <li><a href="/Raport2">Raport2</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Welcome, Brad</a></li>
                <li><a href="login.html">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<header id="header">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <h1><icon class="icon-cog"></icon> Dashboard <small>Manage Your Site</small></h1>
            </div>
        </div>
    </div>
</header>


