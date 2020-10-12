import java.util.concurrent.Semaphore;

public class farmerThread extends Thread{

    private String name;//farmer ID
    private boolean northbound, crossing = false, crossed;//boolean variables to denote direction the farmer is going, if they are currently crossing and if they have crossed (finished running)
    private int bridgeLen = 20, stepLen = 5, steps = 0;
    private static Semaphore crossingpass;

    public farmerThread(String n, boolean goingNorth, Semaphore s){
        name = n;
        northbound = goingNorth;
        crossingpass = s;
        crossed = false;
    }

    public String getFName(){
        return name;
    }

    public boolean hasCrossed(){
        return crossed;
    }

    public boolean isNorthbound(){
        return northbound;
    }


    public void run(){
        //Runs the thread to cross the bridge
        //Code to check if the bridge is available to cross and step 20 steps 5 steps at a time
        try {
            boolean cont = true;

            while (cont){
                if (crossingpass.availablePermits()>0||crossing){
                    if (northbound)
                        System.out.println(name + ": Waiting for bridge. Going towards North");
                    else
                        System.out.println(name + ": Waiting for bridge. Going towards South");
                    crossingpass.acquire();
                    crossing = true;
                    for (int i = 0; i < bridgeLen-5; i+=stepLen) {
                        steps += stepLen;
                        System.out.println(name + ": Crossing bridge Step " + steps + ".");
                        Thread.sleep(200);
                    }
                    System.out.println(name + ": Across the bridge.");
                    northbound = !northbound;//Switch the direction the farmer is heading
                    crossing = false;
                    crossingpass.release();
                    cont = false;
                }
            }
            crossed = true;

        } catch (Exception e){
            System.out.println("error");
        }

    }
}
