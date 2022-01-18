import static org.junit.jupiter.api.Assertions.*;

import actions.Content;
import general.AnalyzerFunctionFactory;
import general.LearningSpace;
import general.LearningZone;
import general.Protocol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class ProtocolTest {

    private Protocol p;

    @BeforeEach
    void setupProtocol() {
        LocalDate d1 = LocalDate.of(2022, 1, 20);
        LocalDate d2 = LocalDate.of(2022, 1, 30);
        p = new Protocol(d1, d2);
    }

    //Protocol should not accept any null values
    //Protocol should also not accept an end DateTime that lies before the start DateTime
    @Test
    void testConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Protocol(null, LocalDate.now()));
        assertThrows(IllegalArgumentException.class, () -> new Protocol(LocalDate.now(), null));
        assertThrows(IllegalArgumentException.class, () -> new Protocol(LocalDate.now().plusDays(1), LocalDate.now()));
        assertDoesNotThrow(() -> new Protocol(LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    //test getter methods of Protocol
    @Test
    void testGetDates() {
        LocalDate d1 = LocalDate.of(2022, 1, 20);
        LocalDate d2 = LocalDate.of(2022, 1, 30);
        assertEquals(d1, p.getFrom());
        assertEquals(d2, p.getTo());
    }

    //tests adding a LearningZone to the Protocol
    @Test
    void testAddLearningZone() {
        LearningZone lz1 = new LearningZone("A", "EG", "Z01");
        LearningZone lz2 = new LearningZone("B", "OG", "Z02");
        assertTrue(p.addLearningZone(lz1));
        assertTrue(p.addLearningZone(lz2));
        assertEquals(2, p.getLearningZones().size());
    }

    //tests that adding a LearningZone a second time should not be possible
    @Test
    void testAddAlreadyAddedLearningZone() {
        LearningZone lz1 = new LearningZone("A", "EG", "Z01");
        LearningZone lz2 = new LearningZone("B", "OG", "Z02");
        p.addLearningZone(lz1);
        p.addLearningZone(lz2);
        assertFalse(p.addLearningZone(lz1));
        assertEquals(2, p.getLearningZones().size());
    }

    //addLearningZone should not accept null as a parameter
    @Test
    void testAddNullLearningZone() {
        assertThrows(IllegalArgumentException.class, () -> p.addLearningZone(null));
    }

    //tests that removing a LearningZone works correctly
    @Test
    void testRemoveLearningZone() {
        LearningZone lz1 = new LearningZone("A", "EG", "Z01");
        LearningZone lz2 = new LearningZone("B", "OG", "Z02");
        LearningZone lz3 = new LearningZone("C", "OG", "Z02");
        p.addLearningZone(lz1);
        p.addLearningZone(lz2);
        assertTrue(p.removeLearningZone(lz1));
        assertEquals(1, p.getLearningZones().size());
        assertFalse(p.removeLearningZone(lz3));
        assertEquals(1, p.getLearningZones().size());
        assertTrue(p.removeLearningZone(lz2));
        assertEquals(0, p.getLearningZones().size());
    }

    //removeLearningZone should not accept null as a parameter
    @Test
    void testRemoveNullLearningZone() {
        assertThrows(IllegalArgumentException.class, () -> p.removeLearningZone(null));
    }

    //test that apply method works as expected
    @Test
    void testApplyFunction() {
        int r = p.apply(l -> 1);
        assertEquals(1, r);
        r = p.apply(List::size);
        assertEquals(p.getLearningZones().size(), r);
        int reservationCount = 0;
        for (LearningZone lz : p.getLearningZones()) {
            for (LearningSpace ls : lz.getLearningSpaces()) {
                reservationCount += ls.getReservations().size();
            }
        }
        int functionReservationCount = p.apply(AnalyzerFunctionFactory.getCountFunction(Content.RESERVATION));
        assertEquals(reservationCount, functionReservationCount);
    }

    //apply method should not be able to take null as a parameter
    @Test
    void testApplyFunctionNull() {
        assertThrows(IllegalArgumentException.class, () -> p.apply(null));
    }

    //test that no Exception gets thrown when toString gets called
    @Test
    void testToString() {
        LearningZone lz1 = new LearningZone("A", "EG", "Z01");
        LearningZone lz2 = new LearningZone("B", "OG", "Z02");
        p.addLearningZone(lz1);
        p.addLearningZone(lz2);
        assertDoesNotThrow(p::toString);
    }
}
