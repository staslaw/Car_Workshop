package pl.coderslab.servlet;

import com.google.gson.Gson;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetVehicle", urlPatterns = "/GetVehicle")
public class GetVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        String vehicleIdParam = request.getParameter("vehicleId").trim();
        if(vehicleIdParam != null){
            int vehicleId = Integer.parseInt(vehicleIdParam);
            Vehicle vehicle = VehicleDao.loadById(vehicleId);
            if(vehicle!= null) {
                response.setContentType("application/json");
                new Gson().toJson(vehicle, response.getWriter());
            } else {
                response.getWriter().append("0");
            }
        }
    }
}
