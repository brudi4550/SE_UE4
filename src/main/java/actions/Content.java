package actions;

import general.LearningSpace;

import java.util.List;

/**
 * Content enumeration needed for dynamically accessing different lists
 * in a given LearningSpace, which is needed for a flexible FunctionFactory.
 * The Content enum provides two abstract methods get() and getTimeable()
 * which need to be overridden by the enum-value.
 *
 * @author GR47
 */
public enum Content {
    RESERVATION {
        @Override
        public List<Reservation> get(LearningSpace ls) {
            return ls.getReservations();
        }

        @Override
        public List<? extends Timeable> getTimeable(LearningSpace ls) {
            return ls.getReservations();
        }
    },
    OCCUPANCY {
        @Override
        public List<Occupancy> get(LearningSpace ls) {
            return ls.getOccupancies();
        }

        @Override
        public List<? extends Timeable> getTimeable(LearningSpace ls) {
            return ls.getOccupancies();
        }
    },
    CANCELLATION {
        @Override
        public List<Cancellation> get(LearningSpace ls) {
            return ls.getCancellations();
        }

        @Override
        public List<? extends Timeable> getTimeable(LearningSpace ls) {
            return null;
        }
    };

    public abstract List<? extends Action> get(LearningSpace ls);

    public abstract List<? extends Timeable> getTimeable(LearningSpace ls);
}
