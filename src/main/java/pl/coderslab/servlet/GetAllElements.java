package pl.coderslab.servlet;

import com.google.gson.Gson;
import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Client;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetAllElements", urlPatterns = "/GetAllElements")
public class GetAllElements extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        List<Client> clients = ClientDao.loadAll();
        List<Employee> employees = EmployeeDao.loadAll();
        List<Vehicle> vehicles = VehicleDao.loadAll();

        List<Object> elements = new ArrayList<>();

        elements.add(clients);
        elements.add(employees);
        elements.add(vehicles);

        response.setContentType("application/json");
        new Gson().toJson(elements, response.getWriter());


    }
}
