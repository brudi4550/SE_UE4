import actions.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionTest {

    //checks if default constructor sets creationDateTime correctly
    @Test
    void testDefaultConstructor() {
        Action a = new Action() {};
        assertNotNull(a.getCreationDateTime());
    }

    //Action with null as creationDateTime should throw IllegalArgumentException
    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Action(null) {});
    }
}
