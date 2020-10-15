/*
COMP2240 Assignment 2 Problem 1
File: Bridge.java
Author: Timothy Kemmis
Std no. c3329386
*/

import java.util.concurrent.Semaphore;

public class Bridge {
    private static Semaphore crossingPass;//Shared semaphore

    public Bridge(Semaphore s){//Constructor that sets the shared semaphore used for bridge management
        crossingPass = s;
    }

    public void cross(FarmerThread f){//to initiate the thread crossing the bridge

        try {
            crossingPass.acquire();//Semaphore acquiring.
            // As only one Semaphore can be in use at any one time it will utilise the inbuilt queue of the Semaphore to execute the Thread run in a FCFS manner
            f.run();

        } catch (Exception e){
            System.out.println("Cross failed");
        }
    }
}
