import actions.Cancellation;
import actions.Occupancy;
import actions.Reservation;
import general.LearningSpace;
import general.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LearningSpaceTest {
    private LearningSpace ls;

    @BeforeEach
    void setupLearningSpace() {
        ls = new LearningSpace(1, 10);
    }

    //checks that IllegalArgumentException is thrown when given
    //invalid constructor arguments
    @Test
    void testConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new LearningSpace(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new LearningSpace(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new LearningSpace(-1, -1));
    }

    //checks if adding normal reservation is possible
    @Test
    void addReservationTest() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        assertTrue(ls.addReservation(r1));
    }

    //adding the same reservation twice should not be possible
    @Test
    void addAlreadyAddedReservationTest() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        ls.addReservation(r1);
        assertFalse(ls.addReservation(r1));
    }

    //should throw IllegalArgumentException when given null as argument
    @Test
    void addNullReservationTest() {
        assertThrows(IllegalArgumentException.class, () -> ls.addReservation(null));
    }

    //checks if adding normal occupancy is possible
    @Test
    void addOccupancyTest() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        ls.addReservation(r1);
        Occupancy o1 = new Occupancy(r1, LocalDateTime.now(), LocalDateTime.now());
        assertTrue(ls.addOccupancy(o1));
    }

    //you shouldn't be able to add an occupancy if the reservation has been cancelled
    @Test
    void addOccupancyToCancelledReservation() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        Cancellation c1 = new Cancellation(r1, LocalDateTime.now());
        ls.addReservation(r1);
        ls.addCancellation(c1);
        Occupancy o2 = new Occupancy(r1, LocalDateTime.now(), LocalDateTime.now());
        assertFalse(ls.addOccupancy(o2));
    }

    //should throw IllegalArgumentException when given null as argument
    @Test
    void addNullOccupancyTest() {
        assertThrows(IllegalArgumentException.class, () -> ls.addOccupancy(null));
    }

    //checks if adding a normal cancellation is possible
    @Test
    void addCancellationTest() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        ls.addReservation(r1);
        Cancellation c1 = new Cancellation(r1, LocalDateTime.now());
        assertTrue(ls.addCancellation(c1));
    }

    //you shouldn't be able to add a cancellation if there already is an occupancy for the reservation
    @Test
    void addCancellationOnReservationWithOccupancy() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        Occupancy o1 = new Occupancy(r1, LocalDateTime.now(), LocalDateTime.now());
        ls.addReservation(r1);
        ls.addOccupancy(o1);
        Cancellation c2 = new Cancellation(r1, LocalDateTime.now());
        assertFalse(ls.addCancellation(c2));
    }

    //adding null as a cancellation shouldn't be possible.
    @Test
    void addNullCancellationTest() {
        assertThrows(IllegalArgumentException.class, () -> ls.addCancellation(null));
    }

    //tests all getter methods of LearningSpace
    @Test
    void testGetterMethods() {
        assertEquals(1, ls.getNr());
        assertEquals(10, ls.getCapacity());
        assertNotNull(ls.getReservations());
        assertNotNull(ls.getOccupancies());
        assertNotNull(ls.getCancellations());
    }

    //checks that no Exception is thrown when calling toString
    @Test
    void testToString() {
        Student s = new Student("K12345678");
        Reservation r1 = new Reservation("R1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                10,
                s,
                LocalDateTime.now());
        ls.addReservation(r1);
        assertDoesNotThrow(ls::toString);
    }
}
