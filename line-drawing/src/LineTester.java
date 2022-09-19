import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class LineTester {

    public BasicLineDrawer basicLineDrawer;
    public BresenhamLineDrawer bresenhamLineDrawer;
    private JFrame frame;
    private int width;
    private int height;

    interface simpleReportGenerator {
        void reportRandom(int n);
    }

    /**
     * Creates a new LineTester, with a JFrame of the given width and height and 
     * two LineDrawer instances, one being BresenhamLineDrawer and the other being
     * BasicLineDrawer. 
     * @param width  the width of this LineTester's JFrame
     * @param height  the height of this LineTester's JFrame
     */
    public LineTester(int width, int height)
    {
        this.width = width;
        this.height = height;

        // initialize frame
        frame = new JFrame();
        frame.setTitle("Line Drawer (" + width + " x " + height + ")");
        frame.setLocationRelativeTo(null);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // initialize panels
        basicLineDrawer = new BasicLineDrawer(width, height);
        basicLineDrawer.setLayout(new BoxLayout(basicLineDrawer, BoxLayout.LINE_AXIS));
        basicLineDrawer.setMaximumSize(new Dimension(width, height));

        bresenhamLineDrawer = new BresenhamLineDrawer(width, height);
        bresenhamLineDrawer.setLayout(new BoxLayout(bresenhamLineDrawer, BoxLayout.LINE_AXIS));
        bresenhamLineDrawer.setMaximumSize(new Dimension(width, height));
    }

    public long generateRandomLines(int numLines, LineDrawer lineDrawer)
    {
        // draw random lines
        long time = 0;
        for (int i = 0; i < numLines ; i++)
        {
            int x0 = (int)(Math.random() * width);
            int x1 = (int)(Math.random() * width);
            int y0 = (int)(Math.random() * height);
            int y1 = (int)(Math.random() * height);

            time += lineDrawer.drawLine(x0, y0, x1, y1);
        }
        return time;
    }

    public long generatePresetLines(LineDrawer lineDrawer)
    {
        long totalRunTime = 0;

        // horizontal line
        totalRunTime += lineDrawer.drawLine((int)(width * 0.2), height / 2, 
            (int)(width * 0.8), height / 2);

        // vertical line
        totalRunTime += lineDrawer.drawLine((int)(height/2), (int)(height * 0.2), 
            (int)(height/2), (int)(height * 0.8));

        // diagonal line with positive slope
        totalRunTime += lineDrawer.drawLine((int)(width * 0.2), (int)(height * 0.2), 
            (int)(width * 0.8), (int)(height * 0.8));

        // diagonal line with negative slope
        totalRunTime += lineDrawer.drawLine((int)(width * 0.2), (int)(height * 0.8),
            (int)(width * 0.8), (int)(height * 0.2));

        return totalRunTime;
    }

    public long generateRandomLongLines(int numLines, LineDrawer lineDrawer)
    {
        long time = 0;
        int x0, x1, y0, y1;
        // draw long lines from bottom to top of frame
        for (int i = 0; i < numLines/2 ; i++)
        {
            x0 = ThreadLocalRandom.current().nextInt(0, width);
            x1 = ThreadLocalRandom.current().nextInt(0, width);
            y0 = ThreadLocalRandom.current().nextInt(0, (int)(height * 0.2));
            y1 = ThreadLocalRandom.current().nextInt((int)(height * 0.8), height);

            time += lineDrawer.drawLine(x0, y0, x1, y1);
            
        }
        // draw lines from left to right of frame
        for (int i = 0; i < numLines / 2; i++)
        {
            y0 = ThreadLocalRandom.current().nextInt(0, height);
            y1 = ThreadLocalRandom.current().nextInt(0, height);
            x0 = ThreadLocalRandom.current().nextInt(0, (int)(width * 0.2));
            x1 = ThreadLocalRandom.current().nextInt((int)(width * 0.8), width);

            time += lineDrawer.drawLine(x0, y0, x1, y1);
        }
        return time;
    }

    public long generateRandomShortLines(int numLines, LineDrawer lineDrawer)
    {
        long time = 0;
        int x0, x1, y0, y1;
        for (int i = 0; i < numLines ; i++)
        {
            x0 = ThreadLocalRandom.current().nextInt((int)(width * 0.2), (int)(width * 0.8));
            x1 = ThreadLocalRandom.current().nextInt(0,(int)(width * 0.2)) + x0;
            y0 = ThreadLocalRandom.current().nextInt((int)(height * 0.2), (int)(height * 0.8));
            y1 = ThreadLocalRandom.current().nextInt(0, (int)(height * 0.2)) + y0;

            time += lineDrawer.drawLine(x0, y0, x1, y1); 
        }
        return time;
    }

    public long generateRandomVerticalLines(int numLines, LineDrawer lineDrawer)
    {
        long time = 0;
        int x0, x1, y0, y1;
        for (int i = 0; i < numLines ; i++)
        {
            x0 = ThreadLocalRandom.current().nextInt(0, width);
            x1 = x0;
            y0 = ThreadLocalRandom.current().nextInt(0, height);
            y1 = ThreadLocalRandom.current().nextInt(0, height);

            time += lineDrawer.drawLine(x0, y0, x1, y1); 
        }
        return time;
    }

    public long generateRandomHorizontalLines(int numLines, LineDrawer lineDrawer)
    {
        long time = 0;
        int x0, x1, y0, y1;
        for (int i = 0; i < numLines ; i++)
        {
            x0 = ThreadLocalRandom.current().nextInt(0, width);
            x1 = ThreadLocalRandom.current().nextInt(0, width);
            y0 = ThreadLocalRandom.current().nextInt(0, height);
            y1 = y0;

            time += lineDrawer.drawLine(x0, y0, x1, y1); 
        }
        return time;
    }

    private long averageTimeRandomLines(int numSamples, int numLines, LineDrawer lineDrawer)
    {
        long totalTime = 0;
        for (int i = 0; i < numSamples; i++)
        {
            totalTime += generateRandomLines(numLines, bresenhamLineDrawer);
        }
        return totalTime/numSamples;
    }

    private long averageTimeLongLines(int numSamples, int numLines, LineDrawer lineDrawer)
    {
        long totalTime = 0;
        for (int i = 0; i < numSamples; i++)
        {
            totalTime += generateRandomLongLines(numLines, bresenhamLineDrawer);
        }
        return totalTime/numSamples;
    }

    private long averageTimeShortLines(int numSamples, int numLines, LineDrawer lineDrawer)
    {
        long totalTime = 0;
        for (int i = 0; i < numSamples; i++)
        {
            totalTime += generateRandomShortLines(numLines, bresenhamLineDrawer);
        }
        return totalTime/numSamples;
    }

    private long averageTimeVerticalLines(int numSamples, int numLines, LineDrawer lineDrawer)
    {
        long totalTime = 0;
        for (int i = 0; i < numSamples; i++)
        {
            totalTime += generateRandomVerticalLines(numLines, bresenhamLineDrawer);
        }
        return totalTime/numSamples;
    }

    private long averageTimeHorizontalLines(int numSamples, int numLines, LineDrawer lineDrawer)
    {
        long totalTime = 0;
        for (int i = 0; i < numSamples; i++)
        {
            totalTime += generateRandomHorizontalLines(numLines, bresenhamLineDrawer);
        }
        return totalTime/numSamples;
    }

    
    public void generateCompleteComparativeReport(int numLines)
    {
        int numSamples = 100;
        
        System.out.println("--- Bresenham vs. Naive Line Drawing Algorithm Comparison ---\n");
        System.out.println("Average time in (ns) drawing " + numLines + " lines.");
        System.out.println("Drawing\t\t\tBresenham\tSimple");

        System.out.print("N Random lines:\t\t");
        System.out.print(averageTimeRandomLines(numSamples, numLines, bresenhamLineDrawer));
        System.out.print("\t\t");
        System.out.print(averageTimeRandomLines(numSamples, numLines, basicLineDrawer));
        System.out.println();

        System.out.print("N Long lines:\t\t");
        System.out.print(averageTimeLongLines(numSamples, numLines, bresenhamLineDrawer));
        System.out.print("\t\t");
        System.out.print(averageTimeLongLines(numSamples, numLines, basicLineDrawer));
        System.out.println();


        System.out.print("N Short lines:\t\t");
        System.out.print(averageTimeShortLines(numSamples, numLines, bresenhamLineDrawer));
        System.out.print("\t\t");
        System.out.print(averageTimeShortLines(numSamples, numLines, basicLineDrawer));
        System.out.println();

        System.out.print("N Vertical lines:\t");
        System.out.print(averageTimeVerticalLines(numSamples, numLines, bresenhamLineDrawer));
        System.out.print("\t\t");
        System.out.print(averageTimeVerticalLines(numSamples, numLines, basicLineDrawer));
        System.out.println();

        System.out.print("N Horizontal lines:\t");
        System.out.print(averageTimeHorizontalLines(numSamples, numLines, bresenhamLineDrawer));
        System.out.print("\t\t");
        System.out.print(averageTimeHorizontalLines(numSamples, numLines, basicLineDrawer));
        System.out.println();

    }

    public void generateSizeComparativeReport()
    {
        int numSamples = 1000;
        System.out.println("--- Bresenham vs. Naive Line Drawing Algorithm Comparison ---\n");
        System.out.println("Average time in (ns) drawing N Lines");
        System.out.println("N\t\tBresenham\tSimple");
        simpleReportGenerator reporter = (n) ->
        {
            System.out.print(n + "\t\t");
            System.out.print(averageTimeRandomLines(numSamples, n, bresenhamLineDrawer));
            System.out.print("\t\t");
            System.out.print(averageTimeRandomLines(numSamples, n, basicLineDrawer));
            System.out.println();
        };
        reporter.reportRandom(1);
        reporter.reportRandom(10);
        reporter.reportRandom(100);
        reporter.reportRandom(1000);
        reporter.reportRandom(10000);
    }

    public void displayFrame(LineDrawer panel)
    {
        frame.getContentPane().removeAll();
        frame.setTitle("Line Drawer: " + panel.getClass().getSimpleName() +  " (" + width + " x " + height + ")");
        panel.drawImage();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        panel.clearImage();
    }
}
