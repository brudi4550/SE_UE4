package actions;

import java.time.Duration;
import java.time.LocalDateTime;

public interface Timeable {
    LocalDateTime getStart();
    LocalDateTime getEnd();
    Duration getDuration();
}
