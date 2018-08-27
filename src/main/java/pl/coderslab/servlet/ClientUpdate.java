package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ClientUpdate", urlPatterns = "/client/update")
public class ClientUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");

        Client client = new Client(firstName, lastName, email, phone, birthday);
        client.setId(id);
        ClientDao.save(client);
        response.sendRedirect("/clients");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("servletPath", servletPath);

        int id = Integer.parseInt(request.getParameter("id"));
        Client client = ClientDao.loadById(id);
        request.setAttribute("client", client);
        getServletContext().getRequestDispatcher("/WEB-INF/fragments/editor.jsp").forward(request, response);
    }
}