package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VehicleShow", urlPatterns = {"/showAllVehicles", "/vehicles/client"})
public class VehicleShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String find = request.getParameter("find").toLowerCase();
        int length = find.length();
        if(find != null && !find.isEmpty()) {
            List<pl.coderslab.model.Client> clientAll = ClientDao.loadAll();
            List<pl.coderslab.model.Client> clientList = new ArrayList<>();
            for (int i = 0; i < clientAll.size(); i++) {
                String lastName = clientAll.get(i).getLastName().toLowerCase();
                String sub = lastName.substring(0,length);
                if (lastName.equals(find)) {
                    clientList.add(clientAll.get(i));
                }
            }
            if (clientList.isEmpty()) {
                getServletContext().getRequestDispatcher("/Client").forward(request, response);
            } else {
                request.setAttribute("clientList", clientList);
                getServletContext().getRequestDispatcher("/client.jsp").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/showVehicles.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        List<Client> clients = ClientDao.loadAll();
        request.setAttribute("clients", clients);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("servletPath", servletPath);

        if("/vehicles/client".equalsIgnoreCase(servletPath)) {

            String idParam = request.getParameter("id");
            if(idParam == null || idParam.isEmpty()) {
                response.sendRedirect("/Client");
            } else {

                int id = Integer.valueOf(idParam);
                List<pl.coderslab.model.Vehicle> vehicleList = VehicleDao.loadAllByClientId(id);
                Client client = ClientDao.loadById(id);
                request.setAttribute("vehicleList", vehicleList);
                request.setAttribute("chosedClient", client);

                getServletContext().getRequestDispatcher("/showVehicles.jsp").forward(request, response);
            }
        }

        String pageTitle = "Lista Pojazdów";
        request.setAttribute("pageTitle",pageTitle);

        List<pl.coderslab.model.Vehicle> vehicleList = VehicleDao.loadAll();
        if(vehicleList != null & !vehicleList.isEmpty()) {
            request.setAttribute("vehicleList", vehicleList);
            request.getRequestDispatcher("/showVehicles.jsp").forward(request, response);
        } else {
            response.getWriter().append("THERE ARE NO VEHICLES");
        }
    }
}
