/*
COMP2240 Assignment 2 Problem 1
File: P1.java
Author: Timothy Kemmis
Std no. c3329386
*/
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class P1 {

    public static void main(String args[]) throws InterruptedException {//look into parallel
        Semaphore numOfPasses = new Semaphore(1);//A semaphore used to keep track of how if the bridge is in use
        //numOfPasses.release();//increments +1 to permits counter
        //numOfPasses.acquire();//decrements -1 to permits counter
        int neon = 0, count = 0, numS_Farmers = 0, numN_Farmers = 0;
        boolean allCrossed = false;
        String in;
        String[] input1, input2;
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));

            numN_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);
            numS_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);
        } catch (Exception e){
            System.out.println("Error reading file. Exiting ...");
            return;
        }

        Bridge NZBridge = new Bridge(numOfPasses);

        FarmerThread[] N_Farmers = new FarmerThread[numN_Farmers];
        FarmerThread[] S_Farmers = new FarmerThread[numS_Farmers];

        for (int i = 1; i < numN_Farmers+1; i++) {
            N_Farmers[i-1] = new FarmerThread("N_Farmer"+i, false, numOfPasses, neon, NZBridge);
        }

        for (int i = 1; i < numS_Farmers+1; i++) {
            S_Farmers[i-1] = new FarmerThread("S_Farmer"+i, true, numOfPasses, neon, NZBridge);
        }

        while (true){
            for (int i = 0; i < numN_Farmers; i++) {
                NZBridge.cross(N_Farmers[i]);
                N_Farmers[i].changeDirection();
            }

            for (int i = 0; i < numS_Farmers; i++) {
                NZBridge.cross(S_Farmers[i]);
                S_Farmers[i].changeDirection();
            }
        }

        /*FarmerThread t1 = new FarmerThread("N_Farmer"+1, false, numOfPasses, neon, NZBridge);
        FarmerThread t2 = new FarmerThread("N_Farmer"+2, false, numOfPasses, neon, NZBridge);
        FarmerThread t3 = new FarmerThread("S_Farmer"+1, true, numOfPasses, neon, NZBridge);
        FarmerThread t4 = new FarmerThread("S_Farmer"+2, true, numOfPasses, neon, NZBridge);*/

        /*t1.start();
        t2.start();
        t3.start();
        t4.start();*/

        /*NZBridge.cross(t1);
        NZBridge.cross(t2);
        NZBridge.cross(t3);
        NZBridge.cross(t4);*/

        /*while (true){
            if (threadsCrossed[0]){
                System.out.println("All farmers have crossed");
                break;
            } else if (t1.hasCrossed() && !threadsCrossed[0]){
                neon++;
                threadsCrossed[0] = true;
                System.out.println("N_Farmer1 has crossed\nNEON = " + neon);

            } else if (t2.hasCrossed() && !threadsCrossed[1]){
                neon++;
                threadsCrossed[1] = true;
                System.out.println("N_Farmer2 has crossed\nNEON = " + neon);

            } else if (t3.hasCrossed() && !threadsCrossed[2]){
                neon++;
                threadsCrossed[2] = true;
                System.out.println("S_Farmer1 has crossed\nNEON = " + neon);

            }else if (t4.hasCrossed() && !threadsCrossed[3]){
                neon++;
                threadsCrossed[3] = true;
                System.out.println("S_Farmer2 has crossed\nNEON = " + neon);

            }
        }*/

        //Work out how to identify when a thread finishes and update neon. When all threads are finished create new threads for each farmer going the opposite direction



    }
}
