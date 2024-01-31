package main;


/**
 * @author Rickard Cornell, Elissa Arias Sosa, Raahitya Botta, Zaina Ramadan, Jean Perbet
 * Class representing a Logical Connector Matrix (LCM), a 15x15 symmetric matrix with elements
 * of type OR, AND or NOTUSED.
 */
public record LCM(String[][] matrix) {

    // Default value making it easier for testing purposes.
    public static final LCM DEFAULT = new LCM(new String[][]{
        {"OR", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "OR", "AND", "AND", "NOTUSED", "AND", "OR", "NOTUSED", "AND", "OR", "AND"},
        {"NOTUSED", "NOTUSED", "AND", "AND", "NOTUSED", "OR", "AND", "AND", "AND", "OR", "OR", "NOTUSED", "OR", "OR", "AND"},
        {"NOTUSED", "AND", "AND", "NOTUSED", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "AND", "OR", "NOTUSED", "AND", "NOTUSED"},
        {"OR", "AND", "NOTUSED", "NOTUSED", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "AND", "AND", "OR", "NOTUSED", "OR"},
        {"NOTUSED", "NOTUSED", "OR", "OR", "OR", "AND", "AND", "OR", "OR", "AND", "AND", "AND", "AND", "OR", "AND"},
        {"OR", "OR", "OR", "OR", "AND", "OR", "NOTUSED", "AND", "NOTUSED", "AND", "OR", "NOTUSED", "AND", "NOTUSED", "AND"},
        {"AND", "AND", "AND", "AND", "AND", "NOTUSED", "OR", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "AND", "OR", "AND", "AND"},
        {"AND", "AND", "OR", "OR", "OR", "AND", "NOTUSED", "AND", "NOTUSED", "OR", "AND", "NOTUSED", "NOTUSED", "OR", "NOTUSED"},
        {"NOTUSED", "AND", "NOTUSED", "NOTUSED", "OR", "NOTUSED", "NOTUSED", "NOTUSED", "NOTUSED", "AND", "AND", "OR", "NOTUSED", "OR", "NOTUSED"},
        {"AND", "OR", "AND", "AND", "AND", "AND", "OR", "OR", "AND", "AND", "NOTUSED", "NOTUSED", "NOTUSED", "AND", "OR"},
        {"OR", "OR", "AND", "AND", "AND", "OR", "NOTUSED", "AND", "AND", "NOTUSED", "AND", "OR", "OR", "NOTUSED", "AND"},
        {"NOTUSED", "NOTUSED", "OR", "AND", "AND", "NOTUSED", "AND", "NOTUSED", "OR", "NOTUSED", "OR", "AND", "AND", "NOTUSED", "NOTUSED"},
        {"AND", "OR", "NOTUSED", "OR", "AND", "AND", "OR", "NOTUSED", "NOTUSED", "NOTUSED", "OR", "AND", "AND", "AND", "OR"},
        {"OR", "OR", "AND", "NOTUSED", "OR", "NOTUSED", "AND", "OR", "OR", "AND", "NOTUSED", "NOTUSED", "AND", "NOTUSED", "OR"},
        {"AND", "AND", "NOTUSED", "OR", "AND", "AND", "AND", "NOTUSED", "NOTUSED", "OR", "AND", "NOTUSED", "OR", "OR", "OR"}
});

    /**
     * Public constructor for a LCM.
     *
     * @param matrix: the matrix of logical connectors
     */
    public LCM {}

    /**
     * Getter for the LCM.
     *
     * @return the matrix of logical connectors
     */
    @Override
    public String[][] matrix() {
        return matrix;
    }
}
