public class Client {
    private String clientName;
    private int clientPhone;
    private String clientEmail;

    public Client(String clientName, int clientPhone, String clientEmail) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
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
}