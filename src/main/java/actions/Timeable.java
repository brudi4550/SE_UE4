package actions;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Interface representing something that has a
 * start- and endpoint in time and therefore
 * a certain duration.
 */
public interface Timeable {
    LocalDateTime getStart();
    LocalDateTime getEnd();
    Duration getDuration();
}
