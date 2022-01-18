package general;

import java.util.Objects;

/**
 * Class representing a student.
 *
 * @author GR47
 */
public class Student {
    /**
     * The student number of the student.
     */
    private final String studentNr;

    /**
     * Constructor for a Student. Parameter is not checked, caller needs to
     * check beforehand if the argument is valid.
     * @param studentNr the student number
     * @throws IllegalArgumentException if studentNr is null or not in the form of
     */
    public Student(String studentNr) {
        if(studentNr == null)
            throw new IllegalArgumentException("studentNr can't be null");
        if(!studentNr.matches("^K[0-9]{8}$"))
            throw new IllegalArgumentException("studentNr not in the correct format.");
        this.studentNr = studentNr;
    }

    /**
     * Getter for the student number.
     * @return the student number.
     */
    public String getStudentNr() {
        return studentNr;
    }

    /**
     * Checks for equality based on the student number.
     * @param o the object to compare the student to.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentNr, student.studentNr);
    }
}
