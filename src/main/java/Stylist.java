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
}