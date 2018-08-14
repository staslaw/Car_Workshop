package pl.coderslab.dao;

import pl.coderslab.model.Order;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public static void save(Order order){
        if(order.getId()==0){
            String query = "INSERT INTO Orders(employee_id, vehicle_id, status_id, service_accept, service_plan, issue_description) VALUES (?,?,?,NOW(),?,?)";
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(order.getEmployee().getId()));
            params.add(String.valueOf(order.getVehicle().getId()));
            params.add(String.valueOf(order.getStatus().getId()));
            params.add(order.getServicePlan());
            params.add(order.getIssueDesc());

            try {
                Integer id = DbService.insertIntoDatabase(query,params);
                if(id!=null){
                    order.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            String query = "UPDATE Orders SET employee_id = ?, vehicle_id = ?, status_id = ?, service_accept = ?," +
                    "service_plan = ?, service_start = ?, issue_description = ?, repair_description = ?, " +
                    "repair_cost = ?, parts_cost = ?, hourly_rate = ?, man_hours = ? WHERE order_id = ?";
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(order.getEmployee().getId()));
            params.add(String.valueOf(order.getVehicle().getId()));
            params.add(String.valueOf(order.getStatus().getId()));
            params.add(order.getServiceAccept());
            params.add(order.getServicePlan());
            params.add(order.getServiceStart());
            params.add(order.getIssueDesc());
            params.add(order.getRepairDesc());
            params.add(String.valueOf(order.getRepairCost()));
            params.add(String.valueOf(order.getPartsCost()));
            params.add(String.valueOf(order.getHourlyRate()));
            params.add(String.valueOf(order.getManHours()));
            params.add(String.valueOf(order.getId()));
            try{
                DbService.executeUpdate(query,params);

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Order> loadAll(){
        String query = "SELECT * FROM Orders";
        return getOrdersFromQuery(query,null);

    }

    public static Order loadById(int id){
        String query = "SELECT * FROM Orders WHERE order_id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Order order = new Order();
            order.setId(Integer.parseInt(rows.get(0)[0]));
            order.setEmployee(EmployeeDao.loadById(Integer.valueOf(rows.get(0)[1])));
            order.setVehicle(VehicleDao.loadById(Integer.valueOf(rows.get(0)[2])));
            order.setStatus(StatusDao.loadById(Integer.valueOf(rows.get(0)[3])));
            order.setServiceAccept(rows.get(0)[4]);
            order.setServicePlan(rows.get(0)[5]);
            order.setServiceStart(rows.get(0)[6]);
            order.setIssueDesc(rows.get(0)[7]);
            order.setRepairDesc(rows.get(0)[8]);

            if(rows.get(0)[9] != null) {
                order.setRepairCost(Double.parseDouble(rows.get(0)[9]));
            }

            if(rows.get(0)[10] != null) {
                order.setPartsCost(Double.parseDouble(rows.get(0)[10]));
            }

            if(rows.get(0)[11] != null) {
                order.setHourlyRate(Double.parseDouble(rows.get(0)[11]));
            }

            if(rows.get(0)[12] != null) {
                order.setManHours(Integer.parseInt(rows.get(0)[12]));
            }

            return order;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }

    public static List<Order> loadAllByVehicleId(int id){
        String query = "SELECT * FROM Orders WHERE vehicle_id = ?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return getOrdersFromQuery(query, params);
    }

    public static List<Order> loadAllByEmployeeId(int id){
        String query = "SELECT * FROM Orders WHERE employee_id = ?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return getOrdersFromQuery(query, params);
    }


    private static List<Order> getOrdersFromQuery(String query, List<String> params){
        List<Order> orders = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for(String[] row : rows){
                Order order = new Order();
                order.setId(Integer.parseInt(row[0]));
                order.setEmployee(EmployeeDao.loadById(Integer.valueOf(row[1])));
                order.setVehicle(VehicleDao.loadById(Integer.valueOf(row[2])));
                order.setStatus(StatusDao.loadById(Integer.valueOf(row[3])));
                order.setServiceAccept(row[4]);
                order.setServicePlan(row[5]);
                order.setServiceStart(row[6]);
                order.setIssueDesc(row[7]);
                order.setRepairDesc(row[8]);

                if(row[9] != null) {
                    order.setRepairCost(Double.parseDouble(row[9]));
                }

                if(row[10] != null) {
                    order.setPartsCost(Double.parseDouble(row[10]));
                }

                if(row[11] != null) {
                    order.setHourlyRate(Double.parseDouble(row[11]));
                }

                if(row[12] != null) {
                    order.setManHours(Integer.parseInt(row[12]));
                }

                orders.add(order);
            }
            return orders;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Order order){
        String query = "DELETE FROM Orders WHERE order_id = ?";
        try{
            if (order.getId() != 0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(order.getId()));
                DbService.executeUpdate(query,params);
                order.setId(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
