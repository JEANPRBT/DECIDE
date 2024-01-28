package main;

public class Parameters {

    // Default value making it easier for testing purposes.
    public static Parameters DEFAULT = new Parameters(1d, 1d, 1d, 1d, 3, 3,
            1d, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1d,
            1d, 1d);
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
                      int f_pts, int g_pts, double length2, double radius2, double area2) {
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
    }
}