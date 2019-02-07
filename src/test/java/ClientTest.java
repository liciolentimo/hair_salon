import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("john", 1234, "john@test.com");
        assertEquals(true, myClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithName_String() {
        Client myClient = new Client("john", 1234, "john@test.com");
        assertEquals("john", myClient.getClientName());
    }


    @Test
    public void Client_instantiatesWithPhone_int() {
        Client myClient = new Client("john", 1234, "john@test.com");
        assertEquals(1234, myClient.getClientPhone());
    }


    @Test
    public void Client_instantiatesWithEmail_String() {
        Client myClient = new Client("john", 1234, "john@test.com");
        assertEquals("john@test.com", myClient.getClientEmail());
    }

}