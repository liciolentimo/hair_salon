import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        assertEquals(true, myClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithName_String() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        assertEquals("john", myClient.getClientName());
    }

    @Test
    public void Client_instantiatesWithPhone_int() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        assertEquals(1234, myClient.getClientPhone());
    }

    @Test
    public void Client_instantiatesWithEmail_String() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        assertEquals("john@test.com", myClient.getClientEmail());
    }

    @Test
    public void getId_clientsInstantiateWithAnID() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        myClient.save();
        assertTrue(myClient.getId() > 0);
    }

    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("john", 1234, "john@test.com", 1);
        firstClient.save();
        Client secondClient = new Client("john", 1234, "john@test.com", 1);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
    }

    @Test
    public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("john", 1234, "john@test.com", 1);
        firstClient.save();
        Client secondClient = new Client("john", 1234, "john@test.com", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }

    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }

    @Test
    public void save_savesStylistIdIntoDB_true() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        myStylist.save();
        Client myClient = new Client("john", 1234, "john@test.com", myStylist.getId());
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylist.getId());
    }

    // @Test
    // public void update_updatesClientDetails_true() {
    //     Client myClient = new Client("john", 1234, "john@test.com", 1);
    //     myClient.save();
    //     myClient.update("james", 1274, "james@test.com");
    //     assertEquals("james", Client.find(myClient.getId()).getClientName());
    //     assertEquals(1274, Client.find(myClient.getId()).getClientPhone());
    //     assertEquals("james@test.com", Client.find(myClient.getId()).getClientEmail());
    // }
    

    @Test
    public void delete_deletesClient_true() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
    }

    @Test
    public void save_returnsTrueIfDetailsAretheSame() {
        Client myClient = new Client("john", 1234, "john@test.com", 1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }

}