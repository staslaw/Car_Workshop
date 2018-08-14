package pl.coderslab.servlet;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteVehicle", urlPatterns = "/deleteVehicle")
public class DeleteVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Vehicle vehicle = request.getParameter();
        try{
//            VehicleDao.delete(vehicle);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
