import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_instantiatesCorrectly_true() {
        Sighting testSighting  = new Sighting("Fred", "Endangered","Zone A",1);
        assertEquals(true, testSighting instanceof Sighting);
    }
    @Test
    public void getRangerName_InstantiatesWithRangerName_true() throws Exception {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A",1);
        assertEquals("Fred", testSighting.getRangerName());
    }

    @Test
    public void getSpecies_InstantiatesWithSpecies_true() throws Exception {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A",1);
        assertEquals("Endangered", testSighting.getSpecies());
    }
    @Test
    public void getLocation_InstantiatesWithLocation_true() throws Exception {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A",1);
        assertEquals("Zone A", testSighting.getLocation());
    }
    @Test
    public void equals_returnsTrueIfSightingsIdAreSame_true() {
        Sighting firstSighting = new Sighting("Fred", "Endangered","Zone A",1);
        Sighting anotherSighting = new Sighting("Fred", "Endangered","Zone A",1);
        assertTrue(firstSighting.equals(anotherSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone D",1);
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }


    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sighting firstSighting = new Sighting("Rose", "Endangered","Zone D",1);
        firstSighting.save();
        Sighting secondSighting = new Sighting("Rose", "Endangered","Zone D",1);
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }
    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A",1);
        testSighting.save();
        Sighting savedSighting= Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingsWithSameId_secondSighting() {
        Sighting firstSighting = new Sighting("Fred", "Endangered","Zone A",1);
        firstSighting.save();
        Sighting secondSighting = new Sighting("Fred", "Thriving","Zone D",1);
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }
    @Test
    public void delete_deletesSighting_true() {
        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A",1);
        testSighting.save();
        testSighting.delete();
        assertEquals(0, Sighting .all().size());
    }
    @Test
    public void getAnimals_retrievesAllAnimalsFromDatabase_monstersList() {
        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A",1);
        testSighting.save();
        Endangered firstEndangered = new Endangered("Lion","ill","newborn", testSighting.getId());
        firstEndangered.save();
        UnEndangered secondUnEndangered = new UnEndangered("Lion","ill","newborn",testSighting.getId() );
        secondUnEndangered.save();
        Object  [] animals = new Object [] { firstEndangered , secondUnEndangered};
        assertTrue(testSighting.getAnimals().containsAll(Arrays.asList(animals)));
    }
    @Test
    public void sighting_instantiatesWithRangerId_int() {
        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A",1);
        assertEquals(1, testSighting.getRangerId());
    }
    @Test
    public void save_savesRangerIdIntoDB_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        testRanger.save();
        Sighting testSighting = new Sighting("Fred", "Thriving","Zone A",testRanger.getId());
        testSighting.save();
        Sighting  savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getRangerId(), testRanger.getId());
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sighting testSighting = new Sighting("Rose", "Endangered", "Zone D",1);
        testSighting.save();
        String savedSightingTimeSpotted = Sighting.find(testSighting.getId()).getTimeSpotted();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedSightingTimeSpotted));

    }
}