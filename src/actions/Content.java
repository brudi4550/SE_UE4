package actions;

import general.LearningSpace;

import java.util.List;

/**
 * Content enumeration needed for dynamically accessing different lists
 * in a given LearningSpace, which is needed for a flexible FunctionFactory.
 * The Content enum provides two methods get() getTimeable()
 * which need to be overridden by the enum-value if possible.
 * All non-overridden methods will return null.
 *
 * @author GR47
 */
public enum Content {
    RESERVATION {
        public List<Reservation> get(LearningSpace ls) {
            return ls.getReservations();
        }

        public List<? extends Timeable> getTimeable(LearningSpace ls) {
            return ls.getReservations();
        }
    },
    OCCUPANCY {
        public List<Occupancy> get(LearningSpace ls) {
            return ls.getOccupancies();
        }

        public List<? extends Timeable> getTimeable(LearningSpace ls) {
            return ls.getOccupancies();
        }
    },
    CANCELLATION {
        public List<Cancellation> get(LearningSpace ls) {
            return ls.getCancellations();
        }
    };

    public List<? extends Action> get(LearningSpace ls) {
        return null;
    }

    public List<? extends Timeable> getTimeable(LearningSpace ls) {
        return null;
    }
}
