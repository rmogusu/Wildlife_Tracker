import org.junit.Rule;
import org.junit.Test;

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
        Sighting testSighting = new Sighting("Fred", "Endangered","Zone A");
        testSighting.save();
        UnEndangered  testUnEndangered = new UnEndangered("Lion","ill","newborn", testSighting.getId()) ;
        testUnEndangered.save();
        UnEndangered  savedUnEndangered = UnEndangered.find(testUnEndangered.getId());
        assertEquals(savedUnEndangered.getSightingId() , testSighting.getId());
    }
}