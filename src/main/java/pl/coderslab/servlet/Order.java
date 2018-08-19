package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Order", urlPatterns = {"/orders","/orders/employee","/orders/vehicle"})
public class Order extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        List<Employee> employees = EmployeeDao.loadAll();
        List<Vehicle> vehicles = VehicleDao.loadAll();

        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);


        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("servletPath", servletPath);

        if("/orders".equalsIgnoreCase(servletPath)) {
            List<pl.coderslab.model.Order> orders = OrderDao.loadAll();
            request.setAttribute("orders", orders);

            Map<String, Integer> stats = getValuesForOrdersView(employees, orders);

            request.setAttribute("stats",stats);

            getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
        }

        if("/orders/employee".equalsIgnoreCase(servletPath)) {

            String idParam = request.getParameter("id");
            if(idParam == null || idParam.isEmpty()) {
                response.sendRedirect("/Employee");
            } else {

                int id = Integer.valueOf(idParam);
                List<pl.coderslab.model.Order> orderList = OrderDao.loadAllByEmployeeId(id);
                Employee employee = EmployeeDao.loadById(id);
                request.setAttribute("orderList", orderList);
                request.setAttribute("chosedEmployee",employee);

                getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
            }
        }

        if("/orders/vehicle".equalsIgnoreCase(servletPath)) {

            String idParam = request.getParameter("id");
            if(idParam == null || idParam.isEmpty()) {
                response.sendRedirect("/showAllVehicles");
            } else {
                int id = Integer.valueOf(idParam);
                List<pl.coderslab.model.Order> orderList = OrderDao.loadAllByVehicleId(id);
                Vehicle vehicle = VehicleDao.loadById(id);
                request.setAttribute("orderList", orderList);
                request.setAttribute("chosedVehicle",vehicle);

                getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
            }

        }
    }

    public static Map<String, Integer> getValuesForOrdersView(List<Employee> employees, List<pl.coderslab.model.Order> orderList) {
        int freeEmployees = 0;

        boolean employeeFound = true;

        for(Employee employee: employees) {
            for(pl.coderslab.model.Order order: orderList) {
                if(employee.getId() == order.getEmployee().getId()) {
                    employeeFound = true;
                    break;
                } else {
                    employeeFound = false;
                }

            }

            if(employeeFound==false) {
                freeEmployees++;
            }
        }

        List<pl.coderslab.model.Order> ordersAwaited = OrderDao.loadAllByStatusId(1);
        List<pl.coderslab.model.Order> ordersInRepair = OrderDao.loadAllByStatusId(3);
        List<pl.coderslab.model.Order> ordersEnded = OrderDao.loadAllByStatusId(4);

        int orderAwaitedCount = 0;
        int orderInRepairCount = 0;
        int ordersEndedCount = 0;

        if(ordersAwaited != null) {
            orderAwaitedCount = ordersAwaited.size();
        }

        if(ordersInRepair != null) {
            orderInRepairCount = ordersInRepair.size();
        }

        if(ordersEnded != null) {
            ordersEndedCount = ordersEnded.size();
        }

        Map<String, Integer> stats = new HashMap<>();

        stats.put("orderAwaitedCount",orderAwaitedCount);
        stats.put("orderInRepairCount",orderInRepairCount);
        stats.put("ordersEndedCount",ordersEndedCount);
        stats.put("freeEmployees",freeEmployees);
        return stats;
    }
}
