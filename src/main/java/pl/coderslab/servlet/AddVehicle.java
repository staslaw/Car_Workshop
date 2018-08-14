package pl.coderslab.servlet;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Client;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddVehicle", urlPatterns = "/addVehicle")
public class AddVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String model = request.getParameter("model");
        String make = request.getParameter("make");
        String productionDate = request.getParameter("productionDate");
        String registration = request.getParameter("registration");
        String nextService = request.getParameter("nextService");
//        Client client = request.getParameter(client.getFirstName() + " " + client.getLastName()); // ???
//        Vehicle vehicle = new Vehicle(model, make, productionDate, registration, nextService, client);

//        VehicleDao.save(vehicle);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jdbc/addVehicle.jsp").forward(request,response);
    }
}
