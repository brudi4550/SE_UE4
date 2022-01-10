package actions;

import general.LearningSpace;

import java.util.List;

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
    public List<? extends Action> get(LearningSpace ls) { return null; }
    public List<? extends Timeable> getTimeable(LearningSpace ls) { return null; }
}
