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

}