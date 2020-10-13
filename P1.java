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

    public static boolean allTrue(boolean[] farmersCrossed){
        for (boolean b:farmersCrossed) {
            if (!b)
                return false;
        }
        return true;
    }

    public static void main(String args[]) throws InterruptedException {//look into parallel
        Semaphore numOfPasses = new Semaphore(1);//A semaphore used to keep track of how if the bridge is in use
        //numOfPasses.release();//increments +1 to permits counter
        //numOfPasses.acquire();//decrements -1 to permits counter
        int count = 0, S_Farmers = 0, N_Farmers = 0;
        int neon = 0;
        boolean allCrossed = false;
        String in;
        String[] input1, input2;
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));

            N_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);
            S_Farmers = Integer.parseInt(reader.next().split("=")[1].split("")[0]);
        } catch (Exception e){
            System.out.println("Error reading file. Exiting ...");
            return;
        }
        boolean[] threadsCrossed = new boolean[N_Farmers*2];//boolean array for tracking which threads have run
        for (int i = 0; i < 2*N_Farmers; i++) {
            threadsCrossed[i] = false;
        }
        /*Thread t1 = new Thread(new tLogic("N_Farmer"+1, false, numOfPasses));
        Thread t2 = new Thread(new tLogic("N_Farmer"+2, false, numOfPasses));
        Thread t3 = new Thread(new tLogic("S_Farmer"+1, true, numOfPasses));
        Thread t4 = new Thread(new tLogic("S_Farmer"+2, true, numOfPasses));*/

        farmerThread t1 = new farmerThread("N_Farmer"+1, false, numOfPasses, neon);
        farmerThread t2 = new farmerThread("N_Farmer"+2, false, numOfPasses, neon);
        farmerThread t3 = new farmerThread("S_Farmer"+1, true, numOfPasses, neon);
        farmerThread t4 = new farmerThread("S_Farmer"+2, true, numOfPasses, neon);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        while (true){
            if (allTrue(threadsCrossed) || (threadsCrossed[0]&&threadsCrossed[1]&&threadsCrossed[2]&&threadsCrossed[3])){
                System.out.println("All farmers have crossed");
                break;
            } else if (t1.hasCrossed() && !threadsCrossed[0]){
                neon++;
                threadsCrossed[0] = true;


            } else if (t2.hasCrossed() && !threadsCrossed[1]){
                neon++;
                threadsCrossed[1] = true;


            } else if (t3.hasCrossed() && !threadsCrossed[2]){
                neon++;
                threadsCrossed[2] = true;


            }else if (t4.hasCrossed() && !threadsCrossed[3]){
                neon++;
                threadsCrossed[3] = true;


            }
        }

        System.out.println("All threads executed once");

        /*t1 = new Thread(new tLogic("N_Farmer"+1, true, numOfPasses));
        t2 = new Thread(new tLogic("N_Farmer"+2, true, numOfPasses));
        t3 = new Thread(new tLogic("S_Farmer"+1, false, numOfPasses));
        t4 = new Thread(new tLogic("S_Farmer"+2, false, numOfPasses));*/

        /*t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();*/

        //Work out how to identify when a thread finishes and update neon. When all threads are finished create new threads for each farmer going the opposite direction
        /*while (true){
            if (!t1.isAlive()&&!t2.isAlive()&&!t3.isAlive()&&!t4.isAlive()){
                System.out.println("All finished");
                t1 = new Thread(new tLogic("N_Farmer"+1, false, numOfPasses));
                t2 = new Thread(new tLogic("N_Farmer"+2, false, numOfPasses));
                t3 = new Thread(new tLogic("S_Farmer"+1, true, numOfPasses));
                t4 = new Thread(new tLogic("S_Farmer"+2, true, numOfPasses));
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                break;
            }
        }*/



    }
}
