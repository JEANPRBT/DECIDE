package main;


public final class Decide {

  
    private final FUV fuv;

    public Decide(FUV fuv){
        this.fuv = fuv;
        Launch();
    }

    public boolean getBoolean(){
        return Launch();
    }
    private boolean Launch(){
        for (int i = 0; i < 15; i++){
            if (fuv.getVector()[i] == false){
                System.out.println("NO");
                return false;
            }
        }
        System.out.println("YES");
        return true;
    }
}
