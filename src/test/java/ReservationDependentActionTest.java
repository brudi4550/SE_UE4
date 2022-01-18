import actions.ReservationDependentAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationDependentActionTest {

    //null shouldn't be accepted as a parameter
    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ReservationDependentAction(null) {});
    }
}
