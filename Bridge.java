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

    //A method that starts all the threads
    public void check(FarmerThread[] N_Farmers, FarmerThread[] S_Farmers){

        int numN = N_Farmers.length, numS = S_Farmers.length, count = 0;

        while (count<=numN||count<=numS){//While loop that starts all the threads, alternating between starting north and south farmers
            if (count<numN){
                N_Farmers[count].start();
            }
            if (count<numS){
                S_Farmers[count].start();
            }

            count++;
        }

    }
}
