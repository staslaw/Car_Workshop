package pl.coderslab.servlet;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowVehicles", urlPatterns = "/showAllVehicles")
public class ShowVehicles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
