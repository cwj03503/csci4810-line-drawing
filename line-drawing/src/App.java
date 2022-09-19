import java.util.Scanner;

public class App {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 500;

    public static void main(String[] args) throws Exception {
        
        int numLines;
        Scanner scanner = new Scanner(System.in);
        LineTester tester = new LineTester(FRAME_WIDTH, FRAME_HEIGHT);
        
        System.out.println("Enter the number of lines to be drawn for testing: ");
        numLines = scanner.nextInt();
        scanner.nextLine();

        // tester.displayFrame(tester.bresenhamLineDrawer);
        comparePresetLines(scanner, tester);
        compareRandomLines(scanner, tester, numLines);
        compareRandomVerticalLines(scanner, tester, numLines);
        compareRandomHorizontalLines(scanner, tester, numLines);
        compareRandomLongLines(scanner, tester, numLines);
        compareRandomShortLines(scanner, tester, numLines);

        tester.generateCompleteComparativeReport(numLines);
        
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

    private static void compareRandomVerticalLines(Scanner scanner, LineTester tester, int numLines) {
        tester.generateRandomVerticalLines(numLines, tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generateRandomVerticalLines(numLines, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }


    private static void compareRandomHorizontalLines(Scanner scanner, LineTester tester, int numLines) {
        tester.generateRandomHorizontalLines(numLines, tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generateRandomHorizontalLines(numLines, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }

    private static void compareRandomLongLines(Scanner scanner, LineTester tester, int numLines) {
        tester.generateRandomLongLines(numLines, tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generateRandomLongLines(numLines, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }

    private static void compareRandomShortLines(Scanner scanner, LineTester tester, int numLines) {
        tester.generateRandomShortLines(numLines, tester.bresenhamLineDrawer);
        tester.displayFrame(tester.bresenhamLineDrawer);
        scanner.nextLine();

        tester.generateRandomShortLines(numLines, tester.basicLineDrawer);
        tester.displayFrame(tester.basicLineDrawer);
        scanner.nextLine();
    }

}
