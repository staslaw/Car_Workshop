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
import java.util.ArrayList;
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

        Double partsCost = 0.0;
        Integer manHours = 0;

        if(partsCostParam != null && !partsCostParam.isEmpty()) {
            partsCost = Double.parseDouble(partsCostParam);
        }

        if(manHoursParam != null && !manHoursParam.isEmpty()) {
            manHours = Integer.valueOf(manHoursParam);
        }

        if(serviceAccept.isEmpty()) {
            serviceAccept = null;
        }

        if(servicePlan.isEmpty()) {
            servicePlan = null;
        }

        if(serviceStart == null || serviceStart.isEmpty()) {
            serviceStart = null;
        }

        // Zakładam, że status 'Przyjęty' jest pod id 1.
        int statusId = 1;
        int orderId = 0;

        if(!vehicleIdParam.isEmpty() && !employeeIdParam.isEmpty() && !issueDesc.isEmpty()) {

            int vehicleId = Integer.valueOf(vehicleIdParam);
            int employeeId = Integer.valueOf(employeeIdParam);

            Order order = new Order();

            if("/order/add".equalsIgnoreCase(servletPath)) {

                serviceStart = null;
                repairDesc = null;
                partsCost = null;
                manHours = null;

            }

            if("/order/update".equalsIgnoreCase(servletPath)) {

                orderId = Integer.valueOf(orderIdParam);
                statusId = Integer.valueOf(statusIdParam);

                order = OrderDao.loadById(orderId);

            }

            order.setVehicle(VehicleDao.loadById(vehicleId));
            order.setServiceAccept(serviceAccept);
            order.setServicePlan(servicePlan);
            order.setServiceStart(serviceStart);
            order.setEmployee(EmployeeDao.loadById(employeeId));
            order.setHourlyRate(order.getEmployee().getHourly_rate());
            order.setIssueDesc(issueDesc);
            order.setRepairDesc(repairDesc);
            order.setPartsCost(partsCost);
            order.setManHours(manHours);
            order.setStatus(StatusDao.loadById(statusId));

            if("/order/update".equalsIgnoreCase(servletPath)) {

                BigDecimal hourlyRateBG = new BigDecimal(String.valueOf(order.getHourlyRate()));
                BigDecimal manHoursBG = new BigDecimal(String.valueOf(order.getManHours()));
                BigDecimal partsCostBG = new BigDecimal(String.valueOf(order.getPartsCost()));
                BigDecimal totalcost = hourlyRateBG.multiply(manHoursBG).add(partsCostBG);
                totalcost = totalcost.setScale(2, RoundingMode.CEILING);

                Double totalCostDouble = totalcost.doubleValue();

                List<String> formInfo = new ArrayList<>();

                boolean validated = true;

                if(totalCostDouble > 99999.99 || serviceAccept == null || serviceAccept.isEmpty()) {

                    Order orderBack = OrderDao.loadById(orderId);

                    if(totalCostDouble > 99999.99) {
                        validated = false;
                        String info = "Chyba trochę przesadziłeś z kosztami?";
                        formInfo.add(info);

                        order.setPartsCost(orderBack.getRepairCost());
                        order.setManHours(orderBack.getManHours());
                    }

                    if(serviceAccept == null || serviceAccept.isEmpty()) {
                        validated = false;
                        String info = "Data przyjęcia zlecenia nie może być pusta";
                        formInfo.add(info);
                        order.setServiceAccept(orderBack.getServiceAccept());
                    }
                }

                if(validated){
                    order.setRepairCost(totalCostDouble);
                } else {
                    backtoFormWithInfo(request, response, order, formInfo);
                }

            }

            OrderDao.save(order);
            response.sendRedirect("/orders");

        } else if("/order/add".equalsIgnoreCase(servletPath)) {
            List<Vehicle> vehicles = VehicleDao.loadAll();
            List<Employee> employees = EmployeeDao.loadAll();
            String formInfo = "Wypełnij wszystkie pola";
            request.setAttribute("serviceAccept",serviceAccept);
            request.setAttribute("servicePlan",servicePlan);

            if(!employeeIdParam.isEmpty()) {
                int employeeId = Integer.valueOf(employeeIdParam);
                Employee chosedEmployee = EmployeeDao.loadById(employeeId);
                request.setAttribute("chosedEmployee",chosedEmployee);
            }

            if(!vehicleIdParam.isEmpty()) {
                int vehicleId = Integer.valueOf(vehicleIdParam);
                Vehicle chosedVehicle = VehicleDao.loadById(vehicleId);
                request.setAttribute("chosedVehicle",chosedVehicle);
            }

            request.setAttribute("formInfo",formInfo);
            request.setAttribute("issueDesc",issueDesc);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("employees", employees);

            getServletContext().getRequestDispatcher("/orderformadd.jsp").forward(request, response);
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

    private void backtoFormWithInfo(HttpServletRequest request, HttpServletResponse response, Order order, List<String> formInfo) throws ServletException, IOException {
        List<Vehicle> vehicles = VehicleDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        List<Status> statuses = StatusDao.loadAll();

        request.setAttribute("formInfo",formInfo);
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);
        request.setAttribute("order",order);
        request.setAttribute("statuses", statuses);
        getServletContext().getRequestDispatcher("/orderform.jsp").forward(request, response);
    }
}
