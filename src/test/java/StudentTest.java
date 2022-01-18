import general.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student s;

    @BeforeEach
    void setupStudent() {
        s = new Student("K12345678");
    }

    //test getter method of Student
    @Test
    void testGetStudentNr() {
        assertEquals("K12345678", s.getStudentNr());
    }

    //test that no Exception gets thrown when calling toString
    @Test
    void testToString() {
        assertDoesNotThrow(s::toString);
    }

    //test that only the correct student number format gets accepted as a value
    @Test
    void testCorrectFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Student("K123456789"));
        assertThrows(IllegalArgumentException.class, () -> new Student("12345678"));
        assertThrows(IllegalArgumentException.class, () -> new Student(""));
        assertThrows(IllegalArgumentException.class, () -> new Student(null));
        assertThrows(IllegalArgumentException.class, () -> new Student("K1234567"));
        assertThrows(IllegalArgumentException.class, () -> new Student("k12345678"));
    }
}
