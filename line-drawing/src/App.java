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

        System.out.println("Simple Line Drawing Algorithm:");
        totalRunTime = tester.generateRandomLines(NUM_LINES, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        System.out.println("Total time generating " + NUM_LINES + " random lines: " + totalRunTime + " ns.");

        scanner.close();
    }
}
