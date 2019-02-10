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

    @Test
    public void find_returnsStylistWithSameName_secondStylist() {
        Stylist firstStylist = new Stylist("mary", 5678, "mary@test.com");
        firstStylist.save();
        Stylist secondStylist = new Stylist("kevin", 5679, "kevin@test.com");
        secondStylist.save();
        assertEquals(Stylist.findName(secondStylist.getStylistName()), secondStylist);
    }

    @Test
    public void find_returnsStylistWithSameEmail_secondStylist() {
        Stylist firstStylist = new Stylist("mary", 5678, "mary@test.com");
        firstStylist.save();
        Stylist secondStylist = new Stylist("kevin", 5679, "kevin@test.com");
        secondStylist.save();
        assertEquals(Stylist.findEmail(secondStylist.getStylistEmail()), secondStylist);
    }

    @Test
    public void getClients_initiallyReturnsEmptyList_ArrayList() {
        Stylist testStylist = new Stylist("mary", 5678, "mary@test.com");
        assertEquals(0, testStylist.getClients().size());
    }

    @Test
    public void equals_returnsTrueIfStylistsAretheSame() {
        Stylist firstStylist = new Stylist("mary", 5678, "mary@test.com");
        Stylist secondStylist = new Stylist("mary", 5678, "mary@test.com");
        assertTrue(firstStylist.equals(secondStylist));
    }

    @Test
    public void save_savesIntoDatabase_true() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }

    @Test
    public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
    }

    @Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        myStylist.save();
        Client firstClient = new Client("john", 1234, "john@test.com", myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("james", 1239, "james@test.com", myStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
    }

    @Test
    public void delete_deletesStylist_true() {
        Stylist myStylist = new Stylist("mary", 5678, "mary@test.com");
        myStylist.save();
        int myStylistId = myStylist.getId();
        myStylist.delete();
        assertEquals(null, Stylist.find(myStylistId));
    }

}