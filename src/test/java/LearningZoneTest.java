import general.LearningSpace;
import general.LearningZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LearningZoneTest {
    private LearningZone lz;

    @BeforeEach
    void setupLearningZone() {
        lz = new LearningZone("A", "EG", "Z01");
    }

    //LearningZone shouldn't accept any null values in constructor
    @Test
    void testConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new LearningZone(null, "A", "1"));
        assertThrows(IllegalArgumentException.class, () -> new LearningZone("B", null, "1"));
        assertThrows(IllegalArgumentException.class, () -> new LearningZone("B","A", null));
    }

    //tests all getter methods of LearningZone
    @Test
    void testGetterMethods() {
        assertEquals(0, lz.getLearningSpaces().size());
        assertEquals("A", lz.getBuilding());
        assertEquals("EG", lz.getFloor());
        assertEquals("Z01", lz.getConsecutiveNr());
    }

    //test adding a LearningSpace
    @Test
    void testAddLearningSpace() {
        LearningSpace ls1 = new LearningSpace(1, 10);
        LearningSpace ls2 = new LearningSpace(2, 17);
        assertTrue(lz.addLearningSpace(ls1));
        assertTrue(lz.addLearningSpace(ls2));
    }

    //adding a LearningSpace a second time shouldn't be possible
    @Test
    void testAddAlreadyContainedLearningSpace() {
        LearningSpace ls1 = new LearningSpace(1, 10);
        LearningSpace ls2 = new LearningSpace(2, 17);
        lz.addLearningSpace(ls1);
        lz.addLearningSpace(ls2);
        assertFalse(lz.addLearningSpace(ls2));
        assertEquals(2, lz.getLearningSpaces().size());
    }

    //addLearningSpace shouldn't accept null values
    @Test
    void testAddNullLearningSpace() {
        assertThrows(IllegalArgumentException.class, () -> lz.addLearningSpace(null));
    }

    //tests removing a LearningSpace
    @Test
    void testRemoveLearningSpace() {
        LearningSpace ls1 = new LearningSpace(1, 10);
        LearningSpace ls2 = new LearningSpace(2, 17);
        LearningSpace ls3 = new LearningSpace(3, 4);
        lz.addLearningSpace(ls1);
        lz.addLearningSpace(ls2);
        assertTrue(lz.removeLearningSpace(ls1));
        assertEquals(1, lz.getLearningSpaces().size());
        assertTrue(lz.removeLearningSpace(ls2));
        assertFalse(lz.removeLearningSpace(ls2));
        assertFalse(lz.removeLearningSpace(ls3));
    }

    //tests that removing a not contained LearningSpace should return false
    @Test
    void testRemoveNotContainedLearningSpace() {
        LearningSpace ls1 = new LearningSpace(1, 10);
        lz.addLearningSpace(ls1);
        lz.removeLearningSpace(ls1);
        assertFalse(lz.removeLearningSpace(ls1));
    }

    //removeLearningSpace should not accept any null values
    @Test
    void testRemoveNullLearningSpace() {
        assertThrows(IllegalArgumentException.class, () -> lz.removeLearningSpace(null));
    }

    //checks that no Exception is thrown when calling toString
    @Test
    void testToString() {
        LearningSpace ls1 = new LearningSpace(1, 10);
        LearningSpace ls2 = new LearningSpace(2, 17);
        LearningSpace ls3 = new LearningSpace(3, 4);
        lz.addLearningSpace(ls1);
        lz.addLearningSpace(ls2);
        lz.addLearningSpace(ls3);
        assertDoesNotThrow(lz::toString);
    }
}