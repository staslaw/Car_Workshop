package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.VehicleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VehicleShow", urlPatterns = "/showAllVehicles")
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

        List<pl.coderslab.model.Vehicle> vehicleList = VehicleDao.loadAll();
        if(vehicleList != null & !vehicleList.isEmpty()) {
            request.setAttribute("vehicleList", vehicleList);
            request.getRequestDispatcher("/showVehicles.jsp").forward(request, response);
        } else {
            response.getWriter().append("THERE ARE NO VEHICLES");
        }
    }
}
