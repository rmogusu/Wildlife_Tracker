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
        assertTrue(Endangered.all().get(0).equals(testEndangered));
    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        Endangered testEndangered = new Endangered("Lion", 1);
        testEndangered.save();
        assertTrue(Endangered.all().get(0).equals(testEndangered));
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

}