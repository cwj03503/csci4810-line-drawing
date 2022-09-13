import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;

public class LineTester {

    private BasicLineDrawer panel;
    private JFrame frame;
    private int width;
    private int height;

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

        // initialize panel
        panel = new BasicLineDrawer(width, height);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setMaximumSize(new Dimension(width, height));
    }

    public long generateRandomLinesDDA(int numLines)
    {
        // draw random lines
        long totalRunTime = 0;

        for (int i = 0; i < numLines ; i++)
        {
            int x0 = (int)(Math.random() * width);
            int x1 = (int)(Math.random() * width);
            int y0 = (int)(Math.random() * height);
            int y1 = (int)(Math.random() * height);

            long start = System.nanoTime();
            panel.drawLineBasic(x0, y0, x1, y1);
            long end = System.nanoTime();
            totalRunTime += (end - start);

            // System.out.println("line drawn from (" 
            // + x0 + "," + y0 + ") to (" + x1 + "," + y1 + ").");
        }
        return totalRunTime;
    }

    public long generatePresetLinesDDA()
    {
        long totalRunTime = 0;
        long start = System.nanoTime();

        // horizontal line
        panel.drawLineBasic((int)(width * 0.2), height / 2, 
            (int)(width * 0.8), height / 2);

        // vertical line
        panel.drawLineBasic((int)(height/2), (int)(height * 0.2), 
            (int)(height/2), (int)(height * 0.8));

        // diagonal line with positive slope
        panel.drawLineBasic((int)(width * 0.2), height / 2, 
            (int)(width * 0.8), height / 2);

        long end = System.nanoTime();
        totalRunTime += (end - start);
        
        return totalRunTime;
    }

    public void displayFrame()
    {
        panel.drawImage();
        frame.add(panel);
        frame.setVisible(true);
        panel.clearImage();
    }
}
