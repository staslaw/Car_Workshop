package pl.coderslab.servlet;

import pl.coderslab.dao.*;
import pl.coderslab.model.Client;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Status;
import pl.coderslab.model.Vehicle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static pl.coderslab.servlet.Order.getValuesForOrdersView;

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

            if(orderIdParam == null || !isNumber(orderIdParam) || "0".equals(orderIdParam)) {
                List<String> formInfoEditing = new ArrayList<>();
                formInfoEditing.add("Brak klienta o podanym id");
                Order order = new Order();
                backtoFormWithInfo(request, response, order, order, null, formInfoEditing,"/WEB-INF/fragments/editor.jsp");
            } else {

                int orderId = Integer.valueOf(orderIdParam);

                Order orderEditing = OrderDao.loadById(orderId);
                orderEditing.setRepairDesc(repairDesc);
                orderEditing.setStatus(StatusDao.loadById(Integer.valueOf(statusIdParam)));

                List<String> formInfoEditing = isValid(vehicleIdParam, serviceAccept, servicePlan, employeeIdParam, issueDesc, orderEditing,"update");

                boolean editOrdersValid = true;
                boolean serviceStartValid = true;
                boolean partsCostValidated = true;
                boolean manHoursValidated = true;
                boolean totalCostValidated = true;

                String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                if(compareDate(serviceAccept,serviceStart) == 1 || (!serviceStart.isEmpty() && compareDate(serviceStart,todayDate) == -100)) {
                    serviceStartValid = false;
                    formInfoEditing.add("Nieprawidłowa data rozpoczęcia naprawy: data nie może być wcześniejsza niż data przyjęcia zlecenia.");

                } else if (serviceStart.isEmpty()) {
                    orderEditing.setServiceStart(null);

                } else {
                    orderEditing.setServiceStart(serviceStart);
                }

                Double partsCost;
                Integer manHours;

                // blokuje mozliwosc wpisania roboczogodzin jesli nie została ustalona data rozpoczęcia naprawy.

                if(serviceStartValid && compareDate(serviceStart,todayDate) != -100) {

                    if(manHoursParam == null || (!isNumber(manHoursParam) && !manHoursParam.isEmpty())){
                        manHoursValidated = false;
                        manHours = 0;
                        orderEditing.setManHours(manHours);
                        formInfoEditing.add("Podana ilość roboczogodzin nie jest liczbą.");

                    } else if(manHoursParam != null && isNumber(manHoursParam) && !manHoursParam.isEmpty()) {
                        manHours = Integer.valueOf(manHoursParam);
                        orderEditing.setManHours(manHours);
                    } else if(manHoursParam.isEmpty()) {
                        manHours = 0;
                        orderEditing.setManHours(manHours);
                    }

                } else if(!manHoursParam.isEmpty() && !"0".equals(manHoursParam)) {

                    manHours = 0;
                    orderEditing.setManHours(manHours);
                    manHoursValidated = false;
                    formInfoEditing.add("Nie można dodać ilości roboczogodzin pracownika, dopóki nie zostanie podana data rozpoczęcia naprawy");


                } else {
                    manHours = 0;
                    orderEditing.setManHours(manHours);
                }


                if(partsCostParam == null || (!isNumber(partsCostParam) && !partsCostParam.isEmpty())) {
                    partsCostValidated = false;
                    formInfoEditing.add("Podane koszty wykorzystanych części nie są liczbą.");

                } else if (partsCostParam != null && isNumber(partsCostParam) && !partsCostParam.isEmpty()) {
                    partsCost = Double.parseDouble(partsCostParam);
                    orderEditing.setPartsCost(partsCost);
                } else if (partsCostParam.isEmpty()) {
                    partsCost = 0.0;
                    orderEditing.setPartsCost(partsCost);
                }


                if(!partsCostValidated || !manHoursValidated || !serviceStartValid) {
                    editOrdersValid = false;
                }

                if(editOrdersValid) {

                    BigDecimal hourlyRateBG = new BigDecimal(String.valueOf(orderEditing.getHourlyRate()));
                    BigDecimal manHoursBG = new BigDecimal(String.valueOf(orderEditing.getManHours()));
                    BigDecimal partsCostBG = new BigDecimal(String.valueOf(orderEditing.getPartsCost()));
                    BigDecimal totalcost = hourlyRateBG.multiply(manHoursBG).add(partsCostBG);
                    totalcost = totalcost.setScale(2, RoundingMode.CEILING);

                    Double totalCostDouble = totalcost.doubleValue();

                    if (totalCostDouble > 99999.99) {
                        Order orderBack = OrderDao.loadById(orderId);
                        formInfoEditing.add("Chyba trochę przesadziłeś z kosztami?");
                        orderEditing.setPartsCost(orderBack.getRepairCost());
                        orderEditing.setManHours(orderBack.getManHours());
                        totalCostValidated= false;
                    } else {
                        orderEditing.setRepairCost(totalCostDouble);

                    }
                }

                if(formInfoEditing.size() == 0 && editOrdersValid && totalCostValidated) {
                    OrderDao.save(orderEditing);
                    response.sendRedirect("/orders");
                } else {
                    request.setAttribute("servletPath",servletPath);
                    backtoFormWithInfo(request, response, null, orderEditing, null, formInfoEditing,"/WEB-INF/fragments/editor.jsp");
                }

            }


        }

        if("/order/add".equalsIgnoreCase(servletPath)) {

            HttpSession httpSession = request.getSession();

            String orderIdEditingParam = request.getParameter("orderIdEditing");

            int orderIdEditing = 0;

            Order orderEditing = new Order();

            if(orderIdEditingParam != null && isNumber(orderIdEditingParam)) {
                orderIdEditing = Integer.valueOf(orderIdEditingParam);
                orderEditing = OrderDao.loadById(orderIdEditing);
            }

            httpSession.setAttribute("orderEditingSession",orderEditing);

            String previousServletPath = (String) httpSession.getAttribute("servletPath");

            if(previousServletPath == null) {
                previousServletPath="/orders";
            } else {
                previousServletPath = previousServletPath.trim();
                int n = previousServletPath.indexOf("?");

                if(n>0) {
                    previousServletPath = previousServletPath.substring(0,n);
                }

            }

            request.setAttribute("previousServletPath",previousServletPath);


            Order orderToAdd = new Order();

            List<String> formInfoAdding = isValid(vehicleIdParam, serviceAccept, servicePlan, employeeIdParam, issueDesc, orderToAdd, "add");

            if (formInfoAdding.size()==0) {
                orderToAdd.setStatus(StatusDao.loadById(statusId));
                OrderDao.save(orderToAdd);
                response.sendRedirect("/orders");
            } else {

                if("/orders".equals(previousServletPath)) {
                    System.out.println("**********************");
                    System.out.println(previousServletPath);

                    backtoFormWithInfo(request, response, orderToAdd,orderEditing, formInfoAdding,null,"/orders.jsp");

                } else if ("/order/update".equals(previousServletPath)) {
                    System.out.println("**********************");
                    System.out.println(previousServletPath);

                    backtoFormWithInfo(request, response, orderToAdd,orderEditing, formInfoAdding,null,"/WEB-INF/fragments/editor.jsp");


                } else {
                    System.out.println("**********************");
                    System.out.println(previousServletPath);
                    backtoFormWithInfo(request, response, orderToAdd,orderEditing, formInfoAdding,null,"/index.jsp");


                }
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


            // sesje są zapisywame, aby wiedzieć skąd pochodzi zapytanie i następnie odesłać w to samo miejsce
            // chodzi o formularz dodający nowe zlecenie - ktory jest na stronie głównej, w zakładce naprawy i w zakładce edytuj naprawę.

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("servletPath", servletPath);

            int id = 0;
            Order orderEditing = new Order();

            if(idParam != null && !idParam.isEmpty()) {
                id = Integer.valueOf(idParam);
                orderEditing = OrderDao.loadById(id);
            }



            List<Status> statuses = StatusDao.loadAll();

            request.setAttribute("orderEditing",orderEditing);
            request.setAttribute("statuses", statuses);
            request.setAttribute("servletPath",servletPath);


            getServletContext().getRequestDispatcher("/WEB-INF/fragments/editor.jsp").forward(request, response);
        }
    }

    private void backtoFormWithInfo(HttpServletRequest request, HttpServletResponse response, Order orderToAdd, Order orderEditing, List<String> formInfoAdding, List<String> formInfoEditing, String path) throws ServletException, IOException {
        List<Vehicle> vehicles = VehicleDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        List<Status> statuses = StatusDao.loadAll();
        List<Order> orders = OrderDao.loadAll();
        List<Client> clients = ClientDao.loadAll();
        List<Order> ordersLast5 = OrderDao.loadLastLimit(5);

        request.setAttribute("formInfoAdding",formInfoAdding);
        request.setAttribute("formInfoEditing",formInfoEditing);
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);
        request.setAttribute("ordersLast5", ordersLast5);
        request.setAttribute("orders", orders);
        request.setAttribute("orderToAdd",orderToAdd);
        request.setAttribute("orderEditing",orderEditing);
        request.setAttribute("statuses", statuses);

        int ordersSize = orders.size();
        int clientsSize = clients.size();
        int employeesSize = employees.size();
        int vehiclesSize = vehicles.size();

        Map<String, Integer> stats = getValuesForOrdersView(employees, orders);

        stats.put("ordersSize",ordersSize);
        stats.put("clientsSize",clientsSize);
        stats.put("employeesSize",employeesSize);
        stats.put("vehiclesSize",vehiclesSize);

        request.setAttribute("stats",stats);

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
