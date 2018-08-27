package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Vehicle;
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

        String pageTitle = "Lista Zleceń";
        request.setAttribute("pageTitle",pageTitle);

        String servletPath = request.getServletPath();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("servletPath", servletPath);

        if("/orders".equalsIgnoreCase(servletPath)) {
            List<pl.coderslab.model.Order> orders = OrderDao.loadAll();
            request.setAttribute("orders", orders);

            Map<String, Integer> stats = getValuesForOrdersView(orders);

            request.setAttribute("stats",stats);

            getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
        }

        if("/orders/employee".equalsIgnoreCase(servletPath)) {

            String idParam = request.getParameter("id");
            if(idParam == null || idParam.isEmpty()) {
                response.sendRedirect("/Employee");
            } else {

                int id = Integer.valueOf(idParam);
                List<pl.coderslab.model.Order> orders = OrderDao.loadAllByEmployeeId(id);
                Employee employee = EmployeeDao.loadById(id);
                request.setAttribute("orders", orders);
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
                List<pl.coderslab.model.Order> orders = OrderDao.loadAllByVehicleId(id);
                Vehicle vehicle = VehicleDao.loadById(id);
                request.setAttribute("orders", orders);
                request.setAttribute("chosedVehicle",vehicle);

                getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
            }

        }
    }

    public static Map<String, Integer> getValuesForOrdersView(List<pl.coderslab.model.Order> orders) {

        int ordersAwaitedCount = 0;
        int ordersInRepairCount = 0;
        int ordersEndedCount = 0;
        int ordersCanceled = 0;

        for(pl.coderslab.model.Order order : orders) {
            if(order.getStatus().getId() == 1) {
                ordersAwaitedCount++;
            } else if (order.getStatus().getId() == 3) {
                ordersInRepairCount++;
            } else if (order.getStatus().getId() == 4) {
                ordersEndedCount++;
            } else if (order.getStatus().getId() == 5) {
                ordersCanceled++;
            }
        }

        Map<String, Integer> stats = new HashMap<>();

        stats.put("ordersAwaitedCount",ordersAwaitedCount);
        stats.put("ordersInRepairCount",ordersInRepairCount);
        stats.put("ordersEndedCount",ordersEndedCount);
        stats.put("ordersCanceled",ordersCanceled);
        return stats;
    }
}
