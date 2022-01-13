package general;

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
     */
    public Student(String studentNr) {
        this.studentNr = studentNr;
    }

    /**
     * Getter for the student number.
     * @return the student number.
     */
    public String getStudentNr() {
        return studentNr;
    }

}
