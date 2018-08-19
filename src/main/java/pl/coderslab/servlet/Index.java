package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Client;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Index", urlPatterns = {"","/index"})
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = OrderDao.loadAll();
        List<Client> clients = ClientDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        List<Vehicle> vehicles = VehicleDao.loadAll();

        String servletPath = request.getServletPath();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("servletPath", servletPath);


        request.setAttribute("vehicles", vehicles);
        request.setAttribute("employees", employees);

        List<Order> ordersLast5 = OrderDao.loadLastLimit(5);

        int ordersSize = orders.size();
        int clientsSize = clients.size();
        int employeesSize = employees.size();
        int vehiclesSize = vehicles.size();

//        '1', 'Przyjęty'
//        '2', 'Zatwierdzone koszty'
//        '3', 'W naprawie'
//        '4', 'Gotowy do odbioru'
//        '5', 'Rezygnacja'


        int statsInRepair = 0;
        int statsEnded = 0;
        int statsCancel = 0;

        for(Order order: orders) {
            int status = order.getStatus().getId();
            if (status !=4 && status !=5) {
                statsInRepair++;
            } else if (status ==4) {
                statsEnded++;
            } else {
                statsCancel++;
            }
        }

        statsInRepair = 100 * statsInRepair/ordersSize;
        statsEnded = 100 * statsEnded/ordersSize;
        statsCancel = 100 * statsCancel/ordersSize;


        Map<String, Integer> stats = new HashMap<>();

        stats.put("ordersSize",ordersSize);
        stats.put("clientsSize",clientsSize);
        stats.put("employeesSize",employeesSize);
        stats.put("vehiclesSize",vehiclesSize);
        stats.put("statsInRepair",statsInRepair);
        stats.put("statsEnded",statsEnded);
        stats.put("statsCancel",statsCancel);

        request.setAttribute("stats",stats);
        request.setAttribute("ordersLast5",ordersLast5);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);



    }
}
