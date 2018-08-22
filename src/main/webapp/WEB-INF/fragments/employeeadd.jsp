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

<form action='/EmployeeUpdate' method='post'>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Dodaj Zlecenie:</h4>
    </div>
    <div class="modal-body">
        <div class="form-group" style="display: none">
            <label>id</label>
            <input type='text' class="form-control" name='id' value='0' hidden>
        </div>
        <div class="form-group">
            <label>Imię:</label>
            <input type='text' class="form-control" name='firstName' value='' required>
        </div>
        <div class="form-group">
            <label>Nazwisko: </label>
            <input type='text' class="form-control" name='lastName' value='' required>
        </div>
        <div class="form-group">
            <label>Adres:</label>
            <input type='text' class="form-control" name='address' value='' required>
        </div>
        <div class="form-group">
            <label>Telefon:</label>
            <input type='tel' class="form-control" name='phone' value='' maxlength="15" placeholder="600500400" pattern="[0-9]{9,}" required>
        </div>
        <div class="form-group">
            <label>Dodatkowe informacje:</label>
            <input type='text' class="form-control" name='note' value='' required>
        </div>
        <div class="form-group">
            <label>Stawka godzinowa</label>
            <input type='number' class="form-control" name='hourly_rate' value='' min='10' max='400' step='1' required>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>
