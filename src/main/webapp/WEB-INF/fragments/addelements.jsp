<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 18.08.18
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="list-group add-elements">
    <a class="list-group-item active main-color-bg">
        <icon class="icon-cog"></icon> Dashboard
    </a>
    <a type="button" data-toggle="modal" data-target="#addClient" class="list-group-item">
        <i class="icon-plus"></i> Dodaj klienta<span class="badge"><i class="icon-user"></i></span>
    </a>
    <a type="button" data-toggle="modal" data-target="#addPage" class="list-group-item">
        <i class="icon-plus"></i> Dodaj pracownika <span class="badge"><icon class="icon-user-secret"></icon></span>
    </a>
    <a type="button" data-toggle="modal" data-target="#addPage" class="list-group-item">
        <i class="icon-plus"></i> Dodaj pojazd <span class="badge"><icon class="icon-cab"></icon></span>
    </a>
    <a type="button" data-toggle="modal" data-target="#addOrder" class="list-group-item">
        <i class="icon-plus"></i> Dodaj zlecenie <span class="badge"><icon class="icon-wrench"></icon></span>
    </a>
</div>
