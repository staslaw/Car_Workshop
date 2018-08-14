package pl.coderslab.dao;

import pl.coderslab.model.Employee;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public static void save(Employee employee){
        if(employee.getId() == 0){
            String query = "INSERT INTO Employee(first_name, last_name, address, phone, note, hourly_rate) VALUES (?, ?, ?, ?, ?, ?)";
            List<String> params = new ArrayList<>();
            params.add(employee.getFirstName());
            params.add(employee.getLastName());
            params.add(employee.getAddress());
            params.add(employee.getPhone());
            params.add(employee.getNote());
            params.add(String.valueOf(employee.getHourly_rate()));
            try{
                Integer id = DbService.insertIntoDatabase(query, params);
                if(id != null){
                    employee.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            String query = "UPDATE Employee SET first_name = ?, last_name = ?, address = ?, phone = ?, note = ?, hourly_rate = ?";
            List<String> params = new ArrayList<>();
            params.add(employee.getFirstName());
            params.add(employee.getLastName());
            params.add(employee.getAddress());
            params.add(employee.getPhone());
            params.add(employee.getNote());
            params.add(String.valueOf(employee.getHourly_rate()));
            try{
                DbService.executeUpdate(query, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Employee> loadAll(){
        String query = "SELECT * FROM Employee";
        return getEmployeesFromQuery(query,null);
    }

    public static Employee loadById(int id){
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(rows.get(0)[0]));
            employee.setFirstName(rows.get(0)[1]);
            employee.setLastName(rows.get(0)[2]);
            employee.setAddress(rows.get(0)[3]);
            employee.setPhone(rows.get(0)[4]);
            employee.setNote(rows.get(0)[5]);
            employee.setHourly_rate(Double.parseDouble(rows.get(0)[6]));

            return employee;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }

    private static List<Employee> getEmployeesFromQuery(String query, List<String> params){
        List<Employee> employees = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for(String[] row : rows){
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(row[0]));
                employee.setFirstName((row[1]));
                employee.setLastName((row[2]));
                employee.setAddress((row[3]));
                employee.setPhone((row[4]));
                employee.setNote((row[5]));
                employee.setHourly_rate(Double.parseDouble(row[6]));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Employee employee){
        String query = "DELETE FROM Employee WHERE employee_id = ?";
        try{
            if (employee.getId() != 0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(employee.getId()));
                DbService.executeUpdate(query,params);
                employee.setId(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
