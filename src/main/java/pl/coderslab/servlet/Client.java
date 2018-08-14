package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Client", urlPatterns = "/Client")
public class Client extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<pl.coderslab.model.Client> clientList = ClientDao.loadAll();
        request.setAttribute("clientList", clientList);
        getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);
    }
}
