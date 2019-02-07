import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Stylist_instantiatesCorrectly_true() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        assertEquals(true, myStylist instanceof Stylist);
    }

    @Test
    public void Stylist_instantiatesWithName_String() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        assertEquals("mary", myStylist.getStylistName());
    }

    @Test
    public void Stylist_instantiatesWithPhone_int() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        assertEquals(5678, myStylist.getStylistPhone());
    }

    @Test
    public void Stylist_instantiatesWithEmail_String() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        assertEquals("mary@test.com", myStylist.getStylistEmail());
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

    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("mary", 5678, "mary@test.com");
        firstStylist.save();
        Stylist secondStylist = new Stylist("kevin", 5679, "kevin@test.com");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }

}