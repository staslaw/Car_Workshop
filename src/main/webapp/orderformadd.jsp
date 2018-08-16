<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: kamil
  Date: 16.08.18
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#vehicle').on("change",function() {
                if($(this).find(':selected').val()) {

                    $.ajax({
                        url : '/GetVehicle',
                        data : {
                            vehicleId : $('#vehicle').val()
                        },
                        dataType : "json",
                        statusCode: {
                            500: function() {
                                $('#vehicleOwner').val("Brak pojazdu o podanym id");
                            }
                        },
                        success : function(result) {
                            var od = result ;
                            var odString = JSON.stringify(result) ;
                            console.log(odString);
                            $('#vehicleOwner').val(od.client.firstName + " " + od.client.lastName);
                        },
                        error: function(data){
                            $('#vehicleOwner').val("Brak połączenia z bazą danych");
                        }
                    });
                } else {
                    $('#vehicleOwner').val("");
                }
            });
        });

    </script>
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<p style="color:red">${formInfo}</p>
<form action='' method='post'>
    Data przyjęcia do naprawy:
    <input type='date' name='serviceAccept' value='${serviceAccept}' required><br>
    Planowana data rozpoczęcia naprawy:
    <input type='date' name='servicePlan' value='${servicePlan}' ><br>
    Pojazd:
    <select name='vehicle' id="vehicle" required>
        <option value="">Wybierz samochód:</option>
        <c:forEach items="${vehicles}" var="vehicle">
            <option <c:if test="${vehicle.id == chosedVehicle.id}">selected</c:if> value="${vehicle.id}">
                    ${vehicle.model} ${vehicle.make} ${vehicle.registration}
            </option>
        </c:forEach>
    </select><br>
    Właściciel pojazdu:
    <input type='text' id='vehicleOwner' readonly  value='${chosedVehicle.client.firstName} ${chosedVehicle.client.lastName}'><br>
    Pracownik:
    <select name='employee' id="employee" class="costs-counting" required>
        <c:forEach items="${employees}" var="employee">
            <option <c:if test="${employee.id == chosedEmployee.id}">selected</c:if> value="${employee.id}" data-hourly-rate="${employee.hourly_rate}">
                    ${employee.firstName} ${employee.lastName} (stawka: ${employee.hourly_rate} zł/h)
            </option>
        </c:forEach>
    </select><br>
    Opis problemu:
    <input type='text' name='issueDesc' value='${issueDesc}' required><br>
    <input type='submit' value='zapisz'>
</form>
<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>
