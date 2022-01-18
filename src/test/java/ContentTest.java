import actions.Content;
import general.LearningSpace;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ContentTest {

    //Content value CANCELLATION should return null when the getTimeable method is called
    @Test
    void testGetTimeableOfCancellationContent() {
        assertNull(Content.CANCELLATION.getTimeable(new LearningSpace(1, 10)));
    }

}
