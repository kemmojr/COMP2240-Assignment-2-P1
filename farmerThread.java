import java.util.concurrent.Semaphore;

public class FarmerThread extends Thread{

    private String name;//farmer ID
    private boolean northbound, crossing, crossed;//boolean variables to denote direction the farmer is going, if they are currently crossing and if they have crossed (finished running)
    private static Semaphore crossingPass;
    private static int neon;
    private static Bridge bridge;


    public FarmerThread(String n, boolean goingNorth, Semaphore s, int nSign, Bridge b){
        name = n;
        northbound = goingNorth;
        crossingPass = s;
        neon = nSign;
        bridge = b;
        if (northbound)
            System.out.println(name + ": Waiting for bridge. Going towards North");
        else
            System.out.println(name + ": Waiting for bridge. Going towards South");
    }

    public FarmerThread(FarmerThread f){
        this.name = f.name;
        this.northbound = !f.northbound;
        this.crossingPass = f.crossingPass;
        this.neon = f.neon;
        this.bridge = f.bridge;

    }

    public String getFName(){
        return name;
    }

    public void changeDirection(){
        northbound = !northbound;
    }

    public boolean isNorthbound(){
        return northbound;
    }


    @Override
    public void run(){
        //Runs the thread to cross the bridge
        //Code to check if the bridge is available to cross and step 20 steps 5 steps at a time
        try {

            boolean cont = true;
            int steps = 0;
            crossing = true;


            for (int i = 0; i < 3; i++) {
                steps += 5;
                System.out.println(name + ": Crossing bridge Step " + steps + ".");
                Thread.sleep(200);
            }
            System.out.println(name + ": Across the bridge.");
            neon++;
            System.out.println("NEON = " + neon);
            crossingPass.release();
            cont = false;



        } catch (Exception e){
            System.out.println("error");
        }

    }
}
