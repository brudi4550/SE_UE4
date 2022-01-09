package actions;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Action {
    private final LocalDateTime creationDateTime;

    public Action() {
        creationDateTime = LocalDateTime.now();
    }

    public Action(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return creationDateTime.equals(action.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDateTime);
    }

}
