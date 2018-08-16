package pl.coderslab.servlet;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderDetails", urlPatterns = "/order/details")
public class OrderDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");

        try{
            int id = Integer.valueOf(idParam);

            Order order = OrderDao.loadById(id);

            request.setAttribute("order",order);

            getServletContext().getRequestDispatcher("/orderdetails.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().append("Niepoprawny id zlecenia");
        }

    }
}
