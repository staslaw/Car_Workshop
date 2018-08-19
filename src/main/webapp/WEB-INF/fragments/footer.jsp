<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: staszek
  Date: 15.08.18
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer id="footer">
    <p>Copyright Kamil Kozlowski Grzegorz Krawczyński Stanisław, &copy; 2018</p>
</footer>

<!-- Add Client -->
<div class="modal fade" id="addPage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
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
        </div>
    </div>
</div>

<!-- Add Order -->
<div class="modal fade" id="addOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action='/order/add' method='post'>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Dodaj Zlecenie:</h4>
                    <div class="formValid" data-is-valid="<c:if test='${fn:length(formInfo) gt 0}'>0</c:if>">
                        <ul class="valid-messages" style="color:red">
                            <c:forEach items="${formInfo}" var="info">
                                <li>${info}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Data przyjęcia do naprawy:</label>
                        <input type='date' class="form-control" name='serviceAccept' value='${order.serviceAccept}' required>
                    </div>
                    <div class="form-group">
                        <label>Planowana data rozpoczęcia naprawy:</label>
                        <input type='date' class="form-control" name='servicePlan' value='${order.servicePlan}'>
                    </div>
                    <div class="form-group">
                        <label>Dotyczy pojazdu:</label>
                        <select name='vehicle' class="form-control" id="vehicle" required>
                            <option value="">Wybierz z listy:</option>
                            <c:forEach items="${vehicles}" var="vehicle">
                                <option <c:if test="${vehicle.id == order.vehicle.id}">selected</c:if> value="${vehicle.id}">
                                        ${vehicle.model} ${vehicle.make} ${vehicle.registration}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Właściciel pojazdu:</label>
                        <input type='text' class="form-control" id='vehicleOwner' readonly  value='${order.vehicle.client.firstName} ${order.vehicle.client.lastName}'>
                    </div>
                    <div class="form-group">
                        <label>Pracownik odpowiedzialny:</label>
                        <select name='employee' class="form-control" id="employee" class="costs-counting">
                            <option value="">Wybierz z listy:</option>
                            <c:forEach items="${employees}" var="employee">
                                <option <c:if test="${employee.id == order.employee.id}">selected</c:if> value="${employee.id}" data-hourly-rate="${employee.hourly_rate}">
                                        ${employee.firstName} ${employee.lastName} (stawka: ${employee.hourly_rate} zł/h)
                                </option>
                            </c:forEach>
                        </select><br>
                    </div>
                    <div class="form-group">
                        <label>Opis problemu:</label>
                        <input type='text' class="form-control" name='issueDesc' value='${order.issueDesc}' required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
                    <button type="submit" class="btn btn-primary">Zapisz</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/resources/js/main.js'></script>
</body>
</html>
