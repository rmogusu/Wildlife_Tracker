import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class UnEndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void UnEndangeredAnimal_instantiatesCorrectly_true() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertEquals(true, testUnEndangered instanceof UnEndangered);
    }

    @Test
    public void UnEndangeredAnimal_instantiatesWithName_String() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertEquals("Lion", testUnEndangered.getName());
    }
    @Test
    public void UnEndangeredAnimal_instantiatesWithHealth_String() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertEquals("ill", testUnEndangered.getHealth() );
    }
    @Test
    public void UnEndangeredAnimal_instantiatesWithAge_String() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertEquals("newborn", testUnEndangered.getAge() );
    }
    @Test
    public void UnEndangeredAnimal_instantiatesWithSightingId_int() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertEquals(1, testUnEndangered.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        UnEndangered anotherUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        assertTrue(testUnEndangered.equals(anotherUnEndangered));
    }

    @Test
    public void save_successfullyAddsUnEndangeredAnimalToDatabase_List() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered.save();
        UnEndangered  savedUnEndangered = UnEndangered.all().get(0);
        assertEquals(testUnEndangered.getId(), savedUnEndangered.getId());
    }


    @Test
    public void save_assignsIdToUnEndangeredAnimal() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        testUnEndangered.save();
        UnEndangered  savedUnEndangered = UnEndangered .all().get(0);
        assertEquals(testUnEndangered.getId(), savedUnEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfUnEndangeredAnimal_true() {
        UnEndangered firstUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        firstUnEndangered.save();
        UnEndangered secondUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        secondUnEndangered.save();
        assertEquals(true, UnEndangered.all().get(0).equals(firstUnEndangered));
        assertEquals(true, UnEndangered.all().get(1).equals(secondUnEndangered));
    }
    @Test
    public void find_returnsUnEndangeredAnimalWithSameId_secondUnEndangeredAnimal() {
        UnEndangered firstUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        firstUnEndangered.save();
        UnEndangered secondUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        secondUnEndangered.save();
        assertEquals(UnEndangered.find(secondUnEndangered.getId()), secondUnEndangered);
    }
    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting("Fred", "UnEndangered","Zone A",1);
        testSighting.save();
        UnEndangered  testUnEndangered = new UnEndangered("Lion","ill","newborn", testSighting.getId()) ;
        testUnEndangered.save();
        UnEndangered  savedUnEndangered = UnEndangered.find(testUnEndangered.getId());
        assertEquals(savedUnEndangered.getSightingId() , testSighting.getId());
    }

    @Test
    public void unEndangered_instantiatesWithHalfFullHealthLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getHealthLevel(), (UnEndangered .MAX_HEALTH_LEVEL / 2));
    }
    @Test
    public void unEndangered_instantiatesWithHalfFullsIllLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getIllLevel(), (UnEndangered .MAX_ILL_LEVEL / 2));
    }
    @Test
    public void unEndangered_instantiatesWithHalfFullsOkayLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getOkayLevel(), (UnEndangered .MAX_OKAY_LEVEL / 2));
    }
    @Test
    public void unEndangered_instantiatesWithHalfFullAdultLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getAdultLevel(), (UnEndangered .MAX_ADULT_LEVEL / 2));
    }
    @Test
    public void unEndangered_instantiatesWithHalfFullsYoungLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getYoungLevel(), (UnEndangered .MAX_YOUNG_LEVEL / 2));
    }
    @Test
    public void unEndangered_instantiatesWithHalfFullsNewbornLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.getNewbornLevel(), (UnEndangered .MAX_NEWBORN_LEVEL / 2));
    }
    @Test
    public void isAlive_confirmsUnEndangeredAnimalIsAliveIfAllLevelsAboveMinimum_true(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered.isAlive(), true);
    }
    @Test
    public void depleteLevels_reducesAllLevels(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .depleteLevels();
        assertEquals(testUnEndangered .getHealthLevel(), (UnEndangered.MAX_HEALTH_LEVEL / 2) - 1);
        assertEquals(testUnEndangered .getIllLevel(), (UnEndangered .MAX_ILL_LEVEL / 2) - 1);
        assertEquals(testUnEndangered .getOkayLevel(), (UnEndangered.MAX_OKAY_LEVEL / 2) - 1);
        assertEquals(testUnEndangered .getAdultLevel(), (UnEndangered.MAX_ADULT_LEVEL / 2) - 1);
        assertEquals(testUnEndangered .getYoungLevel(), (UnEndangered .MAX_YOUNG_LEVEL / 2) - 1);
        assertEquals(testUnEndangered .getNewbornLevel(), (UnEndangered.MAX_NEWBORN_LEVEL / 2) - 1);
    }
    @Test
    public void isAlive_recognizesUnEndangeredAnimalIsDeadWhenLevelsReachMinimum_false(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered.MIN_ALL_LEVELS; i <= UnEndangered.MAX_HEALTH_LEVEL; i++){
            testUnEndangered.depleteLevels();
        }
        assertEquals(testUnEndangered .isAlive(), false);
    }
    @Test
    public void ill_increasesUnEndangeredIllLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .ill();
        assertTrue(testUnEndangered .getIllLevel() > (UnEndangered .MAX_ILL_LEVEL / 2));
    }
    @Test
    public void okay_increasesUnEndangeredOkayLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .okay();
        assertTrue(testUnEndangered .getOkayLevel() > (UnEndangered .MAX_OKAY_LEVEL / 2));
    }
    @Test
    public void health_increasesUnEndangeredHealthLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .health();
        assertTrue(testUnEndangered .getHealthLevel() > (UnEndangered .MAX_HEALTH_LEVEL / 2));
    }
    @Test
    public void unEndangered_HealthLevelCannotGoBeyondMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_HEALTH_LEVEL + 2); i++){

            try {
                testUnEndangered .health();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testUnEndangered .getHealthLevel() <= UnEndangered .MAX_HEALTH_LEVEL);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void health_throwsExceptionIfHealthLevelIsAtMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_HEALTH_LEVEL); i++){
            testUnEndangered.health();
        }
    }
    @Test(expected = UnsupportedOperationException.class)
    public void ill_throwsExceptionIfIllLevelIsAtMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_ILL_LEVEL); i++){
            testUnEndangered.ill();
        }
    }
    @Test
    public void unEndangered_IllLevelCannotGoBeyondMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_ILL_LEVEL + 2); i++){

            try {
                testUnEndangered .ill();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testUnEndangered .getIllLevel() <= UnEndangered .MAX_ILL_LEVEL);
    }
    @Test
    public void unEndangered_OkayLevelCannotGoBeyondMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_OKAY_LEVEL + 2); i++){

            try {
                testUnEndangered .okay();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testUnEndangered .getOkayLevel() <= UnEndangered .MAX_OKAY_LEVEL);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void okay_throwsExceptionIfOkayLevelIsAtMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered .MIN_ALL_LEVELS; i <= (UnEndangered .MAX_OKAY_LEVEL); i++){
            testUnEndangered.okay();
        }
    }
    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .save();
        Timestamp savedUnEndangeredTimeSpotted = UnEndangered.find(testUnEndangered.getId()).getTimeSpotted();
        Timestamp rightNow = new Timestamp(new Date().getTime());
       assertEquals(rightNow.getDay(), savedUnEndangeredTimeSpotted.getDay());
    }
