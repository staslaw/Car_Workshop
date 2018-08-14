package pl.coderslab.dao;

import pl.coderslab.model.Client;
import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public static void save(Client client){
        if(client.getId()==0){
            String query = "INSERT INTO Client(first_name, last_name, email, phone, birthday) VALUES (?,?,?,?,?)";
            List<String> params = new ArrayList<>();
            params.add(client.getFirstName());
            params.add(client.getLastName());
            params.add(client.getEmail());
            params.add(client.getPhone());
            params.add(client.getBirthday());

            try {
                Integer id = DbService.insertIntoDatabase(query,params);
                if(id!=null){
                    client.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            String query = "UPDATE Client SET first_name = ?, last_name = ?, email = ?, phone = ?, birthday = ? WHERE client_id = ?";
            List<String> params = new ArrayList<>();
            params.add(client.getFirstName());
            params.add(client.getLastName());
            params.add(client.getEmail());
            params.add(client.getPhone());
            params.add(client.getBirthday());
            params.add(String.valueOf(client.getId()));
            try{
                DbService.executeUpdate(query,params);

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Client> loadAll(){
        String query = "SELECT * FROM Client";
        return getClientsFromQuery(query,null);

    }

    public static Client loadById(int id){
        String query = "SELECT * FROM Client WHERE client_id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Client client = new Client();
            client.setId(Integer.parseInt(rows.get(0)[0]));
            client.setFirstName(rows.get(0)[1]);
            client.setLastName(rows.get(0)[2]);
            client.setEmail(rows.get(0)[3]);
            client.setPhone(rows.get(0)[4]);
            client.setBirthday(rows.get(0)[5]);

            return client;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    private static List<Client> getClientsFromQuery(String query, List<String> params) {
        List<Client> clients = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for (String[] row: rows){
                Client client = new Client();
                client.setId(Integer.parseInt(row[0]));
                client.setFirstName(row[1]);
                client.setLastName(row[2]);
                client.setEmail(row[3]);
                client.setPhone(row[4]);
                client.setBirthday(row[5]);
                clients.add(client);
            }
            return clients;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Client client){
        String query = "DELETE FROM Client WHERE client_id = ?";
        try{
            if(client.getId()!=0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(client.getId()));
                DbService.executeUpdate(query,params);
                client.setId(0);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
