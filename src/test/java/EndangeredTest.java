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

//    @Test
//    public void equals_returnsTrueIfNameAndSightingIdAreSame_true() {
//        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
//    }
//
//    @Test
//    public void save_returnsTrueIfEndangeredAnimalsAreTheSame() {
//        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        testEndangeredAnimal.save();
//        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
//    }
//
//    @Test
//    public void save_assignsIdToEndangeredAnimal() {
//        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        testEndangeredAnimal.save();
//        EndangeredAnimal savedEndangeredAnimal = (EndangeredAnimal) EndangeredAnimal.all().get(0);
//        assertEquals(savedEndangeredAnimal.getId(), testEndangeredAnimal.getId());
//    }
//
//    @Test
//    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
//        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        firstEndangeredAnimal.save();
//        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Lion", 1);
//        secondEndangeredAnimal.save();
//        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
//        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
//    }

}