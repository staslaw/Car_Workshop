package pl.coderslab.servlet;

import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ShowVehicles", urlPatterns = "/showAllVehicles")
public class ShowVehicles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) session.getAttribute("vehicles");
        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle all : vehicles){
                System.out.println(all);
            }
            request.getRequestDispatcher("/showallvehicles.jsp").forward(request, response);
        } else {
            response.getWriter().append("THERE ARE NO VEHICLES");
        }
    }
}
