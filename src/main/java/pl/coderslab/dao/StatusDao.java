package pl.coderslab.dao;

import pl.coderslab.model.Status;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    public static void save(Status status){
        if(status.getId()==0){

            String query = "INSERT INTO Status(name) VALUES (?)";
            List<String> params = new ArrayList<>();
            params.add(status.getName());

            try {
                Integer id = DbService.insertIntoDatabase(query,params);
                if(id!=null){
                    status.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            String query = "UPDATE Status SET name = ? WHERE status_id = ?";
            List<String> params = new ArrayList<>();
            params.add(status.getName());
            params.add(String.valueOf(status.getId()));
            try {
                DbService.executeUpdate(query,params);
                System.out.println("Grupa została zmodyfikowana");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Status> loadAll(){
        List<Status> statuses = new ArrayList<>();
        String query = "SELECT * FROM Status";
        try{

            List<String[]> rows = DbService.getData(query,null);
            for (String[] row: rows){
                Status status = new Status();
                status.setId(Integer.parseInt(row[0]));
                status.setName(row[1]);
                statuses.add(status);
            }
            return statuses;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public static Status loadById(int id){
        String query = "SELECT * FROM Status WHERE status_id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Status status = new Status();
            status.setId(Integer.parseInt(rows.get(0)[0]));
            status.setName(rows.get(0)[1]);

            return status;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void delete(Status status){
        String query = "DELETE FROM Status WHERE status_id = ?";
        try{
            if(status.getId()!=0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(status.getId()));
                DbService.executeUpdate(query,params);
                status.setId(0);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
