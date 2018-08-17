package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Client;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VehicleUpdate", urlPatterns = "/updateVehicle")
public class VehicleUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String model = request.getParameter("model");
        String make = request.getParameter("make");
        String productionDate = request.getParameter("productionDate");
        String registration = request.getParameter("registration");
        String nextService = request.getParameter("nextService");
        int idClient = Integer.parseInt(request.getParameter("clientId"));

        Client client = ClientDao.loadById(idClient);
        Vehicle vehicle = new Vehicle(model, make, productionDate, registration, nextService, client);
        vehicle.setId(id);
        client.setId(idClient);
        VehicleDao.save(vehicle);

        response.sendRedirect("/showAllVehicles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Vehicle vehicle = VehicleDao.loadById(id);
            List<Client> clientList = ClientDao.loadAll();
            request.setAttribute("vehicle", vehicle);
            request.setAttribute("clientList", clientList);
            getServletContext().getRequestDispatcher("/updateVehicle.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
