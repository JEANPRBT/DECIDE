package main;

/**
 * @author Rickard Cornell, Elissa Arias Sosa, Raahitya Botta, Zaina Ramadan, Jean Perbet
 * Class representing a Final Unlocking Vector (main.FUV), composed of fifteen elements and resulting
 * from the combination of the Preliminary Unlocking Vector (main.PUV) and Preliminary Unlocking Matrix (main.PUM).
 */
public class FUV {
    private final PUM pum;
    private final PUV puv;
    private final boolean[] fuvVector;

    /**
     * Public constructor for a FUV.
     * @param pum the Preliminary Unlocking Matrix (main.PUM)
     * @param puv the Preliminary Unlocking Vector (main.PUV)
     */
    public FUV(PUM pum, PUV puv){
        this.pum = pum;
        this.puv = puv;
        this.fuvVector = new boolean[15];
        setFUM();
    }

    /**
     * Getter for FUV
     * @return the Final Unlocking Vector (FUV)
     */
    public boolean[] getVector(){
        return fuvVector;
    }

    /**
     * Setter for FUV. Generated from the PUM and PUV.
     * The input PUV indicates whether the corresponding LIC is to be 
     * considered as a factor in signaling interceptor launch.
     */
    private void setFUM(){
        boolean[][] pumMatrix = pum.getMatrix();
        boolean[] puvVector = puv.vector();

        for (int i = 0; i < 15; i++) {
            fuvVector[i] = true;
            if (puvVector[i]) {
                for (int j = 0; j < 15; j++) {
                    if (j != i && !pumMatrix[i][j]) {
                        fuvVector[i] = false;
                        break;                        
                    }
                }
            }
        }
    }
}
