package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientUpdate", urlPatterns = "/ClientUpdate")
public class ClientUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");

        Client client = new Client(firstName, lastName, email, phone, birthday);
        client.setId(id);
        ClientDao.save(client);
        response.sendRedirect("/Client");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = ClientDao.loadById(id);
        request.setAttribute("client", client);
        getServletContext().getRequestDispatcher("/clientForm.jsp").forward(request, response);
    }
}