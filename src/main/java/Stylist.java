import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Stylist {
    private String stylistName;
    private int stylistPhone;
    private String stylistEmail;
    private int id;

    public Stylist(String stylistName, int stylistPhone, String stylistEmail) {
        this.stylistName = stylistName;
        this.stylistPhone = stylistPhone;
        this.stylistEmail = stylistEmail;
    }

    public String getStylistName() {
        return stylistName;
    }

    public int getStylistPhone() {
        return stylistPhone;
    }

    public String getStylistEmail() {
        return stylistEmail;
    }

    public int getId() {
        return id;
    }

    public static List<Stylist> all() {
        String sql = "SELECT id, stylistName,stylistPhone,stylistEmail FROM stylists";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (stylistName, stylistPhone, stylistEmail) VALUES (:stylistName, :stylistPhone, :stylistEmail)";
            this.id = (int) con.createQuery(sql, true).addParameter("stylistName", this.stylistName)
                    .addParameter("stylistPhone", this.stylistPhone).addParameter("stylistEmail", this.stylistEmail)
                    .executeUpdate().getKey();
        }
    }

    public List<Client> getClients() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql).addParameter("id", this.id).executeAndFetch(Client.class);
        }
    }
}