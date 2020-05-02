import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

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
        UnEndangered testUnEndangered = new UnEndangered("Lion","ill","newborn",1 );
        testUnEndangered.save();
        Endangered  savedUnEndangered = Endangered.all().get(0);
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
        Sighting testSighting = new Sighting("Fred", "UnEndangered","Zone A");
        testSighting.save();
        UnEndangered  testUnEndangered = new UnEndangered("Lion","ill","newborn", testSighting.getId()) ;
        testUnEndangered.save();
        UnEndangered  savedUnEndangered = UnEndangered.find(testUnEndangered.getId());
        assertEquals(savedUnEndangered.getSightingId() , testSighting.getId());
    }
    @Test
    public void getUnEndangered_retrievesAllUnEndangeredFromDatabase_UnEndangeredList() {
        Sighting testSighting = new Sighting("Fred", "UnEndangered","Zone A");
        testSighting.save();
        UnEndangered  firstUnEndangered = new UnEndangered("Lion","ill","newborn", testSighting.getId()) ;
        firstUnEndangered.save();
        UnEndangered  secondUnEndangered = new UnEndangered("Lion","ill","newborn", testSighting.getId()) ;
        secondUnEndangered.save();
        UnEndangered [] unendangered = new UnEndangered[]  { firstUnEndangered , secondUnEndangered  };
        assertTrue(testSighting .getUnEndangered().containsAll(Arrays.asList(unendangered)));
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
}