//    @Test
//    public void save_recordsTimeOfCreationInDatabase() {
//        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
//        testUnEndangered .save();
//        String  savedUnEndangeredTimeSpotted = UnEndangered.find(testUnEndangered.getId()).getTimeSpotted();
//        Timestamp rightNow = new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedUnEndangeredTimeSpotted));
//
//    }
    @Test
    public void unEndangered_recordsTimeLastHealthyInDatabase() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered.save();
        testUnEndangered.health();
        Timestamp savedUnEndangeredLastHealth = UnEndangered.find(testUnEndangered.getId()).getLastHealth();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedUnEndangeredLastHealth));
    }
    @Test
    public void unEndangered_recordsTimeLastIllInDatabase() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered.save();
        testUnEndangered.ill();
        Timestamp savedUnEndangeredLastIll = UnEndangered.find(testUnEndangered.getId()).getLastIll();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedUnEndangeredLastIll));
    }
    @Test
    public void unEndangered_recordsTimeLastOkayInDatabase() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered.save();
        testUnEndangered.okay();
        Timestamp savedUnEndangeredLastOkay = UnEndangered.find(testUnEndangered.getId()).getLastOkay();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedUnEndangeredLastOkay));
    }
    @Test
    public void timer_executesDepleteLevelsMethod() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        int firstHealthLevel = testUnEndangered.getHealthLevel();
        testUnEndangered .startTimer();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException exception){}
        int secondHealthLevel = testUnEndangered.getHealthLevel();
        assertTrue(firstHealthLevel > secondHealthLevel);
    }
    @Test
    public void timer_haltsAfterEndangeredAnimalDies() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered .startTimer();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException exception){}
        assertFalse(testUnEndangered .isAlive());
        assertTrue(testUnEndangered .getHealthLevel()>= 0);
    }
    @Test
    public void UnEndangered_instantiatesWithHalfFullUnEndangeredLevel(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        assertEquals(testUnEndangered .getUnEndangeredLevel(), (UnEndangered.MAX_UNENDANGERED_LEVEL / 2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saving_throwsExceptionIfEndangeredLevelIsAtMaxValue(){
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        for(int i = UnEndangered.MIN_ALL_LEVELS; i <= (UnEndangered .MAX_UNENDANGERED_LEVEL); i++){
            testUnEndangered.conserve();
        }
    }
//    @Test
//    public void endangered_recordsTimeLastEndangeredInDatabase() {
//        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
//        testUnEndangered .save();
//        testUnEndangered.conserve();
//        Timestamp savedUnEndangeredLastConserved = UnEndangered.find(testUnEndangered.getId()).getLastConserved();
//        Timestamp rightNow = new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedUnEndangeredLastConserved));
//    }
    @Test
    public void delete_deletesEndangeredAnimal_true() {
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn", 1);
        testUnEndangered.save();
        testUnEndangered.delete();
        assertEquals(null, UnEndangered .find(testUnEndangered.getId()));
    }
}