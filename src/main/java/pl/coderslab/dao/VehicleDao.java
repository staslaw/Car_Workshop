package pl.coderslab.dao;

import pl.coderslab.model.Vehicle;
import pl.coderslab.model.Client;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    public static void save(Vehicle vehicle){
        if(vehicle.getId() == 0){
            String query = "INSERT INTO Vehicle(model, make, production_date, registration, next_service, client_id) VALUES (?, ?, ?, ?, ?, ?)";
            List<String> params = new ArrayList<>();
            params.add(vehicle.getModel());
            params.add(vehicle.getMake());
            params.add(vehicle.getProductionDate());
            params.add(vehicle.getRegistration());
            params.add(vehicle.getNextService());
            params.add(String.valueOf(vehicle.getClient().getId()));
            try{
                Integer id = DbService.insertIntoDatabase(query, params);
                if(id != null){
                    vehicle.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            String query = "UPDATE Vehicle SET model = ?, make = ?, production_date = ?, registration = ?, next_service = ?, client_id = ? WHERE vehicle_id = ?";
            List<String> params = new ArrayList<>();
            params.add(vehicle.getModel());
            params.add(vehicle.getMake());
            params.add(vehicle.getProductionDate());
            params.add(vehicle.getRegistration());
            params.add(vehicle.getNextService());
            params.add(String.valueOf(vehicle.getClient().getId()));
            params.add(String.valueOf(vehicle.getId()));
            try{
                DbService.executeUpdate(query, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Vehicle> loadAll(){
        String query = "SELECT * FROM Vehicle";
        return getVehiclesFromQuery(query,null);
    }

    public static Vehicle loadById(int id){
        String query = "SELECT * FROM Vehicle WHERE vehicle_id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Vehicle vehicle = new Vehicle();
            vehicle.setId(Integer.parseInt(rows.get(0)[0]));
            vehicle.setClient(ClientDao.loadById(Integer.parseInt(rows.get(0)[1])));
            vehicle.setModel(rows.get(0)[2]);
            vehicle.setMake(rows.get(0)[3]);
            vehicle.setProductionDate(rows.get(0)[4]);
            vehicle.setRegistration(rows.get(0)[5]);
            vehicle.setNextService(rows.get(0)[6]);

            return vehicle;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }

    public static ArrayList<Vehicle> loadAllByClientId(int id){
        String query = "SELECT * FROM Vehicle WHERE client_id = ?";
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        return getVehiclesFromQuery(query, params);
    }


    private static ArrayList<Vehicle> getVehiclesFromQuery(String query, List<String> params){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for(String[] row : rows){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(Integer.parseInt(row[0]));
                vehicle.setClient(ClientDao.loadById(Integer.parseInt(row[1])));
                vehicle.setModel((row[2]));
                vehicle.setMake((row[3]));
                vehicle.setProductionDate((row[4]));
                vehicle.setRegistration((row[5]));
                vehicle.setNextService((row[6]));
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Vehicle vehicle){
        String query = "DELETE FROM Vehicle WHERE vehicle_id = ?";
        try{
            if (vehicle.getId() != 0){
                ArrayList<String> params = new ArrayList<>();
                params.add(String.valueOf(vehicle.getId()));
                DbService.executeUpdate(query,params);
                vehicle.setId(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
