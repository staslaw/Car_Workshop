package pl.coderslab.servlet;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Client;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "EmployeeUpdate", urlPatterns = "/EmployeeUpdate")
public class EmployeeUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName1");
        String lastName = request.getParameter("lastName1");
        String address = request.getParameter("address1");
        String phone = request.getParameter("phone1");
        String note = request.getParameter("note1");
        Double hourly_rate = Double.parseDouble(request.getParameter("hourly_rate1"));

        Employee employee = new Employee(firstName, lastName, address, phone, note, hourly_rate);
        employee.setId(id);
        EmployeeDao.save(employee);
        response.sendRedirect("/Employee");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");
        String hourly_rate = request.getParameter("hourly_rate");

        if (firstName==null) firstName="''";
        if (lastName==null) lastName="''";
        if (address==null) address="''";
        if (phone==null) phone="''";
        if (note==null) note="''";
        if (hourly_rate==null) hourly_rate="''";

        response.getWriter().append("<form action='' method='post'>");
        response.getWriter().append("<input type='text' name='firstName1' value=" + firstName + " required>imię<br>");
        response.getWriter().append("<input type='text' name='lastName1' value=" + lastName + " required>nazwisko<br>");
        response.getWriter().append("<input type='text' name='address1' value=" + address + " required>adres<br>");
        response.getWriter().append("<input type='number' name='phone1' value=" + phone + " min='100000000' max='999999999999' step='1' required>telefon<br>");
        response.getWriter().append("<input type='text' name='note1' value=" + note + " required>opis<br>");
        response.getWriter().append("<input type='number' name='hourly_rate1' value=" + hourly_rate + " min='1' max='1000' step='0.1' required>stawka godzinowa<br>");
        response.getWriter().append("<input type='submit' value='zapisz'>");
        response.getWriter().append("</form>");
    }
}
