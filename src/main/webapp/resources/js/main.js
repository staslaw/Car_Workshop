$(document).ready(function() {

    var employeesFromBase;
    var vehiclesFromBase;
    var clientsFromBase;

    var clientsSize;
    var employeesSize;
    var vehiclesSize;

    $.ajax({
        url : '/GetAllElements',
        dataType : "json",
        success : function(result) {

            clientsFromBase = result[0];
            employeesFromBase = result[1];
            vehiclesFromBase = result[2];

            var employeeSelectList = $(".employee-list");
            var vehicleSelectList = $(".vehicle-list")
            var clientSelectList = $(".client-list");

            clientsSize = clientsFromBase.length;
            employeesSize = employeesFromBase.length;
            vehiclesSize = vehiclesFromBase.length;

            $(".clients-size").append(clientsSize);
            $(".employees-size").append(employeesSize);
            $(".vehicles-size").append(vehiclesSize);

            employeesFromBase.forEach(function (employee) {
                var option = $("<option>").attr("value",employee.id);
                var employeeInfo = employee.firstName + " " + employee.lastName;

                if(!employeeSelectList.hasClass("name-only")){
                    employeeInfo = employee.firstName + " " + employee.lastName + " (stawka: " + employee.hourly_rate + " zł/h)";
                }

                option.append(employeeInfo);
                employeeSelectList.append(option);
            })

            vehiclesFromBase.forEach(function (vehicle) {
                var option = $("<option>").attr("value",vehicle.id);
                var vehicleInfo = vehicle.model + " " + vehicle.make + " " + vehicle.registration;
                option.append(vehicleInfo);
                vehicleSelectList.append(option);
            })

            clientsFromBase.forEach(function (client) {
                var option = $("<option>").attr("value",client.id);
                var clientInfo = client.firstName + " " + client.lastName;
                option.append(clientInfo);
                clientSelectList.append(option);
            })

        },
        error: function(data){
            // console.log("error")
        }
    });

    var vehicleSelect = $('.vehicle-select');

    vehicleSelect.on("change",function() {

        var selectedVehicleId = $(this).find(':selected').val();
        var foundVehicleOwner = false;

        for (var i = 0; i < vehiclesFromBase.length ; i++) {

            // console.log("przeszukuje samochody")

            if(vehiclesFromBase[i].id == selectedVehicleId) {

                $(this).closest('.modal-body').find('.vehicle-owner').val(vehiclesFromBase[i].client.firstName + " " + vehiclesFromBase[i].client.lastName);
                foundVehicleOwner = true;
                break
            }
        }

        if(!foundVehicleOwner) {
            $(this).closest('.modal-body').find('.vehicle-owner').val("Brak przypisanego właściciela pojazdu");
        }



        // $.ajax({
        //     url : '/GetVehicle',
        //     data : {
        //         vehicleId : $(this).find(':selected').val()
        //     },
        //     dataType : "json",
        //     statusCode: {
        //         500: function() {
        //             self.closest('.modal-body').find('.vehicle-owner').val("Coś nie tak");
        //         }
        //     },
        //     success : function(result) {
        //         var od = result ;
        //         var odString = JSON.stringify(result) ;
        //         console.log(odString);
        //         console.log(self);
        //         self.closest('.modal-body').find('.vehicle-owner').val(od.client.firstName + " " + od.client.lastName);
        //     },
        //     error: function(data){
        //         self.closest('.modal-body').find('.vehicle-owner').val("Brak połączenia z bazą danych");
        //     }
        // });
    });

    $('#employee').on("change",function() {
        $('#hourly-rate').val($(this).find(':selected').data('hourly-rate'));
    });

    var costsCounting = $("form").find(".costs-counting");

    costsCounting.on("change",function() {
        var hourlyRate = Number($("#employee").find(':selected').data('hourly-rate'));
        var partsCost = Number($("#parts-cost").val());
        var manHours = Number($("#man-hours").val());

        var result = (hourlyRate * manHours) + partsCost;

        result = (Math.round(result*100)/100).toFixed(2);

        $("#total-cost").val(result);

    });

    var ordersList = $('#orders-list').html();
    // var header = $('.filtred-orders');
    // if(header.length !=0) {
    //     var editedHeader = header.html();
    //     var n = header.html().lastIndexOf('pracownika');
    //     if(n >= 0) {
    //         editedHeader = header.substring(0,n)
    //     }
    //
    // }


    $('#employees').on("change",function() {
        if($(this).find(':selected').val()) {

            $.ajax({
                url : '/GetOrders',
                data : {
                    employeeId : $(this).find(':selected').val(),
                    vehicleId : $(this).data('vehicle-id')
                },
                dataType : "json",
                statusCode: {
                    500: function() {
                        $('#ajax-info').html("Nie ma takiego powiązania");
                        $('#orders-list').html("");
                    }
                },
                success : function(result) {

                    if(result.length>0) {
                        var od = result ;
                        var employeeOrders = "";
                        $.each(od,function (key,value) {
                            employeeOrders += "<tr>";
                            employeeOrders += "<td>" + value.serviceAccept +"</td>";
                            employeeOrders += "<td>" + value.vehicle.client.firstName + " " + value.vehicle.client.lastName + "</td>";
                            employeeOrders += "<td>" + value.vehicle.model + " " + value.vehicle.make + "</td>";
                            employeeOrders += "<td>" + value.employee.firstName + " " + value.employee.lastName + "</td>";
                            employeeOrders += "<td>" + value.status.name + "</td>";
                            var repairCost = (value.repairCost > 0) ? value.repairCost.toFixed(2) : "";
                            repairCost = repairCost.replace(".",",")
                            employeeOrders += "<td>" + repairCost + "</td>";
                            employeeOrders += "<td><a class='btn btn-default' href='/order/details?id=" + value.id + "'><i class=\"icon-info-circled-alt\"></i> szczegóły</a></td>";
                            employeeOrders += "<td><a class='btn btn-primary' href='/order/update?id=" + value.id + "'><i class=\"icon-cog\"></i> edytuj</a></td>";
                            employeeOrders += "<td><a class='btn btn-danger' href='#'><i class=\"icon-cancel-circled\"></i> usuń</a></td>";
                            employeeOrders += "</tr>";

                        });
                        $('#orders-list').html(employeeOrders);
                        $(".sort-chosed-employee").show().find("span").text(od[0].employee.firstName + " " + od[0].employee.lastName);
                        $('#ajax-info').html("");
                    } else {
                        $('#ajax-info').html("Nie ma takiego powiązania");
                        $('#orders-list').html("");
                        header.html(editedHeader);
                    }

                },
                error: function(data){
                    $('#ajax-info').html("Błąd połączenia do bazy danych");
                }
            });
        } else {
            $('#orders-list').html(ordersList);
            // header.html(editedHeader);
            $('#ajax-info').html("");
            $(".sort-chosed-employee").hide().find("span").text("");
        }
    });

    var clientsList = $('#clients-list').html();

    $('#clients').on("change",function() {
        if($(this).find(':selected').val()) {
            
            var selectedClientId = $(this).find(':selected').val();

            var clientVehicles = "";

            vehiclesFromBase.forEach(function (vehicle) {

                if(vehicle.client.id == selectedClientId) {

                    $(".sort-chosed-client").show().find("span").text(vehicle.client.firstName + " " + vehicle.client.lastName);

                    // console.log("znalazłem samochód klienta");

                    clientVehicles += "<tr>";
                    clientVehicles += "<td>" + vehicle.id +"</td>";
                    clientVehicles += "<td>" + vehicle.model +"</td>";
                    clientVehicles += "<td>" + vehicle.make + "</td>";
                    clientVehicles += "<td>" + vehicle.productionDate + "</td>";
                    clientVehicles += "<td>" + vehicle.registration + "</td>";
                    clientVehicles += "<td>" + vehicle.nextService + "</td>";
                    clientVehicles += "<td>" + vehicle.client.firstName + " " + vehicle.client.lastName + "</td>";
                    clientVehicles += "<td><a class='btn btn-default' href='/orders/vehicle?id=" + vehicle.id + "'><i class=\"icon-info-circled-alt\"></i> szczegóły</a></td>";
                    clientVehicles += "<td><a class='btn btn-primary' href='/updateVehicle?id=" + vehicle.id + "'><i class=\"icon-cog\"></i> edytuj</a></td>";
                    clientVehicles += "<td><a class='btn btn-danger' href='/deleteVehicle?id=" + vehicle.id + "'><i class=\"icon-cancel-circled\"></i> usuń</a></td>";
                    clientVehicles += "</tr>";
                }

            })

            $('#ajax-info').html("");
            $('#clients-list').html(clientVehicles);

        } else {
            $('#clients-list').html(clientsList);
            // header.html(editedHeader);
            $('#ajax-info').html("");
            $(".sort-chosed-employee").hide().find("span").text("");
        }
    });

    // zaznaczanie w menu obecnie otwartej zakładki

    var navbarMenu = $('.navbar');
    navbarMenu.find('li.active').removeClass('active');
    navbarMenu.find('a[href="' + location.pathname + '"]').closest('li').addClass('active');

    // po walidacji po stronie serwera formularza do edycji zlecen ponowne otwarcie formularza z dodanymi przez serwer komunikatami

    var formValid = $('.formValid.editor-add');
    // console.log(formValid);
    // console.log(formValid.data('is-valid'));


    if(formValid.data('is-valid') == '0') {

        console.log("formularz nie zwalidowany");
        var formToInvolve = formValid.closest(".modal")[0].id;

        // console.log(formToInvolve)

        var elementToInvole = $(".add-elements").find("[data-target='#" + formToInvolve + "']");
        // console.log(elementToInvole);

        elementToInvole.click();

    }

    // przycisk anuluj w formularzu czysci dane i komunikaty z serwera,

    $('button.btn.btn-default').on("click",function () {

        if(formValid.data('is-valid') !== undefined) {

            $('.modal.fade').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(e) {

                if(formValid.data('location') !== "") {
                    var url = formValid.data("location");
                } else {
                    var url = "/";
                }

                window.location.href = url;
                return false;

                // $(this).closest('form').find("input[type=text], input[type=date] textarea, select").val("");
                //
                // var toClear = $(this).closest('form').find('.modal-header').find('.formValid');
                // toClear.attr("data-is-valid", "1")
                // toClear.find("ul").empty();
            });
        }
    })
});