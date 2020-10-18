/*
COMP2240 Assignment 2 Problem 1
File: Bridge.java
Author: Timothy Kemmis
Std no. c3329386
*/

import java.util.concurrent.Semaphore;

public class Bridge {//Bridge class used for continually crossing the farmer
    private static Semaphore crossingPass;//Shared semaphore

    //Constructor that initialises the shared semaphore in the Bridge class
    public Bridge(Semaphore s){//Constructor that sets the shared semaphore used for bridge management
        crossingPass = s;
    }

    public void check(FarmerThread[] N_Farmers, FarmerThread[] S_Farmers){
        for (FarmerThread f: N_Farmers){
            f.start();
        }
        for (FarmerThread f: S_Farmers){
            f.start();
        }
    }

    public void cross(FarmerThread f){//Initiates the thread crossing the bridge by acquiring the semaphore and running the thread
        try {
            crossingPass.acquire();//Semaphore acquiring.
            // As only one Semaphore can be in use at any one time it will utilise the inbuilt queue of the Semaphore to execute the Thread run in a FCFS manner
            f.run();

        } catch (Exception e){
            System.out.println("Cross failed");
        }
    }
}
