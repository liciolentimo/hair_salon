import java.util.List;
import org.sql2o.*;

public class Client {
    private String clientName;
    private int clientPhone;
    private String clientEmail;
    private int id;
    private int stylistId;

    public Client(String clientName, int clientPhone, String clientEmail, int stylistId) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.stylistId = stylistId;
    }

    public String getClientName() {
        return clientName;
    }

    public int getClientPhone() {
        return clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public int getId() {
        return id;
    }

    public int getStylistId() {
        return stylistId;
    }

    public static List<Client> all() {
        String sql = "SELECT id, clientName,clientPhone,clientEmail, stylistId FROM clients";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (clientName, clientPhone, clientEmail, stylistId) VALUES (:clientName, :clientPhone, :clientEmail, :stylistId)";
            this.id = (int) con.createQuery(sql, true).addParameter("clientName", this.clientName)
                    .addParameter("clientPhone", this.clientPhone).addParameter("clientEmail", this.clientEmail)
                    .addParameter("stylistId", this.stylistId).executeUpdate().getKey();
        }
    }

    public static Client find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Client.class);
            return client;
        }
    }

    public void update(String clientName,int clientPhone, String clientEmail) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET clientName = :clientName,:clientPhone,:clientEmail WHERE id = :id";
            con.createQuery(sql).addParameter("clientName",clientName).addParameter("clientPhone", clientPhone).addParameter("clientEmail", clientEmail)
                    .addParameter("id", id).executeUpdate();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients WHERE id = :id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

    @Override
    public boolean equals(Object otherClient){
      if (!(otherClient instanceof Client)) {
        return false;
      } else {
        Client newClient = (Client) otherClient;
        return this.getClientName().equals(newClient.getClientName()) &&
               this.getClientPhone() == (newClient.getClientPhone()) &&
               this.getClientEmail().equals(newClient.getClientEmail()) &&
               this.getId() == newClient.getId() &&
               this.getStylistId() == newClient.getStylistId();
      }
    }


}