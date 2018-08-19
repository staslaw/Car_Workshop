$(document).ready(function() {
    console.log("hello !")

    $('#vehicle').on("change",function() {
        $.ajax({
            url : '/GetVehicle',
            data : {
                vehicleId : $('#vehicle').val()
            },
            dataType : "json",
            statusCode: {
                500: function() {
                    $('#vehicleOwner').val("");
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
    });

    $('#employee').on("change",function() {
        $('#hourly-rate').val($(this).find(':selected').data('hourly-rate'));
    });

    var costsCounting = $("#order-editing").find(".costs-counting");

    costsCounting.on("change",function() {
        var hourlyRate = Number($("#employee").find(':selected').data('hourly-rate'));
        var partsCost = Number($("#parts-cost").val());
        var manHours = Number($("#man-hours").val());

        var result = (hourlyRate * manHours) + partsCost;

        result = (Math.round(result*100)/100).toFixed(2);

        $("#total-cost").val(result);

    });

    var ordersList = $('#orders-list').html();
    var header = $('h2').html();
    if(header != null) {

        var n = header.lastIndexOf('pracownika');
        if(n >= 0) {
            header = header.substring(0,n)
        }

    }


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
                            employeeOrders += "<td>" + value.id +"</td>";
                            employeeOrders += "<td>" + value.serviceAccept +"</td>";
                            employeeOrders += "<td>" + value.vehicle.client.firstName + " " + value.vehicle.client.lastName + "</td>";
                            employeeOrders += "<td>" + value.vehicle.model + " " + value.vehicle.make + "</td>";
                            employeeOrders += "<td>" + value.employee.firstName + " " + value.employee.lastName + "</td>";
                            employeeOrders += "<td>" + value.status.name + "</td>";
                            var repairCost = (value.repairCost > 0) ? value.repairCost.toFixed(2) : "";
                            repairCost = repairCost.replace(".",",")
                            employeeOrders += "<td>" + repairCost + "</td>";
                            employeeOrders += "<td><a href='/order/details?id=" + value.id + "'>szczegóły</a></td>";
                            employeeOrders += "<td><a href='/order/update?id=" + value.id + "'>edytuj</a></td>";
                            employeeOrders += "<td><a href='#'>usuń</a></td>";
                            employeeOrders += "</tr>";

                        });
                        $('#orders-list').html(employeeOrders);
                        var newHeader = header + " pracownika: " + od[0].employee.firstName + " " + od[0].employee.lastName;
                        $('h2').html(newHeader);
                        $('#ajax-info').html("");
                    } else {
                        $('#ajax-info').html("Nie ma takiego powiązania");
                        $('#orders-list').html("");
                    }

                },
                error: function(data){
                    $('#ajax-info').html("Błąd połączenia do bazy danych");
                }
            });
        } else {
            $('#orders-list').html(ordersList);
            $('h2').html(header);
            $('#ajax-info').html("");
        }
    });

    var navbarMenu = $('.navbar');
    navbarMenu.find('li.active').removeClass('active');
    navbarMenu.find('a[href="' + location.pathname + '"]').closest('li').addClass('active');

    var formValid = $('.formValid');
    console.log(formValid);
    console.log(formValid.data('is-valid'));


    if(formValid.data('is-valid') == '0') {

        console.log("formularz nie zwalidowany");
        var formToInvolve = formValid.closest(".modal")[0].id;

        console.log(formToInvolve)

        var elementToInvole = $(".add-elements").find("[data-target='#" + formToInvolve + "']");
        console.log(elementToInvole);

        elementToInvole.click();

        // $('.add-elements').find('.list-group-item').click();
    }


    $('button.btn.btn-default').on("click",function () {

        if(formValid.data('is-valid') == '0') {

            $('.modal.fade').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(e) {
                window.location.href = '/';
                return false;
            });
        }



        // logoImgWrapper.one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(e) {
        //   setMarginOnContener();
        // });




            // $(this).closest('form').find("input[type=text], input[type=date] textarea, select").val("");
            //
            // var toClear = $(this).closest('form').find('.modal-header').find('.formValid');
            // toClear.attr("data-is-valid", "1")
            // toClear.find("ul").empty();

    })


});