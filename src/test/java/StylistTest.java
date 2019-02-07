import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

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

}