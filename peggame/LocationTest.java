package peggame;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class LocationTest {

    @Test
    public void testLocationEquals() {
        Location firstLocation = new Location(2, 3);
        Location secondLocation = new Location(2, 3);
        Location thirdLocation = new Location(3, 4);

 
        assertTrue(firstLocation.equals(secondLocation));

        assertFalse(firstLocation.equals(thirdLocation));
    }
}

    

