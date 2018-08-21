package pl.coderslab.servlet;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "Raport2", urlPatterns = "/Raport2")
public class Raport2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = OrderDao.loadAll();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String news;
        int zysk = 0;

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
                for (int i = 0; i < orderList.size(); i++) {
                    String dateStart = orderList.get(i).getServiceStart();
                    if (null == dateStart) {
                    } else {
                        LocalDate checkDate = LocalDate.parse(dateStart);
                        if (!checkDate.isAfter(dateTo) && !checkDate.isBefore(dateFrom)) {
                            zysk += orderList.get(i).getRepairCost();
                        }
                    }
                }
            }
        }
        request.setAttribute("zysk", zysk);
        getServletContext().getRequestDispatcher("/raport2.jsp").forward(request, response);
    }
}
