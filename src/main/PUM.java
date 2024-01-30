package main;

/**
 * @author Rickard Cornell, Elissa Arias Sosa, Raahitya Botta, Zaina Ramadan, Jean Perbet
 * Class representing a Preliminary Unlocking Matrix (PUM), a 15x15 symmetric matrix which combines
 * both the Logical Connector Matrix (LCM) and the Conditions Met Vector (main.CMV).
 */
public class PUM {

        private final LCM lcm;
        private final CMV cmv;

        private final boolean[][] matrix;

        /**
        * Public constructor for a PUM.
        * @param lcm the Logical Connector Matrix (LCM)
        * @param cmv the Conditions Met Vector (CMV)
        */
        public PUM(LCM lcm, CMV cmv){
                this.lcm = lcm;
                this.cmv = cmv;
                this.matrix = new boolean[15][15];
                setPUM();
        }

        /**
         * Public constructor to directly set the values of PUM
         * for testing purposes
         * @param lcm
         * @param cmv
         * @param matrix
         */
        public PUM(LCM lcm, CMV cmv, boolean[][] matrix){
                this.lcm = lcm;
                this.cmv = cmv;
                this.matrix = matrix;
        }

        /**
         * Getter for the PUM.
         * @return the Preliminary Unlocking Matrix (PUM)
         */
        public boolean[][] getMatrix(){
                return matrix;
        }

        /*
         * Setter for the PUM. This method uses the LCM and CMV to set the PUM.
         * The matrix is symmetric, so we only need to set the upper triangle and
         * replicate the found value on the lower triangle.
         */
        private void setPUM(){
                String[][] lcmMatrix = lcm.matrix();
                boolean[] cmvVector = cmv.getVector();

                for (int i = 0; i < 15; i++){
                        for (int j = 0; j <= i; j++){
                                if (lcmMatrix[i][j].equals("AND")){
                                        matrix[i][j] = cmvVector[i] && cmvVector[j];
                                } else if (lcmMatrix[i][j].equals("OR")){
                                        matrix[i][j] = cmvVector[i] || cmvVector[j];
                                } else {
                                        matrix[i][j] = true;
                                }
                                matrix[j][i] = matrix[i][j];
                        }
                }
        }
}
