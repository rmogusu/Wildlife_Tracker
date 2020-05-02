import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void EndangeredAnimal_instantiatesWithName_String() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals("Lion", testEndangered.getName());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithHealth_String() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals("ill", testEndangered.getHealth() );
    }
    @Test
    public void EndangeredAnimal_instantiatesWithAge_String() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals("newborn", testEndangered.getAge());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithSightingId_int() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(1, testEndangered.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        Endangered anotherEndangered = new Endangered("Lion","ill","newborn", 1);
        assertTrue(testEndangered.equals(anotherEndangered));
    }

    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered.save();
        Endangered  savedEndangered = Endangered.all().get(0);
        assertEquals(testEndangered.getId(), savedEndangered.getId());

    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered.save();
        Endangered  savedEndangered = Endangered .all().get(0);
        assertEquals(testEndangered.getId(), savedEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        Endangered firstEndangered = new Endangered("Lion","ill","newborn", 1);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Lion","ill","newborn", 1);
        secondEndangered.save();
        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
    }
    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
        Endangered firstEndangered = new Endangered("Lion","ill","newborn", 1);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Lion","ill","newborn", 1);
        secondEndangered.save();
        assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
    }
    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        testSighting.save();
        Endangered  testEndangered = new Endangered("Lion","ill","newborn", testSighting.getId()) ;
        testEndangered.save();
        Endangered  savedEndangered = Endangered.find(testEndangered.getId());
        assertEquals(savedEndangered.getSightingId() , testSighting.getId());
    }
    @Test
    public void getEndangered_retrievesAllEndangeredFromDatabase_EndangeredList() {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        testSighting.save();
        Endangered  firstEndangered = new Endangered("Lion","ill","newborn", testSighting.getId()) ;
        firstEndangered.save();
        Endangered  secondEndangered = new Endangered("Lion","ill","newborn", testSighting.getId()) ;
        secondEndangered.save();
        Endangered [] endangered = new Endangered[]  { firstEndangered , secondEndangered  };
        assertTrue(testSighting .getEndangered().containsAll(Arrays.asList(endangered)));
    }
    @Test
    public void endangered_instantiatesWithHalfFullHealthLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getHealthLevel(), (Endangered .MAX_HEALTH_LEVEL / 2));
    }
    @Test
    public void endangered_instantiatesWithHalfFullsIllLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getIllLevel(), (Endangered .MAX_ILL_LEVEL / 2));
    }
    @Test
    public void endangered_instantiatesWithHalfFullsOkayLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getOkayLevel(), (Endangered .MAX_OKAY_LEVEL / 2));
    }
    @Test
    public void endangered_instantiatesWithHalfFullAdultLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getAdultLevel(), (Endangered .MAX_ADULT_LEVEL / 2));
    }
    @Test
    public void endangered_instantiatesWithHalfFullsYoungLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getYoungLevel(), (Endangered .MAX_YOUNG_LEVEL / 2));
    }
    @Test
    public void endangered_instantiatesWithHalfFullsNewbornLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.getNewbornLevel(), (Endangered .MAX_NEWBORN_LEVEL / 2));
    }
    @Test
    public void isAlive_confirmsEndangeredAnimalIsAliveIfAllLevelsAboveMinimum_true(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered.isAlive(), true);
    }
    @Test
    public void depleteLevels_reducesAllLevels(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .depleteLevels();
        assertEquals(testEndangered .getHealthLevel(), (Endangered.MAX_HEALTH_LEVEL / 2) - 1);
        assertEquals(testEndangered .getIllLevel(), (Endangered .MAX_ILL_LEVEL / 2) - 1);
        assertEquals(testEndangered .getOkayLevel(), (Endangered.MAX_OKAY_LEVEL / 2) - 1);
        assertEquals(testEndangered .getAdultLevel(), (Endangered.MAX_ADULT_LEVEL / 2) - 1);
        assertEquals(testEndangered .getYoungLevel(), (Endangered .MAX_YOUNG_LEVEL / 2) - 1);
        assertEquals(testEndangered .getNewbornLevel(), (Endangered.MAX_NEWBORN_LEVEL / 2) - 1);
    }
    @Test
    public void isAlive_recognizesEndangeredAnimalIsDeadWhenLevelsReachMinimum_false(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered.MIN_ALL_LEVELS; i <= Endangered.MAX_HEALTH_LEVEL; i++){
            testEndangered.depleteLevels();
        }
        assertEquals(testEndangered .isAlive(), false);
    }
    @Test
    public void ill_increasesEndangeredIllLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .ill();
        assertTrue(testEndangered .getIllLevel() > (Endangered .MAX_ILL_LEVEL / 2));
    }
    @Test
    public void okay_increasesEndangeredOkayLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .okay();
        assertTrue(testEndangered .getOkayLevel() > (Endangered .MAX_OKAY_LEVEL / 2));
    }
    @Test
    public void health_increasesEndangeredHealthLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .health();
        assertTrue(testEndangered .getHealthLevel() > (Endangered .MAX_HEALTH_LEVEL / 2));
    }
    @Test
    public void endangered_HealthLevelCannotGoBeyondMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_HEALTH_LEVEL + 2); i++){
            try {
                testEndangered .health();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testEndangered .getHealthLevel() <= Endangered .MAX_HEALTH_LEVEL);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void health_throwsExceptionIfHealthLevelIsAtMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_HEALTH_LEVEL); i++){
            testEndangered.health();
        }
    }
    @Test
    public void endangered_IllLevelCannotGoBeyondMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_ILL_LEVEL + 2); i++){
            try {
                testEndangered .ill();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testEndangered .getIllLevel() <= Endangered .MAX_ILL_LEVEL);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void ill_throwsExceptionIfIllLevelIsAtMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_ILL_LEVEL); i++){
            testEndangered.ill();
        }
    }
    @Test
    public void endangered_OkayLevelCannotGoBeyondMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_OKAY_LEVEL + 2); i++){
            try {
                testEndangered .okay();
            } catch (UnsupportedOperationException exception){ }

        }
        assertTrue(testEndangered .getOkayLevel() <= Endangered .MAX_OKAY_LEVEL);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void okay_throwsExceptionIfOkayLevelIsAtMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered .MIN_ALL_LEVELS; i <= (Endangered .MAX_OKAY_LEVEL); i++){
            testEndangered.okay();
        }
    }
    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .save();
        Timestamp savedEndangeredTimeSpotted = Endangered.find(testEndangered.getId()).getTimeSpotted();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedEndangeredTimeSpotted.getDay());
    }
    @Test
    public void Endangered_recordsTimeLastHealthInDatabase() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered.save();
        testEndangered.health();
        Timestamp savedEndangeredLastHealth = Endangered.find(testEndangered.getId()).getLastHealth();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedEndangeredLastHealth));
    }
    @Test
    public void Endangered_recordsTimeLastIllInDatabase() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered.save();
        testEndangered.ill();
        Timestamp savedEndangeredLastIll = Endangered.find(testEndangered.getId()).getLastIll();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedEndangeredLastIll));
    }
    @Test
    public void Endangered_recordsTimeLastOkayInDatabase() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered.save();
        testEndangered.okay();
        Timestamp savedEndangeredLastOkay = Endangered.find(testEndangered.getId()).getLastOkay();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedEndangeredLastOkay));
    }
    @Test
    public void timer_executesDepleteLevelsMethod() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        int firstHealthLevel = testEndangered.getHealthLevel();
        testEndangered .startTimer();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException exception){}
        int secondHealthLevel = testEndangered.getHealthLevel();
        assertTrue(firstHealthLevel > secondHealthLevel);
    }
    @Test
    public void timer_haltsAfterEndangeredAnimalDies() {
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        testEndangered .startTimer();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException exception){}
        assertFalse(testEndangered .isAlive());
        assertTrue(testEndangered .getHealthLevel()>= 0);
    }
    @Test
    public void Endangered_instantiatesWithHalfFullEndangeredLevel(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        assertEquals(testEndangered .getEndangeredLevel(), (Endangered.MAX_ENDANGERED_LEVEL / 2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saving_throwsExceptionIfEndangeredLevelIsAtMaxValue(){
        Endangered testEndangered = new Endangered("Lion","ill","newborn", 1);
        for(int i = Endangered.MIN_ALL_LEVELS; i <= (Endangered .MAX_ENDANGERED_LEVEL); i++){
            testEndangered.saving();
        }
    }
}