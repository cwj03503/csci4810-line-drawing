import java.util.Scanner;

public class App {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 500;
    static final int NUM_LINES = 100;

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        LineTester tester = new LineTester(FRAME_WIDTH, FRAME_HEIGHT);

        // System.out.println("Bresenham Line Drawing Algorithm:");
        // totalRunTime = tester.generateRandomHorizontalLines(NUM_LINES, tester.bresenhamLineDrawer);
        // tester.displayFrame(tester.bresenhamLineDrawer);
        // System.out.println("Total time generating " + NUM_LINES + " random lines: " + totalRunTime + " ns.");
        tester.generateCompleteComparativeReport(NUM_LINES);

        scanner.close();
    }
}
