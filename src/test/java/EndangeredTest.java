import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("Lion", 1);
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void EndangeredAnimal_instantiatesWithName_String() {
        Endangered testEndangered = new Endangered("Lion",1 );
        assertEquals("Lion", testEndangered.getName());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithSightingId_int() {
        Endangered testEndangered = new Endangered("Lion", 1);
        assertEquals(1, testEndangered.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
        Endangered testEndangered = new Endangered("Lion", 1);
        Endangered anotherEndangered = new Endangered("Lion", 1);
        assertTrue(testEndangered.equals(anotherEndangered));
    }

    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        Endangered testEndangered= new Endangered("Lion", 1);
        testEndangered.save();
        Endangered  savedEndangered = Endangered.all().get(0);
        assertEquals(testEndangered.getId(), savedEndangered.getId());

    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        Endangered testEndangered = new Endangered("Lion", 1);
        testEndangered.save();
        Endangered  savedEndangered = Endangered .all().get(0);
        assertEquals(testEndangered.getId(), savedEndangered.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        Endangered firstEndangered = new Endangered("Lion", 1);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Lion", 1);
        secondEndangered.save();
        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
    }
    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
        Endangered firstEndangered = new Endangered("Lion", 1);
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Lion", 1);
        secondEndangered.save();
        assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
    }
    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        testSighting.save();
        Endangered  testEndangered = new Endangered("Lion", testSighting.getId()) ;
        testEndangered.save();
        Endangered  savedEndangered = Endangered.find(testEndangered.getId());
        assertEquals(savedEndangered.getSightingId() , testSighting.getId());
    }
}