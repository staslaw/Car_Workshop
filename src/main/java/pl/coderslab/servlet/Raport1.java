package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Raport1", urlPatterns = "/Raport1")
public class Raport1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageTitle = "Raport 1";
        request.setAttribute("pageTitle",pageTitle);

        List<Employee> employeeList = EmployeeDao.loadAll();
        List<Order> orderList = OrderDao.loadAll();
        Map<String, Integer> roboczogodziny = new HashMap<>();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String news;

        for (int i = 0; i < employeeList.size(); i++) {
            int id = employeeList.get(i).getId();
            String name = employeeList.get(i).getFirstName();
            String surname = employeeList.get(i).getLastName();
            String key = name + " " + surname;
            roboczogodziny.put(key, 0);
            int hours = 0;
            for (int j = 0; j < orderList.size(); j++) {
                if (id == orderList.get(j).getEmployee().getId()) {
                    if (!(orderList.get(j).getManHours() == null)) {
                        hours += orderList.get(j).getManHours();
                    }
                }
            }
            roboczogodziny.put(key, hours);
        }
        if (from == null || to == null) {
        } else if (("".equals(from) || "".equals(to))) {
            news = "nie podałeś daty";
            request.setAttribute("news", news);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
        } else {
            LocalDate dateFrom = LocalDate.parse(from);
            LocalDate dateTo = LocalDate.parse(to);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
            if (dateFrom.isAfter(dateTo)) {
                news = "data początkowa musi być wcześniejsza od daty końcowej";
                request.setAttribute("news", news);
            } else {
                roboczogodziny.clear();
                for (int i = 0; i < employeeList.size(); i++) {
                    int id = employeeList.get(i).getId();
                    String name = employeeList.get(i).getFirstName();
                    String surname = employeeList.get(i).getLastName();
                    String key = name + " " + surname;
                    roboczogodziny.put(key, 0);
                    int hours = 0;
                    for (int j = 0; j < orderList.size(); j++) {
                        int checkId = orderList.get(j).getEmployee().getId();
                        String check = orderList.get(j).getServiceStart();
                        if (check == null || "".equals(check)) {
                        } else {
                            LocalDate checkDate = LocalDate.parse(check);
                            if ((id == checkId) && (!checkDate.isAfter(dateTo)) && (!checkDate.isBefore(dateFrom))) {
                                hours += orderList.get(j).getManHours();
                            }
                        }
                    }
                    roboczogodziny.put(key, hours);
                }
            }
        }
        request.setAttribute("roboczogodziny", roboczogodziny);
        getServletContext().getRequestDispatcher("/raport1.jsp").forward(request, response);
    }
}