package main;

public record PUV(boolean[] vector) {
    public static final PUV DEFAULT = new PUV(new boolean[]{
        true, false, true, false, true, false, true, false, true, false, true, false, true, false, true
    });

}
