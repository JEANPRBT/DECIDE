package main;

public class Parameters {
    public int numPoints;
    // Default value making it easier for testing purposes.
    public static Parameters DEFAULT = new Parameters(3d, 4d, 0d, 1d, 3, 3,
            1d, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1d,
            1d, 1d, 5);
    public double length1;
    public double radius1;
    public double epsilon;
    public double area1;
    public int q_pts;
    public int quads;
    public double dist;
    public int n_pts;
    public int k_pts;
    public int a_pts;
    public int b_pts;
    public int c_pts;
    public int d_pts;
    public int e_pts;
    public int f_pts;
    public int g_pts;
    public double length2;
    public double radius2;
    public double area2;

    /**
     * Default constructor for Parameters.
     */
    public Parameters(double length1, double radius1, double epsilon, double area1, int q_pts, int quads,
                      double dist, int n_pts, int k_pts, int a_pts, int b_pts, int c_pts, int d_pts, int e_pts,
                      int f_pts, int g_pts, double length2, double radius2, double area2, int numPoints) {

        if (numPoints < 5) throw new IllegalArgumentException("NUMPOINTS must be at least 5.");                    
        if (epsilon < 0 || epsilon >= Math.PI) throw new IllegalArgumentException("Epsilon must be in the range 0-PI.");
        if (length1 < 0) throw new IllegalArgumentException("LENGTH1 should'nt be negative.");
        if (length2 < 0) throw new IllegalArgumentException("LENGTH2 should'nt be negative."); 
        if (area1 < 0) throw new IllegalArgumentException("AREA1 should'nt be negative.");
        if (area2 < 0) throw new IllegalArgumentException("AREA2 should'nt be negative."); 
        if (radius1 < 0) throw new IllegalArgumentException("RADIUS1 should'nt be negative.");
        if (radius2 < 0) throw new IllegalArgumentException("RADIUS2 should'nt be negative.");
        if (q_pts < 2) throw new IllegalArgumentException("Q_PTS must be at least 2.");
        if (k_pts < 1 || k_pts > (numPoints - 2)) throw new IllegalArgumentException("K_PTS must be in the range [1, NUMPOINTS - 2].");
        if (c_pts < 1) throw new IllegalArgumentException("C_PTS must be at least 1.");
        if (d_pts < 1) throw new IllegalArgumentException("D_PTS must be at least 1.");
        if (e_pts < 1) throw new IllegalArgumentException("E_PTS must be at least 1.");
        if (f_pts < 1) throw new IllegalArgumentException("F_PTS must be at least 1.");
        if (c_pts + d_pts > numPoints - 3) throw new IllegalArgumentException("The sum of C_PTS and D_PTS must be less than or equal to NUMPOINTS - 3.");
        if (e_pts + f_pts > numPoints - 3) throw new IllegalArgumentException("The sum of E_PTS and F_PTS must be less than or equal to NUMPOINTS - 3.");
        if (g_pts < 1 || g_pts > numPoints - 2) throw new IllegalArgumentException("G_PTS must be in the range [1, NUMPOINTS - 2].");
        if (quads < 1 || quads > 3) throw new IllegalArgumentException("QUADS must be in the range [1, 3].");
        if (dist < 0) throw new IllegalArgumentException("DIST should'nt be negative.");
       
                       

        this.length1 = length1;
        this.radius1 = radius1;
        this.epsilon = epsilon;
        this.area1 = area1;
        this.q_pts = q_pts;
        this.quads = quads;
        this.dist = dist;
        this.n_pts = n_pts;
        this.k_pts = k_pts;
        this.a_pts = a_pts;
        this.b_pts = b_pts;
        this.c_pts = c_pts;
        this.d_pts = d_pts;
        this.e_pts = e_pts;
        this.f_pts = f_pts;
        this.g_pts = g_pts;
        this.length2 = length2;
        this.radius2 = radius2;
        this.area2 = area2;
        this.numPoints = numPoints;
    }
}