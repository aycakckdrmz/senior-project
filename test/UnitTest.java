import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    Unit unit = new Unit();
    @Test
    public void stdAssertions(){
        assertEquals(unit.sum(42,5), 47);
    }


}