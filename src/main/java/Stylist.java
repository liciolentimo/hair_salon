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

    public static Stylist find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public static Stylist findName(String stylistName) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where stylistName=:stylistName";
            Stylist stylist = con.createQuery(sql).addParameter("stylistName", stylistName).executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public static Stylist findEmail(String stylistEmail) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where stylistEmail=:stylistEmail";
            Stylist stylist = con.createQuery(sql).addParameter("stylistEmail", stylistEmail).executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getStylistName().equals(newStylist.getStylistName())
                    && this.getStylistPhone() == (newStylist.getStylistPhone())
                    && this.getStylistEmail().equals(newStylist.getStylistEmail())
                    && this.getId() == newStylist.getId();
        }
    }

    public void update(String stylistName,int stylistPhone,String stylistEmail) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE stylists SET stylistName,stylistPhone,stylistEmail = :stylistName,:stylistPhone,stylistEmail WHERE id = :id";
            con.createQuery(sql).addParameter("stylistName",stylistName)
                    .addParameter("id", id).executeUpdate();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM stylists WHERE id=:id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }
}