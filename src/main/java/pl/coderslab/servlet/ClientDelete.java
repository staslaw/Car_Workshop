package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientDelete", urlPatterns = "/ClientDelete")
public class ClientDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = ClientDao.loadById(id);
        ClientDao.delete(client);
        response.sendRedirect("/Client");
    }
}
