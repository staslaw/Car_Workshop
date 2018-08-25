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

@WebServlet(name = "Employee", urlPatterns = "/Employee")
public class Employee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String find = request.getParameter("find");

        if(find != null && !find.isEmpty()) {

            find = find.trim();
            find = find.toLowerCase();

            List<pl.coderslab.model.Employee> employeeAll = EmployeeDao.loadAll();
            List<pl.coderslab.model.Employee> employeeList = new ArrayList<>();
            for (int i = 0; i < employeeAll.size(); i++) {
                String lastName = employeeAll.get(i).getLastName();
                lastName = lastName.trim();
                lastName = lastName.toLowerCase();
                if (lastName.contains(find)) {
                    employeeList.add(employeeAll.get(i));
                }
            }

            if (!employeeList.isEmpty()) {
                request.setAttribute("employeeList", employeeList);
                request.setAttribute("chosedEmployee", find);
                getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
            } else {
                String findInfo = "Brak pracownika o podanym nazwisku";
                request.setAttribute("findInfo",findInfo);
                getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);

            }

        } else {
            String findInfo = "Nie wpisałeś nazwiska";
            request.setAttribute("findInfo",findInfo);
            getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageTitle = "Lista Pracowników";
        request.setAttribute("pageTitle",pageTitle);

        List<pl.coderslab.model.Employee> employeeList = EmployeeDao.loadAll();
        request.setAttribute("employeeList", employeeList);
        getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
