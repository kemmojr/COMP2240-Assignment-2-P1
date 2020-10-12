import java.util.concurrent.Semaphore;

public class tLogic implements Runnable {

    String name;//farmer ID
    boolean northbound, crossing = false, crossed;//boolean variables to denote which direction the farmer is going. 2 are used instead of one for code clarity
    int bridgeLen = 20, stepLen = 5, steps = 0;
    static Semaphore crossingpass;

    public tLogic(String n, boolean goingNorth, Semaphore s){//Constructor that initialises farmer name, their direction, and the shared semaphore
        name = n;
        northbound = goingNorth;
        crossingpass = s;
        crossed = false;
    }

    public boolean getCrossed(){
        return crossed;
    }


    @Override
    public void run() {//Runs the thread to cross the bridge
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
                } else {
                    if (northbound)
                        System.out.println(name + ": Waiting for bridge. Going towards North");
                    else
                        System.out.println(name + ": Waiting for bridge. Going towards South");
                    Thread.sleep(100);
                }
            }
            crossed = true;

        } catch (Exception e){
            System.out.println("error");
        }

    }
}
