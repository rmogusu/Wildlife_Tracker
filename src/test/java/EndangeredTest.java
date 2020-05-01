import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void EndangeredAnimal_instantiatesWithName_String() {
        Endangered testEndangered = new Endangered("Lion", );
        assertEquals("Lion", testEndangered.getName());
    }

}