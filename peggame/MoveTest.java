package peggame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.commons.annotation.Testable;


@Testable
public class MoveTest {

    @Test
    public void testGetFrom() {
        Location from = new Location(0, 0);
        Location to = new Location(2, 2);
        Move move = new Move(from, to);
        assertEquals(from, move.getFrom());
    }
}
