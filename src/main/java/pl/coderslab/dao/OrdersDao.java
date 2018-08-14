//package pl.coderslab.dao;
//
//import pl.coderslab.model.Orders;
//import pl.coderslab.service.DbService;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrdersDao {
//
//    public static void save(Orders order){
//        if(order.getId()==0){
//            String query = "INSERT INTO Orders(employee_id, vehicle_id, status_id, service_accept, service_plan, issue_description) VALUES (?,?,?,NOW(),?,?)";
//            List<String> params = new ArrayList<>();
//            params.add(String.valueOf(order.getEmployee().getId()));
//            params.add(String.valueOf(order.getVehicle().getId()));
//            params.add(String.valueOf(order.getStatus().getId()));
//            params.add(order.getServicePlan());
//            params.add(order.getIssueDesc());
//
//            try {
//                Integer id = DbService.insertIntoDatabase(query,params);
//                if(id!=null){
//                    order.setId(id);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }else{
//            String query = "UPDATE Orders SET employee_id = ?, vehicle_id = ?, status_id = ?, service_accept = ?," +
//                    "service_plan = ?, service_start = ?, issue_description = ?, repair_description = ?, " +
//                    "repair_cost = ?, parts_cost = ?, hourly_rate = ?, man_hours = ? WHERE order_id = ?";
//            List<String> params = new ArrayList<>();
//            params.add(String.valueOf(order.getEmployee().getId()));
//            params.add(String.valueOf(order.getVehicle().getId()));
//            params.add(String.valueOf(order.getStatus().getId()));
//            params.add(order.getServiceAccept());
//            params.add(order.getServicePlan());
//            params.add(order.getServiceStart());
//            params.add(order.getIssueDesc());
//            params.add(order.getRepairDesc());
//            params.add(String.valueOf(order.getRepairCost()));
//            params.add(String.valueOf(order.getPartsCost()));
//            params.add(String.valueOf(order.getHourlyRate()));
//            params.add(String.valueOf(order.getManHours()));
//            params.add(String.valueOf(order.getId()));
//            try{
//                DbService.executeUpdate(query,params);
//
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//
//}
