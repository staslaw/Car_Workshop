package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Client", urlPatterns = "/clients")
public class Client extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String find = request.getParameter("find");

        if(find != null && !find.isEmpty()) {

            find = find.trim();
            find = find.toLowerCase();

            List<pl.coderslab.model.Client> clientAll = ClientDao.loadAll();
            List<pl.coderslab.model.Client> clientList = new ArrayList<>();
            for (int i = 0; i < clientAll.size(); i++) {
                String lastName = clientAll.get(i).getLastName();
                lastName = lastName.trim();
                lastName = lastName.toLowerCase();
                if (lastName.contains(find)) {
                    clientList.add(clientAll.get(i));
                }
            }

            if (!clientList.isEmpty()) {
                request.setAttribute("clientList", clientList);
                request.setAttribute("chosedClient", find);
                getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);
            } else {
                String findInfo = "Brak klienta o podanym nazwisku";
                request.setAttribute("findInfo",findInfo);
                getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);

            }

        } else {
            String findInfo = "Nie wpisałeś nazwiska";
            request.setAttribute("findInfo",findInfo);
            getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<pl.coderslab.model.Client> clientList = ClientDao.loadAll();
        String pageTitle = "Lista Klientów";
        request.setAttribute("clientList", clientList);
        request.setAttribute("pageTitle",pageTitle);
        getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);
    }
}
