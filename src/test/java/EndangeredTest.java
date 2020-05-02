import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

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
}