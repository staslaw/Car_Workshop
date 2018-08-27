<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 16.08.18
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/addVehicle" method="post">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Dodaj Pojazd:</h4>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <label>Marka:</label>
            <input class="form-control" type="text" name="make" required>
        </div>
        <div class="form-group">
            <label>Model:</label>
            <input class="form-control" type="text" name="model" required>
        </div>
        <div class="form-group">
            <label>Data produkcji:</label>
            <input class="form-control" type="date" name="productionDate" required>
        </div>
        <div class="form-group">
            <label>Rejestracja:</label>
            <input class="form-control" type="text" name="registration" required>
        </div>
        <div class="form-group">
            <label>Data kolejnego przeglądu:</label>
            <input class="form-control" type="date" name="nextService" required>
        </div>
        <div class=form-groupr">
            <label>Właściciel pojazdu:</label>
            <select class="form-control client-list" name='clientId' required>
                <option value="" ></option>
            </select>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>