package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ClientFind", urlPatterns = "/ClientFind")
public class ClientFind extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String find = request.getParameter("find");
        List<pl.coderslab.model.Client> clientAll = ClientDao.loadAll();
        List<pl.coderslab.model.Client> clientList = new ArrayList<>();
        for (int i = 0; i < clientAll.size(); i++) {
            if (find.equals(clientAll.get(i).getLastName())) {
                clientList.add(clientAll.get(i));
            }
        }
        if (clientList.isEmpty()) {
            getServletContext().getRequestDispatcher("/Client").forward(request, response);
        } else {
            request.setAttribute("clientList", clientList);
            getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);
        }
    }
}
