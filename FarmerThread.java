/*
COMP2240 Assignment 2 Problem 1
File: FarmerThread.java
Author: Timothy Kemmis
Std no. c3329386
*/

import java.util.concurrent.Semaphore;

public class FarmerThread extends Thread{//Custom thread class for the farmers

    private String name;//farmer ID
    private boolean northbound;//boolean variables to denote direction the farmer is going, if they are currently crossing and if they have crossed (finished running)
    private static Semaphore crossingPass;//The semaphore which controls access to the bridge
    public static int neon;//counter for how many farmers have crossed the bridge
    private int bridgeLen = 20, stepLen = 5;//The length of the bridge and the amount of steps a farmer takes at a time

    //Constructor that sets the name and direction of the farmer, neon sign and the shared Semaphore
    public FarmerThread(String n, boolean goingNorth, Semaphore s, int nSign){
        name = n;
        northbound = goingNorth;
        crossingPass = s;
        neon = nSign;
        if (northbound)//Outputs the direction that the farmer is waiting to go
            System.out.println(name + ": Waiting for bridge. Going towards North");
        else
            System.out.println(name + ": Waiting for bridge. Going towards South");
    }

    //Changes the direction the farmer is going and prints the new direction they are waiting to cross
    public void changeDirection(){
        northbound = !northbound;
        if (northbound)//Outputs the new direction that the farmer is waiting to go
            System.out.println(name + ": Waiting for bridge. Going towards North");
        else
            System.out.println(name + ": Waiting for bridge. Going towards South");
    }

    @Override//Runs the thread to cross the bridge by stepping 20 steps 5 steps at a time
    public void run(){
        try {
            boolean cont = true;
            while (true){//While loop to constantly cross the threads

                crossingPass.acquire();//Acquire the semaphore token or be added to the semaphore queue
                int steps = 0;
                for (int i = 0; i < (bridgeLen/stepLen)-1; i++) {//For loop that steps across the bridge 5 steps at a time with a 200 Millisecond wait between each step
                    steps += stepLen;
                    System.out.println(name + ": Crossing bridge Step " + steps + ".");
                    Thread.sleep(200);
                }
                System.out.println(name + ": Across the bridge.");
                neon++;
                System.out.println("NEON = " + neon);//update the neon sign once crossed
                crossingPass.release();//Release the semaphore token once crossed
                changeDirection();
            }
        } catch (Exception e){
            System.out.println("error");
        }

    }
}
