package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.StatusDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Status;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderUpdate", urlPatterns = {"/order/update","/order/add"})
public class OrderUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String orderIdParam = request.getParameter("orderId");
        String vehicleIdParam = request.getParameter("vehicle");
        String serviceAccept = request.getParameter("serviceAccept");
        String servicePlan = request.getParameter("servicePlan");
        String serviceStart = request.getParameter("serviceStart");
        String employeeIdParam = request.getParameter("employee");
        String issueDesc = request.getParameter("issueDesc");
        String repairDesc = request.getParameter("repairDesc");
        String partsCostParam = request.getParameter("partsCost");
        String manHoursParam = request.getParameter("manHours");
        String statusIdParam = request.getParameter("status");


        // Zakładam, że status 'Przyjęty' jest pod id 1.
        int statusId = 1;

        if("/order/update".equalsIgnoreCase(servletPath)) {

            int orderId = Integer.valueOf(orderIdParam);

            Order order = OrderDao.loadById(orderId);
            order.setRepairDesc(repairDesc);
            order.setStatus(StatusDao.loadById(Integer.valueOf(statusIdParam)));

            List<String> formInfo = isValid(vehicleIdParam, serviceAccept, servicePlan, employeeIdParam, issueDesc, order,"update");

            boolean editOrdersValid = true;
            boolean serviceStartValid = true;
            boolean partsCostValidated = true;
            boolean manHoursValidated = true;
            boolean totalCostValidated = true;

            Double partsCost;
            int manHours;


            if(partsCostParam == null || (!isNumber(partsCostParam) && !partsCostParam.isEmpty())) {
                partsCostValidated = false;
                formInfo.add("Podane koszty wykorzystanych części nie są liczbą.");

            } else if (partsCostParam != null && isNumber(partsCostParam) && !partsCostParam.isEmpty()) {
                partsCost = Double.parseDouble(partsCostParam);
                order.setPartsCost(partsCost);
            } else if (partsCostParam.isEmpty()) {
                partsCost = 0.0;
                order.setPartsCost(partsCost);
            }

            if(manHoursParam == null || (!isNumber(manHoursParam) && !manHoursParam.isEmpty())){
                manHoursValidated = false;
                manHours = 0;
                order.setManHours(manHours);
                formInfo.add("Podana ilość roboczogodzin nie jest liczbą.");

            } else if(manHoursParam != null && isNumber(manHoursParam) && !manHoursParam.isEmpty()) {
                manHours = Integer.valueOf(manHoursParam);
                order.setManHours(manHours);
            } else if(manHoursParam.isEmpty()) {
                manHours = 0;
                order.setManHours(manHours);
            }

            String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

            if(compareDate(serviceAccept,serviceStart) == 1 || (!serviceStart.isEmpty() && compareDate(serviceStart,todayDate) == -100)) {
                serviceStartValid = false;
                formInfo.add("Nieprawidłowa data rozpoczęcia naprawy: data nie może być wcześniejsza niż data przyjęcia zlecenia.");

            } else if (serviceStart.isEmpty()) {
                order.setServiceStart(null);

            } else {
                order.setServiceStart(serviceStart);
            }

            if(!partsCostValidated || !manHoursValidated || !serviceStartValid) {
                editOrdersValid = false;
            }

            if(editOrdersValid) {

                BigDecimal hourlyRateBG = new BigDecimal(String.valueOf(order.getHourlyRate()));
                BigDecimal manHoursBG = new BigDecimal(String.valueOf(order.getManHours()));
                BigDecimal partsCostBG = new BigDecimal(String.valueOf(order.getPartsCost()));
                BigDecimal totalcost = hourlyRateBG.multiply(manHoursBG).add(partsCostBG);
                totalcost = totalcost.setScale(2, RoundingMode.CEILING);

                Double totalCostDouble = totalcost.doubleValue();

                if (totalCostDouble > 99999.99) {
                    Order orderBack = OrderDao.loadById(orderId);
                    formInfo.add("Chyba trochę przesadziłeś z kosztami?");
                    order.setPartsCost(orderBack.getRepairCost());
                    order.setManHours(orderBack.getManHours());
                    totalCostValidated= false;
                } else {
                    order.setRepairCost(totalCostDouble);

                }
            }

            if(formInfo.size() == 0 && editOrdersValid && totalCostValidated) {
                OrderDao.save(order);
                response.sendRedirect("/orders");
            } else {
                backtoFormWithInfo(request, response, order, formInfo, "/orderform.jsp");
            }
        }

        if("/order/add".equalsIgnoreCase(servletPath)) {

            Order order = new Order();

            List<String> formInfo = isValid(vehicleIdParam, serviceAccept, servicePlan, employeeIdParam, issueDesc, order, "add");

            if(formInfo.size()==0){
                order.setStatus(StatusDao.loadById(statusId));
                OrderDao.save(order);
                response.sendRedirect("/orders");
            } else {
                backtoFormWithInfo(request, response, order, formInfo,"/orderformadd.jsp");
            }
        }
    }

    private List<String> isValid(String vehicleIdParam, String serviceAccept, String servicePlan,
                                 String employeeIdParam, String issueDesc, Order order, String method)
            throws ServletException, IOException {

        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        List<String> formInfo = new ArrayList<>();
        boolean serviceAcceptValidated = true;
        boolean servicePlanValidated = true;
        boolean employeeValidated = true;
        boolean vehicleValidated = true;
        boolean issuDescValidated = true;


        if("update".equals(method)) {

            if(serviceAccept == null || serviceAccept.isEmpty() || compareDate(serviceAccept,todayDate) == -100) {
                serviceAcceptValidated = false;
                formInfo.add("Nieprawidłowa data przyjęcia zlecenia: data musi być podana.");
            } else {
                order.setServiceAccept(serviceAccept);
            }

        } else {

            if(serviceAccept == null || serviceAccept.isEmpty() || compareDate(serviceAccept,todayDate) == -1 || compareDate(serviceAccept,todayDate) == -100) {
                serviceAcceptValidated = false;
                formInfo.add("Nieprawidłowa data przyjęcia zlecenia: data musi być podana i nie może być przeszła.");
            } else {
                order.setServiceAccept(serviceAccept);
            }
        }

        if(compareDate(serviceAccept,servicePlan) == 1 || (!servicePlan.isEmpty() && compareDate(servicePlan,todayDate) == -100)) {
            servicePlanValidated = false;
            formInfo.add("Nieprawidłowa planowana data rozpoczęcia naprawy: data nie może być wcześniejsza niż data przyjęcia zlecenia.");

        } else if (servicePlan.isEmpty()) {
            order.setServicePlan(null);

        } else {
            order.setServicePlan(servicePlan);
        }

        if(employeeIdParam == null || employeeIdParam.isEmpty() || EmployeeDao.loadById(Integer.parseInt(employeeIdParam)) == null) {
            employeeValidated = false;
            formInfo.add("Nieprawidłowy id Pracownika.");
        } else {
            order.setEmployee(EmployeeDao.loadById(Integer.valueOf(employeeIdParam)));
            order.setHourlyRate(order.getEmployee().getHourly_rate());
        }

        if(vehicleIdParam == null || vehicleIdParam.isEmpty()) {
            vehicleValidated = false;
            formInfo.add("Nieprawidłowe id Pojazdu.");
        } else {
            order.setVehicle(VehicleDao.loadById(Integer.valueOf(vehicleIdParam)));
        }

        if(issueDesc == null || issueDesc.isEmpty() || "".equals(issueDesc)) {
            issuDescValidated = false;
            formInfo.add("Brak opisu usterki.");
        } else {
            order.setIssueDesc(issueDesc);
        }

        if(!serviceAcceptValidated || !servicePlanValidated || !employeeValidated || !vehicleValidated || !issuDescValidated) {
            return formInfo;
        } else {
            return formInfo;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        List<Vehicle> vehicles = VehicleDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);


        if("/order/add".equalsIgnoreCase(servletPath)) {
            getServletContext().getRequestDispatcher("/orderformadd.jsp").forward(request, response);
        }

        if("/order/update".equalsIgnoreCase(servletPath)) {

            String idParam = request.getParameter("id");

            int id = Integer.valueOf(idParam);

            Order order = OrderDao.loadById(id);

            List<Status> statuses = StatusDao.loadAll();

            request.setAttribute("order",order);
            request.setAttribute("statuses", statuses);


            getServletContext().getRequestDispatcher("/orderform.jsp").forward(request, response);
        }
    }

    private void backtoFormWithInfo(HttpServletRequest request, HttpServletResponse response, Order order, List<String> formInfo, String path) throws ServletException, IOException {
        List<Vehicle> vehicles = VehicleDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        List<Status> statuses = StatusDao.loadAll();

        request.setAttribute("formInfo",formInfo);
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);
        request.setAttribute("order",order);
        request.setAttribute("statuses", statuses);
        getServletContext().getRequestDispatcher(path).forward(request, response);
    }

    private Integer compareDate(String date1, String date2) {
        Date enteredDate1;
        Date enteredDate2;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            enteredDate1 = sdf.parse(date1);
            enteredDate2 = sdf.parse(date2);
        }catch (Exception e) {
            return -100;
        }

        if(enteredDate1.after(enteredDate2)){
            return 1;
        }else if (enteredDate1.equals(enteredDate2)) {
            return 0;
        } else {
            return -1;
        }
    }

    private boolean isNumber(String numberParam) {

        try {
            double d = Double.parseDouble(numberParam);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;

    }
}
