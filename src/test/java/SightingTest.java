import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_instantiatesCorrectly_true() {
        Sighting testSighting  = new Sighting("Fred", "Endangered","Zone A");
        assertEquals(true, testSighting instanceof Sighting);
    }
    @Test
    public void getRangerName_InstantiatesWithRangerName_true() throws Exception {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        assertEquals("Fred", testSighting.getRangerName());
    }

    @Test
    public void getSpecies_InstantiatesWithSpecies_true() throws Exception {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        assertEquals("Endangered", testSighting.getSpecies());
    }
//    @Test
//    public void getLocation_InstantiatesWithLocation_true() throws Exception {
//        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A");
//        assertEquals("Zone A", testSighting.getLocation());
//    }
//    @Test
//    public void equals_returnsTrueIfSightingsAreSame_true() {
//        Sighting firstSighting = new Sighting("Fred", "Thriving","Zone A");
//        Sighting anotherSighting = new Sighting("Fred", "Thriving","Zone A");
//        assertTrue(firstSighting.equals(anotherSighting));
//    }
//    @Test
//    public void save_insertsObjectIntoDatabase_Sighting() {
//        Sighting testSighting = new Sighting("Fred", "Thriving","Zone D");
//        testSighting.save();
//        assertTrue(Sighting.all().get(0).equals(testSighting));
//    }
//
//    @Test
//    public void all_returnsAllInstancesOfSightings_true() {
//        Sighting firstSighting = new Sighting("Rose", "Thriving","Zone D");
//        firstSighting.save();
//        Sighting secondSighting = new Sighting("Rose", "Endangered","Zone D");
//        secondSighting.save();
//        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
//        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
//    }
//    @Test
//    public void save_assignsIdToObject() {
//        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A");
//        testSighting.save();
//        Sighting savedSighting= Sighting.all().get(0);
//        assertEquals(testSighting.getId(), savedSighting.getId());
//    }
//
//    @Test
//    public void find_returnsSightingsWithSameId_secondSighting() {
//        Sighting firstSighting = new Sighting("Fred", "Endangered","Zone A");
//        firstSighting.save();
//        Sighting secondSighting = new Sighting("Fred", "Thriving","Zone D");
//        secondSighting.save();
//        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
//    }
}