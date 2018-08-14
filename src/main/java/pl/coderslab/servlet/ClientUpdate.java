package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "ClientUpdate", urlPatterns = "/ClientUpdate")
public class ClientUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName1");
        String lastName = request.getParameter("lastName1");
        String email = request.getParameter("email1");
        String phone = request.getParameter("phone1");
        String birthday = request.getParameter("birthday1");

        Client client = new Client(firstName, lastName, email, phone, birthday);
        client.setId(id);
        ClientDao.save(client);
        response.sendRedirect("/Client");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");

        if (firstName==null) firstName="''";
        if (lastName==null) lastName="''";
        if (email==null) email="''";
        if (phone==null) phone="''";
        if (birthday==null) birthday="''";

        Calendar cal = Calendar.getInstance();

        response.getWriter().append("<form action='' method='post'>");
        response.getWriter().append("<input type='text' name='firstName1' value=" + firstName + " required>");
        response.getWriter().append("<input type='text' name='lastName1' value=" + lastName + " required>");
        response.getWriter().append("<input type='email' name='email1' value=" + email + " required>");
        response.getWriter().append("<input type='number' name='phone1' value=" + phone + " min='100000000' max='999999999999' step='1' required>");
        response.getWriter().append("<input type='date' name='birthday1' value=" + birthday + " min='1900-01-01' max='" + cal + "' required>");
        response.getWriter().append("<input type='submit' value='zapisz'>");
        response.getWriter().append("</form>");
    }
}