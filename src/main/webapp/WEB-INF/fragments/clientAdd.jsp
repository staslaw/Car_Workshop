<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 16.08.18
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action='/ClientUpdate' method='post'>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Dodaj Klienta:</h4>
    </div>
    <div class="modal-body">
        <div class="form-group" style="display: none;">
            <label>Id klienta:</label>
            <input type='text' class="form-control" name='id' value='0' readonly>
        </div>
        <div class="form-group">
            <label>Imię:</label>
            <input type='text' class="form-control" placeholder="Jan" name='firstName' value='' required>
        </div>
        <div class="form-group">
            <lable>Nazwisko:</lable>
            <input type='text' name='lastName' class="form-control" placeholder="Kowalski" value='' required>
        </div>
        <div class="form-group">
            <lable>Adres e-mail:</lable>
            <input type='email' name='email' class="form-control" placeholder="jankowalski@com.pl" value='' required>
        </div>
        <div class="form-group">
            <lable>Telefon kontaktowy:</lable>
            <input type='tel' name='phone' class="form-control" placeholder="600500400" maxlength="15" pattern="[0-9]{9,}" required>
        </div>
        <div class="form-group">
            <lable>Data urodzin:</lable>
            <input type='text' class="form-control" onfocus="(this.type='date')" onblur="(this.type='text')" placeholder="01-01-1999" name='birthday' value='' min='1900-01-01' required>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </div>
</form>