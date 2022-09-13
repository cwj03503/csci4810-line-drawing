import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;
import java.util.Scanner;

public class App {
    static final int FRAME_WIDTH = 750;
    static final int FRAME_HEIGHT = 750;
    static final int NUM_LINES = 1000;
    public static void main(String[] args) throws Exception {
        
        LineTester tester = new LineTester(FRAME_WIDTH, FRAME_HEIGHT);
        long totalRunTime;

        // Create some number of random lines using the DDA Algorithm
        
        System.out.println("DDA:");
        totalRunTime = tester.generateRandomLinesDDA(NUM_LINES);
        tester.displayFrame();
        System.out.println("Total time generating " + NUM_LINES + " random lines: " + totalRunTime + " ns.");
        

        
        // Create a number of different types of lines using the DDA Algorithm
        System.out.println("DDA:");
        totalRunTime = tester.generatePresetLinesDDA();
        tester.displayFrame();
        System.out.println("Total time generating lines: " + totalRunTime + " ns.");
        
    }
}
