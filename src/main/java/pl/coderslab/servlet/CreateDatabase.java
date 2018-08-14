package pl.coderslab.servlet;

import pl.coderslab.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateDatabase", urlPatterns = "/createDatabase")
public class CreateDatabase extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query =
                "CREATE TABLE IF NOT EXISTS Client(" +
                        "client_id INT AUTO_INCREMENT NOT NULL, " +
                        "first_name VARCHAR(100) NOT NULL, " +
                        "last_name VARCHAR(200) NOT NULL, " +
                        "email VARCHAR(200) UNIQUE, " +
                        "phone CHAR(15), " +
                        "birthday DATE, " +
                        "PRIMARY KEY(client_id))";

        String query2 =
                "CREATE TABLE IF NOT EXISTS Vehicle(" +
                        "vehicle_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                        "client_id INT, " +
                        "model VARCHAR(200), " +
                        "make VARCHAR(200), " +
                        "production_date DATE, " +
                        "registration VARCHAR(200) UNIQUE, " +
                        "next_service DATE," +
                        "FOREIGN KEY(client_id) REFERENCES Client(client_id))";

        String query3 =
                "CREATE TABLE IF NOT EXISTS Employee(" +
                        "employee_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, " +
                        "first_name VARCHAR(100), " +
                        "last_name VARCHAR(200), " +
                        "address VARCHAR(200), " +
                        "phone CHAR(15), " +
                        "note VARCHAR(255)," +
                        "hourly_rate DECIMAL(5,2))";

        String query4 =
                "CREATE TABLE IF NOT EXISTS Orders(" +
                        "order_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, " +
                        "employee_id INT, " +
                        "vehicle_id INT," +
                        "service_accept DATE, " +
                        "service_plan DATE, " +
                        "service_start DATE, " +
                        "issue_description TEXT, " +
                        "repair_description TEXT, " +
                        "status VARCHAR(30) NOT NULL, " +
                        "repair_cost DECIMAL(7,2)," +
                        "parts_cost DECIMAL(7,2)," +
                        "hourly_rate DECIMAL(5,2)," +
                        "man_hours INT," +
                        "FOREIGN KEY (employee_id) REFERENCES Employee(employee_id),"+
                        "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id))";

        try {
            DbService.executeUpdate(query,null);
            DbService.executeUpdate(query2,null);
            DbService.executeUpdate(query3,null);
            DbService.executeUpdate(query4,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
