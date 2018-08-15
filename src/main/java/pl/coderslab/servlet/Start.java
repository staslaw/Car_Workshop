package pl.coderslab.servlet;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Start", urlPatterns = "/Start")
public class Start extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<pl.coderslab.model.Order> orderList = OrderDao.loadAll();
        request.setAttribute("orderList", orderList);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
