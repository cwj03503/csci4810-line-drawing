import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;
import java.util.Scanner;

public class App {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 500;
    static final int NUM_LINES = 100;

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        LineTester tester = new LineTester(FRAME_WIDTH, FRAME_HEIGHT);
        long totalRunTime;

        // Create some number of random lines using the DDA Algorithm
        
        
        System.out.println("Simple Line Drawing Algorithm:");
        totalRunTime = tester.generateRandomLinesBasic(NUM_LINES);
        tester.displayFrame();
        System.out.println("Total time generating " + NUM_LINES + " random lines: " + totalRunTime + " ns.");
        

        // scanner.nextLine();
        
        // Create a number of different types of lines using the DDA Algorithm
         
        /*
        System.out.println("Simple Line Drawing Algorithm:");
        totalRunTime = tester.generatePresetLinesBasic();
        tester.displayFrame();
        System.out.println("Total time generating lines: " + totalRunTime + " ns.");
        */

        scanner.close();
    }
}
