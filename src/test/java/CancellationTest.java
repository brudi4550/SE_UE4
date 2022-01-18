import actions.Cancellation;
import actions.Reservation;
import general.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CancellationTest {

    //checks if toString throws any Exceptions
    @Test
    void testCancellationToString() {
        Reservation r1 = new Reservation("R1", LocalDateTime.now(), LocalDateTime.now(), 10,
                new Student("K12345678"), LocalDateTime.now());
        Cancellation c1 = new Cancellation(r1, LocalDateTime.now());
        assertDoesNotThrow(c1::toString);
    }

}
