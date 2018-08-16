package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeFind", urlPatterns = "/EmployeeFind")
public class EmployeeFind extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String find = request.getParameter("find");
        List<pl.coderslab.model.Employee> employeeAll = EmployeeDao.loadAll();
        List<pl.coderslab.model.Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employeeAll.size(); i++) {
            if (find.equals(employeeAll.get(i).getLastName())) {
                employeeList.add(employeeAll.get(i));
            }
        }
        if (employeeList.isEmpty()) {
            getServletContext().getRequestDispatcher("/Employee").forward(request, response);
        } else {
            request.setAttribute("employeeList", employeeList);
            getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
        }
    }
}
