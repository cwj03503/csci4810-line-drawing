import java.util.Scanner;

public class App {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 500;
    static final int NUM_LINES = 100;

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        LineTester tester = new LineTester(FRAME_WIDTH, FRAME_HEIGHT);
        // tester.generateCompleteComparativeReport(NUM_LINES);
        // tester.displayFrame(tester.bresenhamLineDrawer);

        // Preset Comparison
        comparePresetLines(scanner, tester);

        // Random Lines Test
        compareRandomLines(scanner, tester, NUM_LINES);
        
        scanner.close();
    }

    private static void comparePresetLines(Scanner scanner, LineTester tester) {
        tester.generatePresetLines(tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generatePresetLines(tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }

    private static void compareRandomLines(Scanner scanner, LineTester tester, int numLines) {
        tester.generateRandomLines(numLines, tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generateRandomLines(numLines, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }

}
