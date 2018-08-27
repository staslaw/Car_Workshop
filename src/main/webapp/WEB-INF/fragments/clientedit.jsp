<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 25.08.18
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action='/client/update' method='post'>
    <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Edytuj Klienta:</h4>
        <div class="formValid">
            <ul class="valid-messages" style="color:red">
                <c:forEach items="${formInfoEditing}" var="info">
                    <li>${info}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="modal-body">
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Id Klienta:</label>
                </div>
                <div class="col-md-7">
                    <input type='text' name='id' class="form-control" value='${client.id}' readonly>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Imię:</label>
                </div>
                <div class="col-md-7">
                    <input type='text' name='firstName' class="form-control" value='${client.firstName}' required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Nazwisko:</label>
                </div>
                <div class="col-md-7">
                    <input type='text' name='lastName' class="form-control" value='${client.lastName}' required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>E-mail:</label>
                </div>
                <div class="col-md-7">
                    <input type='email' name='email' class="form-control" value='${client.email}' required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Telefon:</label>
                </div>
                <div class="col-md-7">
                    <input type='tel' name='phone' class="form-control" value='${client.phone}' maxlength="15" placeholder="600500400" pattern="[0-9]{9,}" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-5">
                    <label>Data urodzenia:</label>
                </div>
                <div class="col-md-7">
                    <input type='date' name='birthday' class="form-control" value='${client.birthday}' min='1900-01-01' max='2018-01-01' required>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>