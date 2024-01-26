package main;

public class Parameters{
  
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
     * Default constructor for Parameters, initializing it to pre-defined valid values
     * making it easier for testing purposes.
     */
    public Parameters() {
        this.length1 = 1;
        this.radius1 = 1;
        this.epsilon = 1;
        this.area1 = 1;
        this.q_pts = 3;
        this.quads = 3;
        this.dist = 1;
        this.n_pts = 3;
        this.k_pts = 1;
        this.a_pts = 1;
        this.b_pts = 1;
        this.c_pts = 1;
        this.d_pts = 1;
        this.e_pts = 1;
        this.f_pts = 1;
        this.g_pts = 1;
        this.length2 = 1;
        this.radius2 = 1;
        this.area2 = 1;
    }
    //TODO: add other relevant constructors
}