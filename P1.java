/*
COMP2240 Assignment 2 Problem 1
File: P1.java
Author: Timothy Kemmis
Std no. c3329386
Description: A program to simulate multiple concurrent threads repeating constantly as they wait and one at a time cross a bridge
*/
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class P1 {

    public static void main(String args[]){//Main loop that initialises all the farmer threads and constantly crosses them over the bridge
        Semaphore numOfPasses = new Semaphore(1, true);//A semaphore used to keep track of how and if the bridge is in use
        int neon = 0, numS_Farmers = 0, numN_Farmers = 0;
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            //Getting the input from a file for how many north and south farmers there are
            numN_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);//String manipulation to get the numbers correlating to the number of farmers
            numS_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);//I have assumed there are no mistakes in the input formatting
        } catch (Exception e){
            System.out.println("Exception: " + e);
            System.out.println("Error reading file. Exiting ...");
            return;
        }

        Bridge NZBridge = new Bridge(numOfPasses);//Bridge object for crossing the farmers
        FarmerThread[] N_Farmers = new FarmerThread[numN_Farmers];//Arrays for the north and south farmer threads
        FarmerThread[] S_Farmers = new FarmerThread[numS_Farmers];

        for (int i = 1; i < numN_Farmers+1; i++) {
            N_Farmers[i-1] = new FarmerThread("N_Farmer"+i, false, numOfPasses, neon);//Initialising all the north farmer threads
        }

        for (int i = 1; i < numS_Farmers+1; i++) {
            S_Farmers[i-1] = new FarmerThread("S_Farmer"+i, true, numOfPasses, neon);//Initialising all the south farmer threads
        }

        NZBridge.check(N_Farmers,S_Farmers);//Starts all the threads running which will continue indefinitely
    }
}
