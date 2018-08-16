package pl.coderslab.servlet;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Order", urlPatterns = "/orders")
public class Order extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if(idParam == null || idParam.isEmpty()) {

            List<pl.coderslab.model.Order> orderList = OrderDao.loadAll();
            request.setAttribute("orderList", orderList);
            getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);

        } else {

            int id = Integer.valueOf(idParam);
            List<pl.coderslab.model.Order> orderList = OrderDao.loadAllByEmployeeId(id);
            Employee employee = EmployeeDao.loadById(id);
            request.setAttribute("orderList", orderList);
            request.setAttribute("employee",employee);

            getServletContext().getRequestDispatcher("/orders.jsp").forward(request, response);
        }
    }
}
