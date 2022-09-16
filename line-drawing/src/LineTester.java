import javax.swing.*;

import java.awt.Dimension;
import java.lang.Math;

public class LineTester {

    public BasicLineDrawer basicLineDrawer;
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

        // initialize panels
        basicLineDrawer = new BasicLineDrawer(width, height);
        basicLineDrawer.setLayout(new BoxLayout(basicLineDrawer, BoxLayout.LINE_AXIS));
        basicLineDrawer.setMaximumSize(new Dimension(width, height));
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

    public long generatePresetLinesBasic(LineDrawer lineDrawer)
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

    public void displayFrame(LineDrawer panel)
    {
        panel.drawImage();
        frame.add(panel);
        frame.setVisible(true);
        panel.clearImage();
    }
}
