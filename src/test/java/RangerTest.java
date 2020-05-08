import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    private Ranger  testRanger;
    @Test
    public void Ranger_instantiatesCorrectly_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void getName_InstantiatesWithRangerName_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        assertEquals("Rose", testRanger.getName());
    }
    @Test
    public void getBadgeNo_InstantiatesWithBadgeNo_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        assertEquals(12345, testRanger.getBadgeNo());
    }
    @Test
    public void getContact_InstantiatesWithContact_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        assertEquals(0712121212, testRanger.getContact());
    }
    @Test
    public void equals_returnsTrueIfRangersAreSame_true() {
        Ranger firstRanger  = new Ranger("Rose",12345, 0712121212);
        Ranger anotherRanger  = new Ranger("Rose",12345, 0712121212);
        assertTrue(firstRanger.equals(anotherRanger));
    }
    @Test
    public void save_insertsObjectIntoDatabase_Ranger() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
    }
    @Test
    public void all_returnsAllInstancesOfRangers_true() {
        Ranger firstRanger  = new Ranger("Rose",12345, 0712121212);
        firstRanger.save();
        Ranger secondRanger  = new Ranger("Rose",12345, 0712121212);
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        testRanger.save();
        Ranger savedRanger= Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedRanger.getId());
    }
    @Test
    public void find_returnsRangersWithSameId_secondRanger() {
        Ranger firstRanger  = new Ranger("Rose",12345, 0712121212);
        firstRanger.save();
        Ranger secondRanger  = new Ranger("Rose",12345, 0712121212);
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }
    @Test
    public void delete_deletesAllRangers_true() {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        testRanger.save();
        testRanger.delete();
        assertEquals(0, Ranger .all().size());
    }
//    @Test
//    public void getSightings_retrievesAllSightingsFromDatabase_sightingsList() {
//        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
//        testRanger.save();
//        Sighting firstSighting = new Sighting("Fred", "Thriving","Zone A",testRanger.getId());
//        firstSighting.save();
//        Sighting secondSighting = new Sighting("Fred", "Thriving","Zone A",testRanger.getId());
//        secondSighting.save();
//        Sighting [] sightings = new Sighting[]{ firstSighting , secondSighting};
//        assertTrue(testRanger.getSightings(rangerId).containsAll(Arrays.asList(sightings)));
//    }
    @Test
    public void getAllSightingsByRangerReturnsSightingsCorrectly() throws Exception {
        Ranger testRanger  = new Ranger("Rose",12345, 0712121212);
        testRanger.save();
        int rangerId = testRanger .getId();
        Sighting newSighting = new Sighting("Fred", "Thriving","Zone A",rangerId);
        newSighting.save();
        Sighting otherSighting = new Sighting("Fred", "Thriving","Zone A",rangerId );
        otherSighting.save();
        Sighting thirdSighting = new Sighting("Fred", "Thriving","Zone A",rangerId );
        assertEquals(2 ,testRanger.getSightings(rangerId) );
        assertTrue(testRanger .getSightings(rangerId) .contains(newSighting));
        assertTrue(testRanger.getSightings(rangerId) .contains(otherSighting));
        assertFalse(testRanger.getSightings(rangerId).contains(thirdSighting));
    }
}